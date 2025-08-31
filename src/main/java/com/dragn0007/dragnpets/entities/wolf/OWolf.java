package com.dragn0007.dragnpets.entities.wolf;

import com.dragn0007.dragnlivestock.entities.EntityTypes;
import com.dragn0007.dragnlivestock.entities.donkey.ODonkey;
import com.dragn0007.dragnlivestock.entities.llama.OLlama;
import com.dragn0007.dragnlivestock.entities.mule.OMule;
import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.entities.ai.CanineFollowPackLeaderGoal;
import com.dragn0007.dragnpets.entities.ai.WolfFollowOwnerGoal;
import com.dragn0007.dragnpets.items.custom.DogArmorItem;
import com.dragn0007.dragnpets.util.POTags;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
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
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
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

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OWolf extends TamableAnimal implements NeutralMob, GeoEntity {

   public static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_wolf");
   public static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/wolf");
   private static final ResourceLocation TFC_LOOT_TABLE = new ResourceLocation("tfc", "entities/wolf");
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

   public static final EntityDataAccessor<Integer> DATA_COLLAR_COLOR = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.INT);
   public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.INT);
   public static final Predicate<LivingEntity> PREY_SELECTOR = (entity) -> {
      EntityType<?> entitytype = entity.getType();
      return entitytype == EntityTypes.O_SHEEP_ENTITY.get() ||
              entitytype == EntityTypes.O_RABBIT_ENTITY.get() ||
              entitytype == EntityTypes.O_GOAT_ENTITY.get() ||
              entitytype == EntityTypes.O_HORSE_ENTITY.get() ||
              entitytype == EntityTypes.O_COW_ENTITY.get() ||
              entitytype == EntityTypes.O_CHICKEN_ENTITY.get() ||
              entitytype == EntityTypes.O_PIG_ENTITY.get() ||
              entitytype == POEntityTypes.O_FOX_ENTITY.get() ||
              entitytype == POEntityTypes.O_OCELOT_ENTITY.get() ||
              entitytype == POEntityTypes.DOBERMAN_ENTITY.get()||
              entitytype == POEntityTypes.O_CAT_ENTITY.get()
              ;
   };

   public static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
   @Nullable
   public UUID persistentAngerTarget;

   public OWolf(EntityType<? extends OWolf> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
      this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
      this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
   }

   public void registerGoals() {
      this.goalSelector.addGoal(1, new FloatGoal(this));
      this.goalSelector.addGoal(1, new OWolf.WolfPanicGoal(1.4D));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OLlama.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Camel.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, ODonkey.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OMule.class, 24.0F, 1.5D, 1.5D));
      this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
      this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.8D, true));
      this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
      this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
      this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
      this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
      this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
      this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
      this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
      this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
      this.goalSelector.addGoal(7, new CanineFollowPackLeaderGoal(this));
      this.goalSelector.addGoal(6, new WolfFollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));


      this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 2, true, false,
              entity -> entity instanceof Player && (this.isFollower() || this.hasFollowers()) && PetsOverhaulCommonConfig.WOLF_PACKS_ATTACK_PLAYERS.get() && !this.isTame()));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MOVEMENT_SPEED, 0.28F)
              .add(Attributes.MAX_HEALTH, 14.0D)
              .add(Attributes.ATTACK_DAMAGE, 4.0D);
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

   public int regenHealthCounter = 0;
   public int wagCounter = this.random.nextInt(1200) + 1200;
   public int stayWaggingCounter = 0;

   private boolean wag = false;
   public boolean isWagging() {
      return this.wag;
   }
   public void setWagging(boolean wagging) {
      this.wag = wagging;
   }

   public void tick() {
      super.tick();

      wagCounter--;

      if (--this.wagCounter <= 0) {
         this.stayWaggingCounter++;
         setWagging(true);
         if (this.stayWaggingCounter >= 300) {
            this.wagCounter = this.random.nextInt(1200) + 1200;
            this.stayWaggingCounter = 0;
            setWagging(false);
         }
      }

      if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
         List<? extends OWolf> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(20.0D, 20.0D, 20.0D));
         if (list.size() <= 1) {
            this.packSize = 1;
         }
      }

      regenHealthCounter++;

      if (this.getHealth() < this.getMaxHealth() && regenHealthCounter >= 150 && this.isTame() && this.isAlive()) {
         this.setHealth(this.getHealth() + 2);
         regenHealthCounter = 0;
         this.level().addParticle(ParticleTypes.HEART, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
      }

   }

   public OWolf leader;
   public int packSize = 1;

   public boolean isFollower() {
      return this.leader != null && this.leader.isAlive();
   }

   public OWolf startFollowing(OWolf wolf) {
      this.leader = wolf;
      wolf.addFollower();
      return wolf;
   }

   public void stopFollowing() {
      this.leader.removeFollower();
      this.leader = null;
   }

   public void addFollower() {
      ++this.packSize;
   }

   public void removeFollower() {
      --this.packSize;
   }

   public boolean canBeFollowed() {
      return this.hasFollowers() && this.packSize < this.getMaxHerdSize();
   }

   public int getMaxHerdSize() {
      return PetsOverhaulCommonConfig.WOLF_PACK_MAX.get();
   }

   public boolean hasFollowers() {
      return this.packSize > 1;
   }

   public boolean inRangeOfLeader() {
      return this.distanceToSqr(this.leader) <= 121.0D;
   }

   public void pathToLeader() {
      if (this.isFollower()) {
         this.getNavigation().moveTo(this.leader, 1.0D);
      }

   }

   public void addFollowers(Stream<? extends OWolf> p_27534_) {
      p_27534_.limit((long)(this.getMaxHerdSize() - this.packSize)).filter((wolf) -> {
         return wolf != this;
      }).forEach((wolf) -> {
         wolf.startFollowing(this);
      });
   }

   @Override
   public float getStepHeight() {
      return 1.6F;
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

   public boolean hurt(DamageSource damageSource, float p_30387_) {
      if (this.isInvulnerableTo(damageSource)) {
         return false;
      } else {
         Entity entity = damageSource.getEntity();
         if (!this.level().isClientSide) {
            this.setOrderedToSit(false);
         }

         if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
            p_30387_ = (p_30387_ + 1.0F) / 2.0F;
         }

         return super.hurt(damageSource, p_30387_);
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
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
         this.setHealth(20.0F);
      } else {
         this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
      }

      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
   }

   protected float getStandingEyeHeight(Pose pose, EntityDimensions entityDimensions) {
      return entityDimensions.height * 1.2F;
   }

   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);
      Item item = itemstack.getItem();

      if (this.isOwnedBy(player)) {
         if (itemstack.getItem() instanceof DogArmorItem && this.getArmor().isEmpty()) {
            this.setArmor(itemstack);
            this.playSound(SoundEvents.HORSE_ARMOR, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }

         if (itemstack.is(Items.SHEARS) && (this.isCollared() || !this.getArmor().isEmpty())) {
            this.setCollared(false);
            if (!this.getArmor().isEmpty() && this.getArmor().getItem() instanceof DogArmorItem armorItem) {
               this.spawnAtLocation(armorItem);
               this.setArmor(Items.AIR.getDefaultInstance());
            }
            this.playSound(SoundEvents.SHEEP_SHEAR, 0.5f, 1f);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
         }
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

      //doggy talents next compat
      if (itemstack.is(POTags.Items.TRAINING_TREAT) && this.isOwnedBy(player) && ModList.get().isLoaded("doggytalents")) {
         if (!player.level().isClientSide) {
            Entity entity = this;

            ResourceLocation dtnDogId = new ResourceLocation("doggytalents", "dog");

            EntityType<?> dtnDogType = EntityType.byString(dtnDogId.toString()).orElse(null);

            if (dtnDogType != null) {
               Entity newEntity = dtnDogType.create(entity.level());
               if (newEntity != null) {
                  newEntity.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());

                  if (newEntity instanceof TamableAnimal tamable) {
                     tamable.setOwnerUUID(player.getUUID());
                  }

                  entity.level().addFreshEntity(newEntity);
                  entity.discard();
               }
            } else {
               return InteractionResult.PASS;
            }
         }
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
         boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.BONE) && !this.isTame() && !this.isAngry();
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
                  this.setCollared(true);
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
            if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player) && !player.isShiftKeyDown()) {
               this.setOrderedToSit(!this.isOrderedToSit());
               this.jumping = false;
               this.navigation.stop();
               this.setTarget(null);
               return InteractionResult.SUCCESS;
            } else {
               return interactionresult;
            }
         }
      } else if (itemstack.is(Items.BONE) && !this.isAngry()) {
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

   public static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.DOG_FOOD);
   @Override
   public boolean isFood(ItemStack itemStack) {
      return FOOD_ITEMS.test(itemStack);
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

   public static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("3c50e848-b2e3-404a-9879-7550b12dd09b");

   public boolean canWearArmor() {
      return false;
   }

   public boolean isArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof DogArmorItem;
   }

   public void setArmorEquipment(ItemStack itemStack) {
      this.setArmor(itemStack);
      if (!this.level().isClientSide) {
         this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);

         if (itemStack.getItem() instanceof DogArmorItem dogArmorItem) {
            int protection = dogArmorItem.getProtection();
            if (protection > 0) {
               this.getAttribute(Attributes.ARMOR).addTransientModifier(
                       new AttributeModifier(ARMOR_MODIFIER_UUID, "Dog armor bonus", (double) protection, AttributeModifier.Operation.ADDITION)
               );
            }
         }
      }
   }

   public ItemStack getArmor() {
      return this.getItemBySlot(EquipmentSlot.CHEST);
   }

   public void setArmor(ItemStack itemStack) {
      this.setItemSlot(EquipmentSlot.CHEST, itemStack);
      this.setDropChance(EquipmentSlot.CHEST, 0f);
   }


   // Generates the base texture
   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.INT);
   public ResourceLocation getTextureLocation() {
      return OWolfModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }
   public int getVariant() {
      return this.entityData.get(VARIANT);
   }
   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }


   public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.INT);
   public ResourceLocation getOverlayLocation() {return OWolfMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;}
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

   public static final EntityDataAccessor<Boolean> COLLARED = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.BOOLEAN);
   public boolean isCollared() {
      return this.entityData.get(COLLARED);
   }
   public void setCollared(boolean collared) {
      this.entityData.set(COLLARED, collared);
   }

   public void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(OVERLAY, 0);
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

      if (tag.contains("Gender")) {
         this.setGender(tag.getInt("Gender"));
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

      if(tag.contains("ArmorItem")) {
         ItemStack armorItem = ItemStack.of(tag.getCompound("ArmorItem"));
         this.setArmorEquipment(armorItem);
         this.setArmor(armorItem);
      }

      this.readPersistentAngerSaveData(this.level(), tag);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putInt("Variant", getVariant());
      tag.putInt("Overlay", getOverlayVariant());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
      tag.putBoolean("Panicking", this.getPanicking());
      tag.putByte("CollarColor", (byte)this.getCollarColor().getId());
      tag.putBoolean("Collared", this.isCollared());
      if(!this.getArmor().isEmpty()) {
         tag.put("ArmorItem", this.getArmor().save(new CompoundTag()));
      }
      this.addPersistentAngerSaveData(tag);
   }

   @Override
   @javax.annotation.Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @javax.annotation.Nullable SpawnGroupData data, @javax.annotation.Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setVariant(random.nextInt(OWolfModel.Variant.values().length));
      setOverlayVariant(random.nextInt(OWolfMarkingLayer.Overlay.values().length));
      setGender(random.nextInt(OWolf.Gender.values().length));

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
   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OWolf.class, EntityDataSerializers.INT);
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
      } else if (!(animal instanceof OWolf)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((OWolf) animal).canParent();
         } else {
            OWolf partner = (OWolf) animal;
            if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
               return isFemale();
            }
         }
      }
      return false;
   }

   @Override
   public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
      OWolf pup;
      OWolf partner = (OWolf) ageableMob;
      pup = POEntityTypes.O_WOLF_ENTITY.get().create(serverLevel);

      int variantChance = this.random.nextInt(14);
      int variant;
      if (variantChance < 6) {
         variant = this.getVariant();
      } else if (variantChance < 12) {
         variant = partner.getVariant();
      } else {
         variant = this.random.nextInt(OWolfModel.Variant.values().length);
      }
      pup.setVariant(variant);

      int overlayChance = this.random.nextInt(10);
      int overlay;
      if (overlayChance < 4) {
         overlay = this.getOverlayVariant();
      } else if (overlayChance < 8) {
         overlay = partner.getOverlayVariant();
      } else {
         overlay = this.random.nextInt(OWolfMarkingLayer.Overlay.values().length);
      }
      pup.setOverlayVariant(overlay);

      int gender;
      gender = this.random.nextInt(OWolf.Gender.values().length);
      pup.setGender(gender);

      return pup;
   }


   public boolean wantsToAttack(LivingEntity entity, LivingEntity p_30390_) {
      if (!(entity instanceof Creeper) && !(entity instanceof Ghast)) {
         if (entity instanceof OWolf) {
            OWolf wolf = (OWolf)entity;
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
      return !this.isAngry() && super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   public boolean isPanicking = false;
   public boolean isPanicking() {
      return this.getHealth() < this.getMaxHealth() / 3 && this.isAlive();
   }
   public boolean getPanicking() {
      return this.isPanicking;
   }
   public void setPanicking(boolean panicking) {
      this.isPanicking = panicking;
   }

   class WolfPanicGoal extends PanicGoal {
      public WolfPanicGoal(double v) {
         super(OWolf.this, v);
      }

      public boolean shouldPanic() {
         return this.mob.isFreezing() || this.mob.isOnFire() || this.mob.getHealth() < this.mob.getMaxHealth() / 3 && this.mob.isAlive();
      }
   }
}