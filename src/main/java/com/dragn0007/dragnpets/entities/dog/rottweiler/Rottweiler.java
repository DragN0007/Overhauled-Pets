package com.dragn0007.dragnpets.entities.dog.rottweiler;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.ai.DogFollowOwnerGoal;
import com.dragn0007.dragnpets.entities.ai.DogFollowPackLeaderGoal;
import com.dragn0007.dragnpets.entities.dog.CommonDogModel;
import com.dragn0007.dragnpets.entities.dog.DogMarkingOverlay;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.gui.LabradorMenu;
import com.dragn0007.dragnpets.util.POTags;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class Rottweiler extends ODog implements InventoryCarrier, NeutralMob, GeoEntity, ContainerListener {

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);

   public static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
   @Nullable
   public UUID persistentAngerTarget;

   public Rottweiler(EntityType<? extends Rottweiler> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
      this.updateInventory();
      this.setCanPickUpLoot(true);
      this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
      this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.goalSelector.addGoal(1, new WolfPanicGoal(1.4D));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.5D, true));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
      this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
      this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
      this.goalSelector.addGoal(7, new DogFollowPackLeaderGoal(this));

      this.goalSelector.addGoal(7, new RottweilerSearchForItemsGoal());

      this.goalSelector.addGoal(6, new DogFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));

      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, 2, true, false,
              entity -> entity instanceof Monster && this.isTame() && this.wasToldToGuard()));

      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Slime.class, 2, true, false,
              entity -> entity instanceof Slime && this.isTame() && this.wasToldToGuard()));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MOVEMENT_SPEED, 0.26F)
              .add(Attributes.MAX_HEALTH, 16.0D)
              .add(Attributes.ATTACK_DAMAGE, 4.5D);
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
            controller.setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.0);
         } else if (this.isWagging()) {
            controller.setAnimation(RawAnimation.begin().then("wag", Animation.LoopType.LOOP));
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

   @Override
   public float getStepHeight() {
      return 1F;
   }

   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);

      if (this.isOwnedBy(player) && this.isTame() && !this.isBaby()) {
         if (this.isTame() && player.isSecondaryUseActive() && this.isOrderedToSit()) {
            this.openInventory(player);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }
      }
      return super.mobInteract(player, hand);
   }

   @Override
   public void tick() {
      super.tick();
      if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
         List<? extends ODog> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(20.0D, 20.0D, 20.0D));
         if (list.size() <= 1) {
            this.packSize = 1;
         }
      }

      regenHealthCounter++;

      if (this.getHealth() < this.getMaxHealth() && regenHealthCounter >= 75 && this.isTame() && this.isAlive() && this.wasToldToGuard()) {
         this.setHealth(this.getHealth() + 2);
         regenHealthCounter = 0;
         this.level().addParticle(ParticleTypes.HEART, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
      }

   }

   public boolean hurt(DamageSource damageSource, float amount) {
      if (damageSource.getEntity() instanceof Player player) {

         if (!this.level().isClientSide && this.isTame() && !this.isOrderedToSit() && !this.isInSittingPose() && !this.wasToldToGuard()) {
            if (this.isOwnedBy(player) && player.isShiftKeyDown()) {
               this.setToldToGuard(true);
               player.displayClientMessage(Component.translatable("tooltip.dragnpets.guarding.tooltip").withStyle(ChatFormatting.GOLD), true);
            }
            return false;
         }

         if (!this.level().isClientSide && this.isTame() && !this.isOrderedToSit() && !this.isInSittingPose() && this.wasToldToGuard()) {
            if (this.isOwnedBy(player) && player.isShiftKeyDown()) {
               this.setToldToGuard(false);
               player.displayClientMessage(Component.translatable("tooltip.dragnpets.not_guarding.tooltip").withStyle(ChatFormatting.GOLD), true);
            }
            return false;
         }

         if (this.isInvulnerableTo(damageSource)) {
            return false;
         } else {
            Entity entity = damageSource.getEntity();
            if (!this.level().isClientSide) {
               this.setOrderedToSit(false);
            }

            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
               amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(damageSource, amount);
         }
      }

      if (this.isInvulnerableTo(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         if (!this.level().isClientSide) {
            this.setOrderedToSit(false);
         }

         if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            amount = (amount + 1.0F) / 2.0F;
         }
         return super.hurt(damageSource, amount);
      }
   }

   public void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
      this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
   }

   public SoundEvent getAmbientSound() {
      if (this.isAngry()) {
         return SoundEvents.WOLF_GROWL;
      } else if (this.random.nextInt(3) == 0) {
         return this.isTame() && this.getHealth() < 10.0F ? SoundEvents.WOLF_WHINE : SoundEvents.WOLF_PANT;
      } else {
         return SoundEvents.WOLF_AMBIENT;
      }
   }

   public SoundEvent getHurtSound(DamageSource p_30424_) {
      return SoundEvents.WOLF_HURT;
   }

   public SoundEvent getDeathSound() {
      return SoundEvents.WOLF_DEATH;
   }

   public float getSoundVolume() {
      return 0.4F;
   }

   public void aiStep() {
      super.aiStep();

      if (!this.level().isClientSide) {
         this.updatePersistentAnger((ServerLevel)this.level(), true);
      }

   }

   public boolean doHurtTarget(Entity p_30372_) {
      boolean flag = p_30372_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
      if (flag) {
         this.doEnchantDamageEffects(this, p_30372_);
      }

      return flag;
   }

   public void setTame(boolean p_30443_) {
      super.setTame(p_30443_);
      if (p_30443_) {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(18.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(7.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.5D);
   }

   public int getRemainingPersistentAngerTime() {
      return this.entityData.get(DATA_REMAINING_ANGER_TIME);
   }

   public void setRemainingPersistentAngerTime(int p_30404_) {
      this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
   }

   public void startPersistentAngerTimer() {
      this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
   }

   @Nullable
   public UUID getPersistentAngerTarget() {
      return this.persistentAngerTarget;
   }

   public void setPersistentAngerTarget(@Nullable UUID p_30400_) {
      this.persistentAngerTarget = p_30400_;
   }

   public DyeColor getCollarColor() {
      return DyeColor.byId(this.entityData.get(DATA_COLLAR_COLOR));
   }

   public void setCollarColor(DyeColor p_30398_) {
      this.entityData.set(DATA_COLLAR_COLOR, p_30398_.getId());
   }

   protected void pickUpItem(ItemEntity p_35467_) {
      InventoryCarrier.pickUpItem(this, this, p_35467_);
   }

   // Generates the base texture
   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Boolean> COLLARED = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.BOOLEAN);
   public static final EntityDataAccessor<Integer> CROPPED = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> FLUFFY = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_VEST_COLOR = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Boolean> VEST = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.BOOLEAN);

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY, 0);
      this.entityData.define(GENDER, 0);
      this.entityData.define(CROPPED, 0);
      this.entityData.define(FLUFFY, 0);
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
      this.entityData.define(COLLARED, false);
      this.entityData.define(DATA_VEST_COLOR, DyeColor.RED.getId());
      this.entityData.define(VEST, false);
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

      if (tag.contains("Fluff")) {
         this.setFluff(tag.getInt("Fluff"));
      }

      if (tag.contains("Cropped")) {
         this.setCropped(tag.getInt("Cropped"));
      }

      if (tag.contains("Wandering")) {
         this.setToldToWander(tag.getBoolean("Wandering"));
      }

      if (tag.contains("Panicking")) {
         this.setPanicking(tag.getBoolean("Panicking"));
      }

      if (tag.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(tag.getInt("CollarColor")));
      }

      if(tag.contains("Collared")) {
         this.setCollared(tag.getBoolean("Collared"));
      }

      if (tag.contains("VestColor", 99)) {
         this.setVestColor(DyeColor.byId(tag.getInt("VestColor")));
      }

      if(tag.contains("Vest")) {
         this.setVest(tag.getBoolean("Vest"));
      }

      this.readPersistentAngerSaveData(this.level(), tag);

      this.updateInventory();

      if(this.isTame()) {
         ListTag listTag = tag.getList("Items", 10);

         for(int i = 0; i < listTag.size(); i++) {
            CompoundTag compoundTag = listTag.getCompound(i);
            int j = compoundTag.getByte("Slot") & 255;
            if(j < this.inventory.getContainerSize()) {
               this.inventory.setItem(j, ItemStack.of(compoundTag));
            }
         }
      }

      this.setCanPickUpLoot(true);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putInt("Variant", getVariant());
      tag.putInt("Overlay", getOverlayVariant());
      tag.putInt("Gender", this.getGender());
      tag.putInt("Cropped", this.getCropped());
      tag.putInt("Fluff", this.getFluff());
      tag.putBoolean("Wandering", this.getToldToWander());
      tag.putBoolean("Panicking", this.getPanicking());
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putBoolean("Collared", this.isCollared());
      tag.putByte("VestColor", (byte)this.getVestColor().getId());
      tag.putBoolean("Vest", this.hasVest());
      this.addPersistentAngerSaveData(tag);

      if(this.isTame()) {
         ListTag listTag = new ListTag();

         for(int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack itemStack = this.inventory.getItem(i);
            if(!itemStack.isEmpty()) {
               CompoundTag compoundTag = new CompoundTag();
               compoundTag.putByte("Slot", (byte) i);
               itemStack.save(compoundTag);
               listTag.add(compoundTag);
            }
         }
         tag.put("Items", listTag);
      }
   }

   @Override
   @javax.annotation.Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();

      if (LivestockOverhaulCommonConfig.SPAWN_BY_BREED.get()) {
         this.setColor();
         this.setMarking();
      } else {
         this.setVariant(random.nextInt(CommonDogModel.Variant.values().length));
         this.setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
      }

      setGender(random.nextInt(ODog.Gender.values().length));

      this.setFluffChance();

      if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()) {
         setCropChance();
      } else {
         setCropped(0);
      }

      return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
   }

   public void setCropChance() {
      if (random.nextDouble() <= 0.02) {
         this.setCropped(3); // full crop
      } else if (random.nextDouble() > 0.02 && random.nextDouble() < 0.07) {
         this.setCropped(1); // ears only
      } else if (random.nextDouble() > 0.07 && random.nextDouble() < 0.55) {
         this.setCropped(2); // tail only
      } else {
         this.setCropped(0); // no crop
      }
   }

   public void setFluffChance() {
      if (random.nextDouble() <= 0.02) {
         this.setFluff(1);
      } else {
         this.setFluff(0);
      }
   }

   public void setColor() {
      if (random.nextDouble() < 0.07) {
         setVariant(random.nextInt(CommonDogModel.Variant.values().length));
      } else if (random.nextDouble() > 0.07) {
         int[] variants = {0, 10};
         int randomIndex = new Random().nextInt(variants.length);
         this.setVariant(variants[randomIndex]);
      }
   }

   public void setMarking() {
      if (random.nextDouble() < 0.05) {
         setOverlayVariant(random.nextInt(DogMarkingOverlay.values().length));
      } else if (random.nextDouble() > 0.05 && random.nextDouble() < 0.20) {
         this.setOverlayVariant(0);
      } else if (random.nextDouble() > 0.20) {
         this.setOverlayVariant(4);
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
   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(Rottweiler.class, EntityDataSerializers.INT);
   public int getGender() {
      return this.entityData.get(GENDER);
   }
   public void setGender(int gender) {
      this.entityData.set(GENDER, gender);
   }

   public boolean canParent() {
      return !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
   }

   @Override
   public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {

      ODog pup = null;
      Rottweiler partner = (Rottweiler) ageableMob;

      if (ageableMob instanceof Rottweiler) {
         pup = POEntityTypes.ROTTWEILER_ENTITY.get().create(serverLevel);

         int variantChance = this.random.nextInt(14);
         int variant;
         if (variantChance < 6) {
            variant = this.getVariant();
         } else if (variantChance < 12) {
            variant = partner.getVariant();
         } else {
            variant = this.random.nextInt(CommonDogModel.Variant.values().length);
         }
         pup.setVariant(variant);

         int overlayChance = this.random.nextInt(10);
         int overlay;
         if (overlayChance < 4) {
            overlay = this.getOverlayVariant();
         } else if (overlayChance < 8) {
            overlay = partner.getOverlayVariant();
         } else {
            overlay = this.random.nextInt(DogMarkingOverlay.values().length);
         }
         pup.setOverlayVariant(overlay);

         int fluffyChance = this.random.nextInt(10);
         int fluff;
         if (fluffyChance < 5) {
            fluff = this.getFluff();
         } else {
            fluff = partner.getFluff();
         }
         pup.setFluff(fluff);

         int gender;
         gender = this.random.nextInt(ODog.Gender.values().length);
         pup.setGender(gender);

         if (PetsOverhaulCommonConfig.ALLOW_CROPPED_DOG_SPAWNS.get()){
            pup.setCropChance();
         } else {
            pup.setCropped(0);
         }
      }

      return pup;
   }

   public boolean wantsToAttack(LivingEntity entity, LivingEntity p_30390_) {
      if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
         if (entity instanceof Rottweiler) {
            Rottweiler wolf = (Rottweiler)entity;
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

   public boolean canBeLeashed(Player player) {
      return !this.isAngry() && super.canBeLeashed(player);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public SimpleContainer inventory;

   public LazyOptional<?> itemHandler = null;

   public void updateInventory() {
      SimpleContainer tempInventory = this.inventory;
      this.inventory = new SimpleContainer(this.getInventorySize());

      if(tempInventory != null) {
         tempInventory.removeListener(this);
         int maxSize = Math.min(tempInventory.getContainerSize(), this.inventory.getContainerSize());

         for(int i = 0; i < maxSize; i++) {
            ItemStack itemStack = tempInventory.getItem(i);
            if(!itemStack.isEmpty()) {
               this.inventory.setItem(i, itemStack.copy());
            }
         }
      }
      this.inventory.addListener(this);
      this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
   }

   @Override
   public void dropEquipment() {
      if(!this.level().isClientSide) {
         super.dropEquipment();
         Containers.dropContents(this.level(), this, this.inventory);
      }
   }

   @Override
   public void invalidateCaps() {
      super.invalidateCaps();
      if(this.itemHandler != null) {
         LazyOptional<?> oldHandler = this.itemHandler;
         this.itemHandler = null;
         oldHandler.invalidate();
      }
   }

   public int getInventorySize() {
      return 5;
   }

   public SimpleContainer getInventory() {
      return this.inventory;
   }

   public void openInventory(Player player) {
      if(player instanceof ServerPlayer serverPlayer && this.isTame()) {
         NetworkHooks.openScreen(serverPlayer, new SimpleMenuProvider((containerId, inventory, p) -> {
            return new LabradorMenu(containerId, inventory, this.inventory, this);
         }, this.getDisplayName()), (data) -> {
            data.writeInt(this.getInventorySize());
            data.writeInt(this.getId());
         });
      }
   }

   @Override
   public void containerChanged(Container p_18983_) {
      return;
   }

   public boolean isInventoryFull() {
      for (int i = 0; i < inventory.getContainerSize(); i++) {
         if (inventory.getItem(i).isEmpty()) {
            return false;
         }
      }
      return true;
   }

   static final Predicate<ItemEntity> MONSTER_LOOT = (itemEntity) -> {
      return !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.getItem().is(POTags.Items.MONSTER_LOOT);
   };

   public class RottweilerSearchForItemsGoal extends Goal {

      public RottweilerSearchForItemsGoal() {
         this.setFlags(EnumSet.of(Flag.MOVE));
      }

      public boolean canUse() {
         if (isOrderedToSit()) {
            return false;
         } else if (isInventoryFull()) {
            return false;
         } else if (Rottweiler.this.getTarget() == null && Rottweiler.this.getLastHurtByMob() == null) {
            if (Rottweiler.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
               return false;
            } else {
               List<ItemEntity> list = Rottweiler.this.level().getEntitiesOfClass(ItemEntity.class, Rottweiler.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rottweiler.MONSTER_LOOT);
               return !list.isEmpty();
            }
         } else {
            return false;
         }
      }

      @Override
      public void tick() {
         List<ItemEntity> itemEntities = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rottweiler.MONSTER_LOOT);

         if (!itemEntities.isEmpty() && !isInventoryFull()) {
            ItemEntity itemEntity = itemEntities.get(0);
            getNavigation().moveTo(itemEntity, 1.2D);

            if (distanceToSqr(itemEntity) < 2.0D && itemEntity.getItem().is(POTags.Items.MONSTER_LOOT)) {
               pickUpItem(itemEntity);
            }
         }
      }

      @Override
      public void start() {
         List<ItemEntity> itemEntities = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rottweiler.MONSTER_LOOT);
         if (!itemEntities.isEmpty()) {
            getNavigation().moveTo(itemEntities.get(0), 1.2D);
         }
      }

      private void pickUpItem(ItemEntity itemEntity) {
         if (!isInventoryFull() && itemEntity.getItem().is(POTags.Items.MONSTER_LOOT) && this.canUse()) {
               ItemStack itemStack = itemEntity.getItem();

               for (int i = 0; i < getInventory().getContainerSize(); i++) {
                  ItemStack inventoryStack = getInventory().getItem(i);

                  if (!inventoryStack.isEmpty() && inventoryStack.is(itemStack.getItem()) && inventoryStack.getCount() < inventoryStack.getMaxStackSize() && itemStack.is(POTags.Items.MONSTER_LOOT)) {
                     int j = inventoryStack.getMaxStackSize() - inventoryStack.getCount();
                     int k = Math.min(j, itemStack.getCount());
                     inventoryStack.grow(k);
                     itemStack.shrink(k);

                     if (itemStack.isEmpty()) {
                        itemEntity.discard();
                        break;
                     }
                  }
               }

               if (!itemStack.isEmpty() && itemStack.is(POTags.Items.MONSTER_LOOT) && this.canUse()) {
                  for (int i = 0; i < getInventory().getContainerSize(); i++) {
                     if (getInventory().getItem(i).isEmpty()) {
                        getInventory().setItem(i, itemStack);
                        itemEntity.discard();
                        break;
                     }
                  }
               }
            }
         }
      }

}