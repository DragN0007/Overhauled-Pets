package com.dragn0007.dragnpets.entities.cat.kornish_rex;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.ai.CatFollowOwnerGoal;
import com.dragn0007.dragnpets.entities.cat.CatMarkingOverlay;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.cat.OCatEyeLayer;
import com.dragn0007.dragnpets.entities.cat.OCatModel;
import com.dragn0007.dragnpets.items.POItems;
import com.dragn0007.dragnpets.util.POTags;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.animal.Animal;
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

public class KornishRex extends OCat implements GeoEntity {

   public KornishRex(EntityType<? extends KornishRex> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
      this.reassessTrustingGoals();
   }

   @Nullable
   public KornishRex.OcelotAvoidEntityGoal<Player> ocelotAvoidPlayersGoal;
   @Nullable
   public KornishRex.OcelotTemptGoal temptGoal;

   public static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_ocelot");
   public static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/cat");
   @Override
   public @NotNull ResourceLocation getDefaultLootTable() {
      if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
         return VANILLA_LOOT_TABLE;
      }
      return LOOT_TABLE;
   }

   public void reassessTrustingGoals() {
      if (this.ocelotAvoidPlayersGoal == null) {
         this.ocelotAvoidPlayersGoal = new KornishRex.OcelotAvoidEntityGoal<>(this, Player.class, 16.0F, 0.8D, 1.33D);
      }

      this.goalSelector.removeGoal(this.ocelotAvoidPlayersGoal);
      this.goalSelector.addGoal(4, this.ocelotAvoidPlayersGoal);

   }

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);
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
      this.temptGoal = new KornishRex.OcelotTemptGoal(this, 0.6D, FOOD_ITEMS, true);
      this.goalSelector.addGoal(3, this.temptGoal);
      this.goalSelector.addGoal(8, new OcelotAttackGoal(this));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5D, true));
      this.goalSelector.addGoal(6, new CatFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));

      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 2, true, false,
              entity -> entity.getType().is(POTags.Entity_Types.CATS_HUNT) && ((entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame()) || (!this.isTame()) || (this.isTame() && this.wasToldToWander() && entity instanceof TamableAnimal && !((TamableAnimal) entity).isTame())))  {
      });
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MAX_HEALTH, 6.0D).
              add(Attributes.MOVEMENT_SPEED, (double)0.3F)
              .add(Attributes.ATTACK_DAMAGE, 3.0D);
   }

   public final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   public <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
      double currentSpeed = this.getDeltaMovement().lengthSqr();
      double speedThreshold = 0.02;

      AnimationController<T> controller = tAnimationState.getController();

      if (tAnimationState.isMoving()) {
         if (currentSpeed > speedThreshold) {
            controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.5);
         } else {
            controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.5);
         }
      } else {
         if (isInSittingPose()) {
            controller.setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.0);
         } else if (this.isWagging()) {
            controller.setAnimation(RawAnimation.begin().then("flick_tail", Animation.LoopType.LOOP));
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

   @Override
   public float getStepHeight() {
      return 1F;
   }

   @Nullable
   public SoundEvent getAmbientSound() {
      if (this.isTame()) {
         if (this.isInLove()) {
            return SoundEvents.CAT_PURR;
         } else {
            return this.random.nextInt(4) == 0 ? SoundEvents.CAT_PURREOW : SoundEvents.CAT_AMBIENT;
         }
      } else {
         return SoundEvents.CAT_STRAY_AMBIENT;
      }
   }

   public int getAmbientSoundInterval() {
      return 120;
   }

   public SoundEvent getHurtSound(DamageSource p_28160_) {
      return SoundEvents.CAT_HURT;
   }

   public SoundEvent getDeathSound() {
      return SoundEvents.CAT_DEATH;
   }

   public float getAttackDamage() {
      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
   }

   public boolean doHurtTarget(Entity p_28990_) {
      return p_28990_.hurt(this.damageSources().mobAttack(this), this.getAttackDamage());
   }

   @Override
   public boolean hurt(DamageSource damageSource, float v) {
      if (damageSource.is(DamageTypes.FALL)) {
         return false;
      }
      return super.hurt(damageSource, v);
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

   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);

   public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> EYES = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Boolean> COLLARED = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.BOOLEAN);

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY, 0);
      this.entityData.define(EYES, 0);
      this.entityData.define(GENDER, 0);
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
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

      if (tag.contains("Eyes")) {
         setEyes(tag.getInt("Eyes"));
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
      tag.putInt("Eyes", getEyes());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putBoolean("Collared", this.isCollared());
   }

   @Override
   @Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();

      if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
         this.setEyeColor();
      } else {
         setEyes(random.nextInt(OCatEyeLayer.Eyes.values().length));
      }

      setVariant(random.nextInt(OCatModel.Variant.values().length));
      setOverlayVariant(random.nextInt(CatMarkingOverlay.values().length));
      setGender(random.nextInt(OCat.Gender.values().length));

      return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
   }

   public void setEyeColor() {

      //white and cream cats have a better chance of gaining blue or green eyes
      if (this.getVariant() == 4 || this.getVariant() == 14 || this.getOverlayVariant() == 85) {
         if (random.nextDouble() < 0.10) {
            this.setEyes(5); //green
         } else if (random.nextDouble() < 0.30 && random.nextDouble() > 0.10) {
            this.setEyes(4); //blue
         } else if (random.nextDouble() > 0.30) {
            this.setEyes(this.getRandom().nextInt(3)); //random (between orange and brown)
         } else {
            this.setEyes(0);
         }
      } else {
         if (random.nextDouble() < 0.03) {
            this.setEyes(5);
         } else if (random.nextDouble() < 0.10 && random.nextDouble() > 0.03) {
            this.setEyes(4);
         } else if (random.nextDouble() > 0.10) {
            this.setEyes(this.getRandom().nextInt(3));
         } else {
            this.setEyes(0);
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
   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(KornishRex.class, EntityDataSerializers.INT);
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
      } else if (!(animal instanceof OCat)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((OCat) animal).canParent();
         } else {
            OCat partner = (OCat) animal;
            if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
               return isFemale();
            }
         }
      }
      return false;
   }

   @Override
   public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
      KornishRex kitten;
      KornishRex partner = (KornishRex) ageableMob;
      kitten = POEntityTypes.KORNISH_REX_ENTITY.get().create(serverLevel);

      int variantChance = this.random.nextInt(14);
      int variant;
      if (variantChance < 6) {
         variant = this.getVariant();
      } else if (variantChance < 12) {
         variant = partner.getVariant();
      } else {
         variant = this.random.nextInt(OCatModel.Variant.values().length);
      }
      kitten.setVariant(variant);

      int overlayChance = this.random.nextInt(10);
      int overlay;
      if (overlayChance < 4) {
         overlay = this.getOverlayVariant();
      } else if (overlayChance < 8) {
         overlay = partner.getOverlayVariant();
      } else {
         overlay = this.random.nextInt(CatMarkingOverlay.values().length);
      }
      kitten.setOverlayVariant(overlay);

      int eyeChance = this.random.nextInt(10);
      int eyes;
      if (eyeChance < 4) {
         eyes = this.getEyes();
      } else if (eyeChance < 8) {
         eyes = partner.getEyes();
      } else {
         eyes = this.random.nextInt(OCatEyeLayer.Eyes.values().length);
      }
      kitten.setEyes(eyes);

      int gender;
      gender = this.random.nextInt(OCat.Gender.values().length);
      kitten.setGender(gender);

      return kitten;
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
      public final KornishRex ocelot;

      public OcelotAvoidEntityGoal(KornishRex p_29051_, Class<T> p_29052_, float p_29053_, double p_29054_, double p_29055_) {
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
      public final KornishRex ocelot;

      public OcelotTemptGoal(KornishRex p_29060_, double p_29061_, Ingredient p_29062_, boolean p_29063_) {
         super(p_29060_, p_29061_, p_29062_, p_29063_);
         this.ocelot = p_29060_;
      }

      public boolean canScare() {
         return super.canScare();
      }
   }
}