package com.dragn0007.dragnpets.entities.ocelot;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.ai.OcelotFollowOwnerGoal;
import com.dragn0007.dragnpets.util.POTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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

public class OOcelot extends TamableAnimal implements GeoEntity {

   public OOcelot(EntityType<? extends OOcelot> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
      this.reassessTrustingGoals();
   }

   @javax.annotation.Nullable
   public OOcelot.OcelotAvoidEntityGoal<Player> ocelotAvoidPlayersGoal;
   @Nullable
   public OOcelot.OcelotTemptGoal temptGoal;

   public static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_ocelot");
   public static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/ocelot");
   @Override
   public @NotNull ResourceLocation getDefaultLootTable() {
      if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
         return VANILLA_LOOT_TABLE;
      }
      return LOOT_TABLE;
   }

   public void reassessTrustingGoals() {
      if (this.ocelotAvoidPlayersGoal == null) {
         this.ocelotAvoidPlayersGoal = new OOcelot.OcelotAvoidEntityGoal<>(this, Player.class, 16.0F, 0.8D, 1.33D);
      }

      this.goalSelector.removeGoal(this.ocelotAvoidPlayersGoal);
      this.goalSelector.addGoal(4, this.ocelotAvoidPlayersGoal);

   }

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(OOcelot.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(OOcelot.class, EntityDataSerializers.INT);
   public static final Predicate<LivingEntity> PREY_SELECTOR = (entity) -> {
      EntityType<?> entitytype = entity.getType();
      return entitytype == EntityTypes.O_RABBIT_ENTITY.get() ||
              entitytype == EntityTypes.O_CHICKEN_ENTITY.get() ||
              entitytype == EntityTypes.O_FROG_ENTITY.get() ||
              entitytype == POEntityTypes.MACAW_ENTITY.get() ||
              entitytype == POEntityTypes.COCKATIEL_ENTITY.get() ||
              entitytype == POEntityTypes.RINGNECK_ENTITY.get()
              ;
   };

   public void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.temptGoal = new OOcelot.OcelotTemptGoal(this, 0.6D, FOOD_ITEMS, true);
      this.goalSelector.addGoal(3, this.temptGoal);
      this.goalSelector.addGoal(8, new OcelotAttackGoal(this));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5D, true));
      this.goalSelector.addGoal(6, new OcelotFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MAX_HEALTH, 10.0D).
              add(Attributes.MOVEMENT_SPEED, (double)0.3F)
              .add(Attributes.ATTACK_DAMAGE, 3.0D);
   }

   public final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   public <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
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

   public void customServerAiStep() {
      if (this.getMoveControl().hasWanted()) {
         double d0 = this.getMoveControl().getSpeedModifier();
         if (d0 == 0.6D) {
            this.setPose(Pose.CROUCHING);
            this.setSprinting(false);
         } else if (d0 == 1.33D) {
            this.setPose(Pose.STANDING);
            this.setSprinting(true);
         } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
         }
      } else {
         this.setPose(Pose.STANDING);
         this.setSprinting(false);
      }

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
   public float getStepHeight() {
      return 2F;
   }

   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public boolean hurt(DamageSource damageSource, float v) {
      if (damageSource.is(DamageTypes.FALL)) {
         return false;
      }
      return super.hurt(damageSource, v);
   }

   @javax.annotation.Nullable
   public SoundEvent getAmbientSound() {
      return SoundEvents.OCELOT_AMBIENT;
   }

   public int getAmbientSoundInterval() {
      return 900;
   }

   public SoundEvent getHurtSound(DamageSource p_29035_) {
      return SoundEvents.OCELOT_HURT;
   }

   public SoundEvent getDeathSound() {
      return SoundEvents.OCELOT_DEATH;
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
         boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(POTags.Items.CAT_FOOD) && !this.isTame();
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
               DyeItem dyeitem = (DyeItem)item;
               if (this.isOwnedBy(player)) {
                  DyeColor dyecolor = dyeitem.getDyeColor();
                  if (dyecolor != this.getCollarColor()) {
                     this.setCollarColor(dyecolor);
                     if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                     }

                     return InteractionResult.SUCCESS;
                  }

                  return super.mobInteract(player, hand);
               }
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
      } else if (itemstack.is(POTags.Items.CAT_FOOD)) {
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

   public static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.CAT_FOOD);

   @Override
   public boolean isFood(ItemStack itemStack) {
      return FOOD_ITEMS.test(itemStack);
   }

   public DyeColor getCollarColor() {
      return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
   }

   public void setCollarColor(DyeColor p_30398_) {
      this.entityData.set(DATA_COLLAR_COLOR, p_30398_.getId());
   }


   // Generates the base texture
   public ResourceLocation getTextureResource() {
      return OOcelotModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }

   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OOcelot.class, EntityDataSerializers.INT);

   public int getVariant() {
      return this.entityData.get(VARIANT);
   }

   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
      this.entityData.define(VARIANT, 0);
      this.entityData.define(GENDER, 0);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putInt("Variant", getVariant());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
   }

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);
      if (tag.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(tag.getInt("CollarColor")));
      }

      if (tag.contains("Variant")) {
         setVariant(tag.getInt("Variant"));
      }

      if (tag.contains("Gender")) {
         this.setGender(tag.getInt("Gender"));
      }

      if (tag.contains("Wandering")) {
         this.setToldToWander(tag.getBoolean("Wandering"));
      }
   }

   @Override
   @javax.annotation.Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setVariant(random.nextInt(OOcelotModel.Variant.values().length));
      setGender(random.nextInt(OOcelot.Gender.values().length));

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

   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OOcelot.class, EntityDataSerializers.INT);

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
      } else if (!(animal instanceof OOcelot)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((OOcelot) animal).canParent();
         } else {
            OOcelot partner = (OOcelot) animal;
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
      OOcelot oOcelot = (OOcelot) ageableMob;
      if (ageableMob instanceof OOcelot) {
         OOcelot ocelot = (OOcelot) ageableMob;
         oOcelot = POEntityTypes.O_OCELOT_ENTITY.get().create(serverLevel);

         int i = this.random.nextInt(9);
         int variant;
         if (i < 4) {
            variant = this.getVariant();
         } else if (i < 8) {
            variant = ocelot.getVariant();
         } else {
            variant = this.random.nextInt(OOcelotModel.Variant.values().length);
         }

         int gender;
         gender = this.random.nextInt(OOcelot.Gender.values().length);

         oOcelot.setVariant(variant);
         oOcelot.setGender(gender);
      }

      return oOcelot;
   }


   public boolean wantsToAttack(LivingEntity entity, LivingEntity p_30390_) {
      if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
         if (entity instanceof OOcelot) {
            OOcelot wolf = (OOcelot)entity;
            return !wolf.isTame() || wolf.getOwner() != p_30390_;
         } else if (entity instanceof Player && p_30390_ instanceof Player && !((Player)p_30390_).canHarmPlayer((Player)entity)) {
            return false;
         } else if (entity instanceof AbstractHorse && ((AbstractHorse)entity).isTamed()) {
            return false;
         } else {
            return !(entity instanceof TamableAnimal) || !((TamableAnimal)entity).isTame();
         }
      } else {
         return false;
      }
   }

   public boolean canBeLeashed(Player p_30396_) {
      return super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public void handleEntityEvent(byte p_28995_) {
      if (p_28995_ == 41) {
         this.spawnTrustingParticles(true);
      } else if (p_28995_ == 40) {
         this.spawnTrustingParticles(false);
      } else {
         super.handleEntityEvent(p_28995_);
      }

   }

   public void spawnTrustingParticles(boolean p_29048_) {
      ParticleOptions particleoptions = ParticleTypes.HEART;
      if (!p_29048_) {
         particleoptions = ParticleTypes.SMOKE;
      }

      for(int i = 0; i < 7; ++i) {
         double d0 = this.random.nextGaussian() * 0.02D;
         double d1 = this.random.nextGaussian() * 0.02D;
         double d2 = this.random.nextGaussian() * 0.02D;
         this.level().addParticle(particleoptions, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), d0, d1, d2);
      }

   }

   public boolean isSteppingCarefully() {
      return this.isCrouching() || super.isSteppingCarefully();
   }

   static class OcelotAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
      public final OOcelot ocelot;

      public OcelotAvoidEntityGoal(OOcelot p_29051_, Class<T> p_29052_, float p_29053_, double p_29054_, double p_29055_) {
         super(p_29051_, p_29052_, p_29053_, p_29054_, p_29055_, EntitySelector.NO_CREATIVE_OR_SPECTATOR::test);
         this.ocelot = p_29051_;
      }

      public boolean canUse() {
         return super.canUse();
      }

      public boolean canContinueToUse() {
         return super.canContinueToUse();
      }
   }

   static class OcelotTemptGoal extends TemptGoal {
      public final OOcelot ocelot;

      public OcelotTemptGoal(OOcelot p_29060_, double p_29061_, Ingredient p_29062_, boolean p_29063_) {
         super(p_29060_, p_29061_, p_29062_, p_29063_);
         this.ocelot = p_29060_;
      }

      public boolean canScare() {
         return super.canScare();
      }
   }
}