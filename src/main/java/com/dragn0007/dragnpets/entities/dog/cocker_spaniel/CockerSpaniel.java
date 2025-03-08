package com.dragn0007.dragnpets.entities.dog.cocker_spaniel;

import com.dragn0007.dragnlivestock.entities.chicken.OChicken;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.ai.DogFollowOwnerGoal;
import com.dragn0007.dragnpets.entities.ai.DogFollowPackLeaderGoal;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.fox.OFox;
import com.dragn0007.dragnpets.entities.fox.OFoxMarkingLayer;
import com.dragn0007.dragnpets.gui.LabradorMenu;
import com.dragn0007.dragnpets.util.POTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
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

public class CockerSpaniel extends ODog implements InventoryCarrier, NeutralMob, GeoEntity, ContainerListener {

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(CockerSpaniel.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(CockerSpaniel.class, EntityDataSerializers.INT);

   public static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
   @Nullable
   public UUID persistentAngerTarget;

   public CockerSpaniel(EntityType<? extends CockerSpaniel> entityType, Level level) {
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

      this.goalSelector.addGoal(7, new SpanielSearchForItemsGoal());

      this.goalSelector.addGoal(6, new DogFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));

      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, OChicken.class, 2, true, false,
              entity -> entity instanceof OChicken && (this.isTame() && this.wasToldToHunt())));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MOVEMENT_SPEED, 0.28F)
              .add(Attributes.MAX_HEALTH, 8.0D)
              .add(Attributes.ATTACK_DAMAGE, 1.0D);
   }

   public final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   public <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
      double currentSpeed = this.getDeltaMovement().lengthSqr();
      double speedThreshold = 0.015;

      AnimationController<T> controller = tAnimationState.getController();

      if (tAnimationState.isMoving()) {
         if (currentSpeed > speedThreshold) {
            controller.setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.5);
         } else {
            controller.setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.4);
         }
      } else {
         if (isInSittingPose()) {
            controller.setAnimation(RawAnimation.begin().then("sit2", Animation.LoopType.LOOP));
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
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(7.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(1.0D);
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
   public ResourceLocation getTextureResource() {
      return CockerSpanielModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }

   public ResourceLocation getOverlayResource() {
      return CockerSpanielMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
   }

   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(CockerSpaniel.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> OVERLAY_VARIANT = SynchedEntityData.defineId(CockerSpaniel.class, EntityDataSerializers.INT);

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

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_COLLAR_COLOR, DyeColor.RED.getId());
      this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY_VARIANT, 0);
      this.entityData.define(GENDER, 0);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putInt("Variant", getVariant());
      tag.putInt("Overlay", getOverlayVariant());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
      tag.putBoolean("Panicking", this.getPanicking());
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

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);
      if (tag.contains("CollarColor", 99)) {
         this.setCollarColor(DyeColor.byId(tag.getInt("CollarColor")));
      }

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

      if (tag.contains("Panicking")) {
         this.setPanicking(tag.getBoolean("Panicking"));
      }

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

      this.readPersistentAngerSaveData(this.level(), tag);

      this.setCanPickUpLoot(true);
   }

   @Override
   @javax.annotation.Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setVariant(random.nextInt(CockerSpanielModel.Variant.values().length));
      setOverlayVariant(random.nextInt(CockerSpanielMarkingLayer.Overlay.values().length));
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

   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(CockerSpaniel.class, EntityDataSerializers.INT);

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
      } else if (!(animal instanceof CockerSpaniel)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((CockerSpaniel) animal).canParent();
         } else {
            CockerSpaniel partner = (CockerSpaniel) animal;
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
      CockerSpaniel oWolf1 = (CockerSpaniel) ageableMob;
      if (ageableMob instanceof CockerSpaniel) {
         CockerSpaniel oWolf = (CockerSpaniel) ageableMob;
         oWolf1 = EntityTypes.COCKER_SPANIEL_ENTITY.get().create(serverLevel);

         int i = this.random.nextInt(9);
         int variant;
         if (i < 4) {
            variant = this.getVariant();
         } else if (i < 8) {
            variant = oWolf.getVariant();
         } else {
            variant = this.random.nextInt(CockerSpanielModel.Variant.values().length);
         }

         int j = this.random.nextInt(9);
         int overlay;
         if (j < 4) {
            overlay = this.getOverlayVariant();
         } else if (j < 8) {
            overlay = oWolf.getOverlayVariant();
         } else {
            overlay = this.random.nextInt(CockerSpanielMarkingLayer.Overlay.values().length);
         }

         int gender;
         gender = this.random.nextInt(Gender.values().length);

         oWolf1.setVariant(variant);
         oWolf1.setOverlayVariant(overlay);
         oWolf1.setGender(gender);
      }

      return oWolf1;
   }

   public boolean wantsToAttack(LivingEntity entity, LivingEntity p_30390_) {
      if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
         if (entity instanceof CockerSpaniel) {
            CockerSpaniel wolf = (CockerSpaniel)entity;
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

   static final Predicate<ItemEntity> ANIMAL_LOOT = (itemEntity) -> {
      return !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.getItem().is(POTags.Items.ANIMAL_LOOT);
   };

   //modified fox pickup goal
   public class SpanielSearchForItemsGoal extends Goal {

      public SpanielSearchForItemsGoal() {
         this.setFlags(EnumSet.of(Flag.MOVE));
      }

      public boolean canUse() {
         if (isOrderedToSit()) {
            return false;
         } else if (isInventoryFull()) {
            return false;
         } else if (CockerSpaniel.this.getTarget() == null && CockerSpaniel.this.getLastHurtByMob() == null) {
            if (CockerSpaniel.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
               return false;
            } else {
               List<ItemEntity> list = CockerSpaniel.this.level().getEntitiesOfClass(ItemEntity.class, CockerSpaniel.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CockerSpaniel.ANIMAL_LOOT);
               return !list.isEmpty();
            }
         } else {
            return false;
         }
      }

      @Override
      public void tick() {
         List<ItemEntity> itemEntities = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CockerSpaniel.ANIMAL_LOOT);

         if (!itemEntities.isEmpty() && !isInventoryFull()) {
            ItemEntity itemEntity = itemEntities.get(0);
            getNavigation().moveTo(itemEntity, 1.2D);

            if (distanceToSqr(itemEntity) < 2.0D && itemEntity.getItem().is(POTags.Items.ANIMAL_LOOT)) {
               pickUpItem(itemEntity);
            }
         }
      }

      @Override
      public void start() {
         List<ItemEntity> itemEntities = level().getEntitiesOfClass(ItemEntity.class, getBoundingBox().inflate(8.0D, 8.0D, 8.0D), CockerSpaniel.ANIMAL_LOOT);
         if (!itemEntities.isEmpty()) {
            getNavigation().moveTo(itemEntities.get(0), 1.2D);
         }
      }

      private void pickUpItem(ItemEntity itemEntity) {
         if (!isInventoryFull() && itemEntity.getItem().is(POTags.Items.ANIMAL_LOOT) && this.canUse()) {
               ItemStack itemStack = itemEntity.getItem();

               for (int i = 0; i < getInventory().getContainerSize(); i++) {
                  ItemStack inventoryStack = getInventory().getItem(i);

                  if (!inventoryStack.isEmpty() && inventoryStack.is(itemStack.getItem()) && inventoryStack.getCount() < inventoryStack.getMaxStackSize() && itemStack.is(POTags.Items.ANIMAL_LOOT)) {
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

               if (!itemStack.isEmpty() && itemStack.is(POTags.Items.ANIMAL_LOOT) && this.canUse()) {
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