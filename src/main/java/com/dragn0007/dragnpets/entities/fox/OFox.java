package com.dragn0007.dragnpets.entities.fox;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.horse.BreedModel;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.util.POTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;

public class OFox extends TamableAnimal implements GeoEntity {

   private Goal turtleEggTargetGoal;
   private Goal fishTargetGoal;

   private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_fox");
   private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/fox");
   @Override
   public @NotNull ResourceLocation getDefaultLootTable() {
      if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
         return VANILLA_LOOT_TABLE;
      }
      return LOOT_TABLE;
   }
   
   public static final Predicate<LivingEntity> PREY_SELECTOR = (entity) -> {
      EntityType<?> entitytype = entity.getType();
      return entitytype == EntityTypes.O_RABBIT_ENTITY.get() ||
              entitytype == EntityTypes.O_CHICKEN_ENTITY.get() ||
              entitytype == EntityTypes.O_FROG_ENTITY.get();
   };

   public OFox(EntityType<? extends OFox> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Player.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5D, true));
      this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
      this.turtleEggTargetGoal = new NearestAttackableTargetGoal<>(this, Turtle.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR);
      this.fishTargetGoal = new NearestAttackableTargetGoal<>(this, AbstractFish.class, 20, false, false, (p_28600_) -> {
         return p_28600_ instanceof AbstractSchoolingFish;
      });
      this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
      this.goalSelector.addGoal(10, new OFox.FoxEatBerriesGoal((double)1.2F, 12, 1));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
              .add(Attributes.MAX_HEALTH, 10.0D)
              .add(Attributes.FOLLOW_RANGE, 32.0D)
              .add(Attributes.ATTACK_DAMAGE, 2.0D);
   }

   private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
      double currentSpeed = this.getDeltaMovement().lengthSqr();
      double speedThreshold = 0.015;

      AnimationController<T> controller = tAnimationState.getController();

      if (tAnimationState.isMoving()) {
         if (currentSpeed > speedThreshold) {
            controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.3);
         } else {
            controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.3);
         }
      } else {
         if (isInSittingPose()) {
            controller.setAnimation(RawAnimation.begin().then("loaf", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.0);
         } else {
            controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
         }
      }

      return PlayState.CONTINUE;
   }

   @Override
   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
      controllers.add(new AnimationController<>(this, "controller", 2, this::predicate));
   }

   @Override
   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.geoCache;
   }

   public SoundEvent getEatingSound(ItemStack p_28540_) {
      return SoundEvents.FOX_EAT;
   }

   protected boolean isImmobile() {
      return this.isDeadOrDying();
   }

   @Override
   public float getStepHeight() {
      return 1.6F;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public boolean hurt(DamageSource damageSource, float v) {
      if (damageSource.is(DamageTypes.FALL)) {
         return false;
      }
      return super.hurt(damageSource, v);
   }

   @Nullable
   protected SoundEvent getAmbientSound() {
      return SoundEvents.FOX_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource p_29035_) {
      return SoundEvents.FOX_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.FOX_DEATH;
   }

   private float getAttackDamage() {
      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
   }

   public boolean doHurtTarget(Entity p_28990_) {
      return p_28990_.hurt(this.damageSources().mobAttack(this), this.getAttackDamage());
   }

   public void setTame(boolean p_30443_) {
      super.setTame(p_30443_);
      if (p_30443_) {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
   }

   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);
      Item item = itemstack.getItem();

      if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isFemale()) {
         player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
         ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.FEMALE_GENDER_TEST_STRIP.get().getDefaultInstance());
         player.setItemInHand(hand, itemstack1);
         return InteractionResult.SUCCESS;
      }

      if (itemstack.is(LOItems.GENDER_TEST_STRIP.get()) && this.isMale()) {
         player.playSound(SoundEvents.BEEHIVE_EXIT, 1.0F, 1.0F);
         ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player, LOItems.MALE_GENDER_TEST_STRIP.get().getDefaultInstance());
         player.setItemInHand(hand, itemstack1);
         return InteractionResult.SUCCESS;
      }

      if (this.level().isClientSide) {
         boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(POTags.Items.FOX_FOOD) && !this.isTame();
         return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
      } else if (this.isTame()) {
         if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
            this.heal((float)itemstack.getFoodProperties(this).getNutrition());
            if (!player.getAbilities().instabuild) {
               itemstack.shrink(1);
            }

            this.gameEvent(GameEvent.EAT, this);
            return InteractionResult.SUCCESS;
            
         } else {
            InteractionResult interactionresult = super.mobInteract(player, hand);
            if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
               this.setOrderedToSit(!this.isOrderedToSit());
               this.jumping = false;
               this.navigation.stop();
               this.setTarget(null);
               return InteractionResult.SUCCESS;
            } else {
               return interactionresult;
            }
         }
      } else if (itemstack.is(POTags.Items.FOX_FOOD)) {
         if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
         }

         if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget((LivingEntity)null);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, (byte)7);
         } else {
            this.level().broadcastEntityEvent(this, (byte)6);
         }

         return InteractionResult.SUCCESS;
      } else {
         return super.mobInteract(player, hand);
      }
   }

   private static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.FOX_FOOD);

   @Override
   public boolean isFood(ItemStack itemStack) {
      return FOOD_ITEMS.test(itemStack);
   }


   // Generates the base texture
   public ResourceLocation getTextureResource() {
      return OFoxModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }

   public ResourceLocation getOverlayResource() {
      return OFoxMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
   }

   public ResourceLocation getDomesticResource() {
      return OFoxModel.DomesticVariant.domesticVariantFromOrdinal(getDomesticVariant()).resourceLocation;
   }

   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> OVERLAY_VARIANT = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DOMESTIC_VARIANT = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);

   public int getVariant() {
      return this.entityData.get(VARIANT);
   }

   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }

   public int getOverlayVariant() {
      return this.entityData.get(OVERLAY_VARIANT);
   }

   public void setOverlayVariant(int variant) {
      this.entityData.set(OVERLAY_VARIANT, variant);
   }

   public int getDomesticVariant() {
      return this.entityData.get(DOMESTIC_VARIANT);
   }

   public void setDomestictVariant(int variant) {
      this.entityData.set(DOMESTIC_VARIANT, variant);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY_VARIANT, 0);
      this.entityData.define(DOMESTIC_VARIANT, 0);
      this.entityData.define(GENDER, 0);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putInt("Variant", getVariant());
      tag.putInt("Overlay", getOverlayVariant());
      tag.putInt("DomesticVariant", getDomesticVariant());
      tag.putInt("Gender", this.getGender());
   }

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);

      if (tag.contains("Variant")) {
         setVariant(tag.getInt("Variant"));
      }

      if (tag.contains("Overlay")) {
         setOverlayVariant(tag.getInt("Overlay"));
      }

      if (tag.contains("DomesticVariant")) {
         setDomestictVariant(tag.getInt("DomesticVariant"));
      }

      if (tag.contains("Gender")) {
         this.setGender(tag.getInt("Gender"));
      }
   }

   @Override
   @Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setVariant(random.nextInt(OFoxModel.Variant.values().length));
      setGender(random.nextInt(Gender.values().length));

      return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
   }

   public enum Gender {
      FEMALE,
      MALE
   }

   public boolean isFemale() {
      return this.getGender() == 0;
   }

   public boolean isMale() {
      return this.getGender() == 1;
   }

   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);

   public int getGender() {
      return this.entityData.get(GENDER);
   }

   public void setGender(int gender) {
      this.entityData.set(GENDER, gender);
   }

   public boolean canParent() {
      return !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
   }

   public boolean canMate(Animal animal) {
      if (animal == this) {
         return false;
      } else if (!(animal instanceof OFox)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((OFox) animal).canParent();
         } else {
            OFox partner = (OFox) animal;
            if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
               return true;
            }

            boolean partnerIsFemale = partner.isFemale();
            boolean partnerIsMale = partner.isMale();
            if (LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get() && this.canParent() && partner.canParent()
                    && ((isFemale() && partnerIsMale) || (isMale() && partnerIsFemale))) {
               return isFemale();
            }
         }
      }
      return false;
   }

   public boolean isDomestic = false;

   public boolean isDomestic() {
      return isDomestic;
   }

   public void setDomestic(boolean domestic) {
      this.isDomestic = domestic;
   }

   @Override
   public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
      OFox oFox = (OFox) ageableMob;
      if (ageableMob instanceof OFox) {
         OFox fox = (OFox) ageableMob;
         oFox = com.dragn0007.dragnpets.entities.EntityTypes.O_FOX_ENTITY.get().create(serverLevel);

         int j = this.random.nextInt(9);
         int variant;

         if (j < 3) {
            variant = this.random.nextBoolean() ? this.getVariant() : fox.getVariant();
         } else {
            variant = this.random.nextBoolean() ? fox.getVariant() : this.getVariant();
         }

         int gender = this.random.nextInt(OFox.Gender.values().length);

         oFox.setVariant(variant);
         oFox.setGender(gender);

         oFox.setDomestic(variant == OFoxModel.DomesticVariant.values().length);

      }

      return oFox;
   }


   public boolean canBeLeashed(Player p_30396_) {
      return super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public class FoxEatBerriesGoal extends MoveToBlockGoal {
      private static final int WAIT_TICKS = 40;
      protected int ticksWaited;

      public FoxEatBerriesGoal(double p_28675_, int p_28676_, int p_28677_) {
         super(OFox.this, p_28675_, p_28676_, p_28677_);
      }

      public double acceptedDistance() {
         return 2.0D;
      }

      public boolean shouldRecalculatePath() {
         return this.tryTicks % 100 == 0;
      }

      protected boolean isValidTarget(LevelReader p_28680_, BlockPos p_28681_) {
         BlockState blockstate = p_28680_.getBlockState(p_28681_);
         return blockstate.is(Blocks.SWEET_BERRY_BUSH) && blockstate.getValue(SweetBerryBushBlock.AGE) >= 2 || CaveVines.hasGlowBerries(blockstate);
      }

      public void tick() {
         if (this.isReachedTarget()) {
            if (this.ticksWaited >= 40) {
               this.onReachedTarget();
            } else {
               ++this.ticksWaited;
            }
         } else if (!this.isReachedTarget() && OFox.this.random.nextFloat() < 0.05F) {
            OFox.this.playSound(SoundEvents.FOX_SNIFF, 1.0F, 1.0F);
         }

         super.tick();
      }

      protected void onReachedTarget() {
         if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(OFox.this.level(), OFox.this)) {
            BlockState blockstate = OFox.this.level().getBlockState(this.blockPos);
            if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
               this.pickSweetBerries(blockstate);
            } else if (CaveVines.hasGlowBerries(blockstate)) {
               this.pickGlowBerry(blockstate);
            }

         }
      }

      private void pickGlowBerry(BlockState p_148927_) {
         CaveVines.use(OFox.this, p_148927_, OFox.this.level(), this.blockPos);
      }

      private void pickSweetBerries(BlockState p_148929_) {
         int i = p_148929_.getValue(SweetBerryBushBlock.AGE);
         p_148929_.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
         int j = 1 + OFox.this.level().random.nextInt(2) + (i == 3 ? 1 : 0);
         ItemStack itemstack = OFox.this.getItemBySlot(EquipmentSlot.MAINHAND);
         if (itemstack.isEmpty()) {
            OFox.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
            --j;
         }

         if (j > 0) {
            Block.popResource(OFox.this.level(), this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
         }

         OFox.this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
         OFox.this.level().setBlock(this.blockPos, p_148929_.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
      }

      public boolean canUse() {
         return !OFox.this.isSleeping() && super.canUse();
      }

      public void start() {
         this.ticksWaited = 0;
         super.start();
      }
   }
}