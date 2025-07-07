package com.dragn0007.dragnpets.entities.fox;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.ai.FoxFollowOwnerGoal;
import com.dragn0007.dragnpets.util.POTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
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
import net.minecraftforge.fml.ModList;
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

   public Goal turtleEggTargetGoal;
   public Goal fishTargetGoal;



   public static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_fox");
   public static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/fox");
   private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/fox");
   @Override
   public @NotNull ResourceLocation getDefaultLootTable() {
      if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
         return VANILLA_LOOT_TABLE;
      }
      if (ModList.get().isLoaded("tfc")) {
         return TFC_LOOT_TABLE;
      }
      return LOOT_TABLE;
   }
   
   public static final Predicate<LivingEntity> PREY_SELECTOR = (entity) -> {
      EntityType<?> entitytype = entity.getType();
      return entitytype == EntityTypes.O_RABBIT_ENTITY.get() ||
              entitytype == EntityTypes.O_CHICKEN_ENTITY.get() ||
              entitytype == EntityTypes.O_FROG_ENTITY.get() ||
              entitytype == POEntityTypes.O_CAT_ENTITY.get()
              ;
   };

   public OFox(EntityType<? extends OFox> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Player.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5D, true));
      this.goalSelector.addGoal(6, new FoxFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
      this.fishTargetGoal = new NearestAttackableTargetGoal<>(this, AbstractFish.class, 20, false, false, (p_28600_) -> {
         return p_28600_ instanceof AbstractSchoolingFish;
      });
      this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
      this.goalSelector.addGoal(10, new OFox.FoxEatBerriesGoal((double)1.2F, 12, 1));

      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
              (entity.getType().is(LOTags.Entity_Types.WOLVES) && !this.isTame()) ||
                      (entity.getType().is(LOTags.Entity_Types.WOLVES) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTame()
      ));

      this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, entity ->
              (entity.getType().is(LOTags.Entity_Types.HUNTING_DOGS) && !this.isTame()) ||
                      (entity.getType().is(LOTags.Entity_Types.HUNTING_DOGS) && (entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())) && this.isTame()
      ));

      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 2, true, false,
              entity -> entity.getType().is(POTags.Entity_Types.FOXES_HUNT) && ((entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame()) || (!this.isTame()) || (this.isTame() && this.wasToldToWander() && entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())))  {
      });
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MOVEMENT_SPEED, (double)0.3F)
              .add(Attributes.MAX_HEALTH, 10.0D)
              .add(Attributes.FOLLOW_RANGE, 32.0D)
              .add(Attributes.ATTACK_DAMAGE, 2.0D);
   }

   public final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   public <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
      double currentSpeed = this.getDeltaMovement().lengthSqr();
      double speedThreshold = 0.02;

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

   protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
      return entityDimensions.height;
   }

   public SoundEvent getEatingSound(ItemStack p_28540_) {
      return SoundEvents.FOX_EAT;
   }

   public boolean isImmobile() {
      return this.isDeadOrDying();
   }

   @Override
   public float getStepHeight() {
      return 1.6F;
   }

   public float getSoundVolume() {
      return 0.4F;
   }

   @Nullable
   public SoundEvent getAmbientSound() {
      return SoundEvents.FOX_AMBIENT;
   }

   public SoundEvent getHurtSound(DamageSource p_29035_) {
      return SoundEvents.FOX_HURT;
   }

   public SoundEvent getDeathSound() {
      return SoundEvents.FOX_DEATH;
   }

   public float getAttackDamage() {
      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
   }

   public boolean doHurtTarget(Entity p_28990_) {
      return p_28990_.hurt(this.damageSources().mobAttack(this), this.getAttackDamage());
   }

   public void setTame(boolean p_30443_) {
      super.setTame(p_30443_);
      if (p_30443_) {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10.0D);
         this.setHealth(10.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0D);
   }

   public int regenHealthCounter = 0;

   @Override
   public void tick() {
      super.tick();

      regenHealthCounter++;

      if (this.getHealth() < this.getMaxHealth() && regenHealthCounter >= 150 && this.isTame() && this.isAlive()) {
         this.setHealth(this.getHealth() + 2);
         regenHealthCounter = 0;
         this.level().addParticle(ParticleTypes.HEART, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
      }

   }

   @Override
   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);
      Item item = itemstack.getItem();

      if (itemstack.is(Items.SHEARS) && this.isCollared()) {
         this.setCollared(false);
         this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      }

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

      if (itemstack.is(LOItems.COAT_OSCILLATOR.get()) && player.getAbilities().instabuild) {
         if (player.isShiftKeyDown()) {
            this.setVariant(this.getVariant() - 1);
            this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }
         this.setVariant(this.getVariant() + 1);
         this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else if (itemstack.is(LOItems.MARKING_OSCILLATOR.get()) && player.getAbilities().instabuild) {
         if (player.isShiftKeyDown()) {
            this.setOverlayVariant(this.getOverlayVariant() - 1);
            this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }
         this.setOverlayVariant(this.getOverlayVariant() + 1);
         this.playSound(SoundEvents.BEEHIVE_EXIT, 0.5f, 1f);
         return InteractionResult.sidedSuccess(this.level().isClientSide);
      }

      if (player.isShiftKeyDown() && !this.isFood(itemstack) && !this.isOrderedToSit() && !this.wasToldToWander() && this.isOwnedBy(player)) {
         this.setToldToWander(true);
         player.displayClientMessage(Component.translatable("tooltip.dragnpets.wandering.tooltip").withStyle(ChatFormatting.GOLD), true);
         return InteractionResult.SUCCESS;
      }

      if (player.isShiftKeyDown() && !this.isFood(itemstack) && !this.isOrderedToSit() && this.wasToldToWander() && this.isOwnedBy(player)) {
         this.setToldToWander(false);
         player.displayClientMessage(Component.translatable("tooltip.dragnpets.following.tooltip").withStyle(ChatFormatting.GOLD), true);
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
            if (item instanceof DyeItem) {
                  return super.mobInteract(player, hand);
            }

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

   public static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.FOX_FOOD);
   @Override
   public boolean isFood(ItemStack itemStack) {
      return FOOD_ITEMS.test(itemStack);
   }


   // Generates the base texture
   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);
   public ResourceLocation getTextureLocation() {
      return OFoxModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }
   public int getVariant() {
      return this.entityData.get(VARIANT);
   }
   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }


   public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);
   public ResourceLocation getOverlayLocation() {return OFoxMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
   public int getOverlayVariant() {
      return this.entityData.get(OVERLAY);
   }
   public void setOverlayVariant(int overlayVariant) {
      this.entityData.set(OVERLAY, overlayVariant);
   }

   public boolean toldToWander = false;
   public boolean wasToldToWander() {
      return this.toldToWander;
   }
   public boolean getToldToWander() {
      return this.toldToWander;
   }
   public void setToldToWander(boolean toldToWander) {
      this.toldToWander = toldToWander;
   }

   public static final EntityDataAccessor<Boolean> COLLARED = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.BOOLEAN);
   public boolean isCollared() {
      return this.entityData.get(COLLARED);
   }
   public void setCollared(boolean collared) {
      this.entityData.set(COLLARED, collared);
   }

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(OFox.class, EntityDataSerializers.INT);
   public DyeColor getCollarColor() {
      return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
   }
   public void setCollarColor(DyeColor p_30398_) {
      this.entityData.set(DATA_COLLAR_COLOR, p_30398_.getId());
   }

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY, 0);
      this.entityData.define(GENDER, 0);
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(COLLARED, false);
   }

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);
      if (tag.contains("Variant")) {
         setVariant(tag.getInt("Variant"));
      }

      if (tag.contains("Overlay")) {
         setOverlayVariant(tag.getInt("Overlay"));
      }

      if (tag.contains("Gender")) {
         this.setGender(tag.getInt("Gender"));
      }

      if (tag.contains("Wandering")) {
         this.setToldToWander(tag.getBoolean("Wandering"));
      }

      if (tag.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(tag.getInt("CollarColor")));
      }

      if(tag.contains("Collared")) {
         this.setCollared(tag.getBoolean("Collared"));
      }

   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putInt("Variant", getVariant());
      tag.putInt("Overlay", getOverlayVariant());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putBoolean("Collared", this.isCollared());
   }

   @Override
   @javax.annotation.Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setGender(random.nextInt(OFox.Gender.values().length));

      if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
         this.setColor();
         this.setMarking();
      } else {
         setVariant(random.nextInt(OFoxModel.Variant.values().length));
         setOverlayVariant(random.nextInt(OFoxMarkingLayer.Overlay.values().length));
      }

      return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
   }

   public void setColor() {

      if (random.nextDouble() < 0.05) {
         int[] variants = {0, 1, 2, 3, 4, 6, 9, 10, 11};
         int randomIndex = new Random().nextInt(variants.length);
         this.setVariant(variants[randomIndex]);
      } else if (random.nextDouble() > 0.10) {
         int[] variants = {5, 7, 8, 12};
         int randomIndex = new Random().nextInt(variants.length);
         this.setVariant(variants[randomIndex]);
      }

   }

   public void setMarking() {

      if (!(this.getVariant() == 5) || !(this.getVariant() == 7) || !(this.getVariant() == 8) || !(this.getVariant() == 12)) {
         if (random.nextDouble() < 0.10) {
            setOverlayVariant(random.nextInt(OFoxMarkingLayer.Overlay.values().length));
         } else if (random.nextDouble() > 0.10) {
            int[] variants = {1, 2, 3};
            int randomIndex = new Random().nextInt(variants.length);
            this.setOverlayVariant(variants[randomIndex]);
         }
      }

      if (this.getVariant() == 5 || this.getVariant() == 7 || this.getVariant() == 8) {
         if (random.nextDouble() < 0.02) {
            setOverlayVariant(random.nextInt(OFoxMarkingLayer.Overlay.values().length));
         } else if (random.nextDouble() > 0.02) {
            int[] variants = {1, 2, 3};
            int randomIndex = new Random().nextInt(variants.length);
            this.setOverlayVariant(variants[randomIndex]);
         }
      }

      if (this.getVariant() == 12) {
         if (random.nextDouble() < 0.02) {
            setOverlayVariant(random.nextInt(OFoxMarkingLayer.Overlay.values().length));
         } else if (random.nextDouble() > 0.02) {
            int[] variants = {10, 11, 12};
            int randomIndex = new Random().nextInt(variants.length);
            this.setOverlayVariant(variants[randomIndex]);
         }
      }

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

   @Override
   public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
      OFox pup;
      OFox partner = (OFox) ageableMob;
      pup = POEntityTypes.O_FOX_ENTITY.get().create(serverLevel);

      int variantChance = this.random.nextInt(14);
      int variant;
      if (variantChance < 6) {
         variant = this.getVariant();
      } else if (variantChance < 12) {
         variant = partner.getVariant();
      } else {
         variant = this.random.nextInt(OFoxModel.Variant.values().length);
      }
      pup.setVariant(variant);

      int overlayChance = this.random.nextInt(10);
      int overlay;
      if (overlayChance < 4) {
         overlay = this.getOverlayVariant();
      } else if (overlayChance < 8) {
         overlay = partner.getOverlayVariant();
      } else {
         overlay = this.random.nextInt(OFoxMarkingLayer.Overlay.values().length);
      }
      pup.setOverlayVariant(overlay);

      int gender;
      gender = this.random.nextInt(OFox.Gender.values().length);
      pup.setGender(gender);

      return pup;
   }

   public boolean canBeLeashed(Player p_30396_) {
      return super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public class FoxEatBerriesGoal extends MoveToBlockGoal {
      public static final int WAIT_TICKS = 40;
      public int ticksWaited;

      public FoxEatBerriesGoal(double p_28675_, int p_28676_, int p_28677_) {
         super(OFox.this, p_28675_, p_28676_, p_28677_);
      }

      public double acceptedDistance() {
         return 2.0D;
      }

      public boolean shouldRecalculatePath() {
         return this.tryTicks % 100 == 0;
      }

      public boolean isValidTarget(LevelReader p_28680_, BlockPos p_28681_) {
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

      public void onReachedTarget() {
         if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(OFox.this.level(), OFox.this)) {
            BlockState blockstate = OFox.this.level().getBlockState(this.blockPos);
            if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
               this.pickSweetBerries(blockstate);
            } else if (CaveVines.hasGlowBerries(blockstate)) {
               this.pickGlowBerry(blockstate);
            }

         }
      }

      public void pickGlowBerry(BlockState p_148927_) {
         CaveVines.use(OFox.this, p_148927_, OFox.this.level(), this.blockPos);
      }

      public void pickSweetBerries(BlockState p_148929_) {
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