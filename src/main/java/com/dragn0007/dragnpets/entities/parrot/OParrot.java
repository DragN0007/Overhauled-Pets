package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnlivestock.items.LOItems;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.ai.ParrotFollowOwnerGoal;
import com.dragn0007.dragnpets.util.POTags;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
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
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

public class OParrot extends TamableAnimal implements GeoEntity, FlyingAnimal {

   private static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_parrot");
   private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/parrot");
   @Override
   public @NotNull ResourceLocation getDefaultLootTable() {
      if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
         return VANILLA_LOOT_TABLE;
      }
      return LOOT_TABLE;
   }

   private static final Predicate<Mob> NOT_PARROT_PREDICATE = new Predicate<Mob>() {
      public boolean test(@Nullable Mob p_29453_) {
         return p_29453_ != null && OParrot.MOB_SOUND_MAP.containsKey(p_29453_.getType());
      }
   };
   private static final Item POISONOUS_FOOD = Items.COOKIE;
   private static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.PARROT_FOOD);
   @Override
   public boolean isFood(ItemStack itemStack) {
      return FOOD_ITEMS.test(itemStack);
   }


   static final Map<EntityType<?>, SoundEvent> MOB_SOUND_MAP = Util.make(Maps.newHashMap(), (p_29398_) -> {
      p_29398_.put(EntityType.BLAZE, SoundEvents.PARROT_IMITATE_BLAZE);
      p_29398_.put(EntityType.CAVE_SPIDER, SoundEvents.PARROT_IMITATE_SPIDER);
      p_29398_.put(EntityType.CREEPER, SoundEvents.PARROT_IMITATE_CREEPER);
      p_29398_.put(EntityType.DROWNED, SoundEvents.PARROT_IMITATE_DROWNED);
      p_29398_.put(EntityType.ELDER_GUARDIAN, SoundEvents.PARROT_IMITATE_ELDER_GUARDIAN);
      p_29398_.put(EntityType.ENDER_DRAGON, SoundEvents.PARROT_IMITATE_ENDER_DRAGON);
      p_29398_.put(EntityType.ENDERMITE, SoundEvents.PARROT_IMITATE_ENDERMITE);
      p_29398_.put(EntityType.EVOKER, SoundEvents.PARROT_IMITATE_EVOKER);
      p_29398_.put(EntityType.GHAST, SoundEvents.PARROT_IMITATE_GHAST);
      p_29398_.put(EntityType.GUARDIAN, SoundEvents.PARROT_IMITATE_GUARDIAN);
      p_29398_.put(EntityType.HOGLIN, SoundEvents.PARROT_IMITATE_HOGLIN);
      p_29398_.put(EntityType.HUSK, SoundEvents.PARROT_IMITATE_HUSK);
      p_29398_.put(EntityType.ILLUSIONER, SoundEvents.PARROT_IMITATE_ILLUSIONER);
      p_29398_.put(EntityType.MAGMA_CUBE, SoundEvents.PARROT_IMITATE_MAGMA_CUBE);
      p_29398_.put(EntityType.PHANTOM, SoundEvents.PARROT_IMITATE_PHANTOM);
      p_29398_.put(EntityType.PIGLIN, SoundEvents.PARROT_IMITATE_PIGLIN);
      p_29398_.put(EntityType.PIGLIN_BRUTE, SoundEvents.PARROT_IMITATE_PIGLIN_BRUTE);
      p_29398_.put(EntityType.PILLAGER, SoundEvents.PARROT_IMITATE_PILLAGER);
      p_29398_.put(EntityType.RAVAGER, SoundEvents.PARROT_IMITATE_RAVAGER);
      p_29398_.put(EntityType.SHULKER, SoundEvents.PARROT_IMITATE_SHULKER);
      p_29398_.put(EntityType.SILVERFISH, SoundEvents.PARROT_IMITATE_SILVERFISH);
      p_29398_.put(EntityType.SKELETON, SoundEvents.PARROT_IMITATE_SKELETON);
      p_29398_.put(EntityType.SLIME, SoundEvents.PARROT_IMITATE_SLIME);
      p_29398_.put(EntityType.SPIDER, SoundEvents.PARROT_IMITATE_SPIDER);
      p_29398_.put(EntityType.STRAY, SoundEvents.PARROT_IMITATE_STRAY);
      p_29398_.put(EntityType.VEX, SoundEvents.PARROT_IMITATE_VEX);
      p_29398_.put(EntityType.VINDICATOR, SoundEvents.PARROT_IMITATE_VINDICATOR);
      p_29398_.put(EntityType.WARDEN, SoundEvents.PARROT_IMITATE_WARDEN);
      p_29398_.put(EntityType.WITCH, SoundEvents.PARROT_IMITATE_WITCH);
      p_29398_.put(EntityType.WITHER, SoundEvents.PARROT_IMITATE_WITHER);
      p_29398_.put(EntityType.WITHER_SKELETON, SoundEvents.PARROT_IMITATE_WITHER_SKELETON);
      p_29398_.put(EntityType.ZOGLIN, SoundEvents.PARROT_IMITATE_ZOGLIN);
      p_29398_.put(EntityType.ZOMBIE, SoundEvents.PARROT_IMITATE_ZOMBIE);
      p_29398_.put(EntityType.ZOMBIE_VILLAGER, SoundEvents.PARROT_IMITATE_ZOMBIE_VILLAGER);
   });

   public static boolean imitateNearbyMobs(Level p_29383_, Entity p_29384_) {
      if (p_29384_.isAlive() && !p_29384_.isSilent() && p_29383_.random.nextInt(2) == 0) {
         List<Mob> list = p_29383_.getEntitiesOfClass(Mob.class, p_29384_.getBoundingBox().inflate(20.0D), NOT_PARROT_PREDICATE);
         if (!list.isEmpty()) {
            Mob mob = list.get(p_29383_.random.nextInt(list.size()));
            if (!mob.isSilent()) {
               SoundEvent soundevent = getImitatedSound(mob.getType());
               p_29383_.playSound((Player)null, p_29384_.getX(), p_29384_.getY(), p_29384_.getZ(), soundevent, p_29384_.getSoundSource(), 0.7F, getPitch(p_29383_.random));
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void aiStep() {
      if (this.level().random.nextInt(400) == 0) {
         imitateNearbyMobs(this.level(), this);
      }

      super.aiStep();
   }

   public boolean doHurtTarget(Entity p_29365_) {
      return p_29365_.hurt(this.damageSources().mobAttack(this), 3.0F);
   }

   public OParrot(EntityType<? extends OParrot> entityType, Level level) {
      super(entityType, level);
      this.moveControl = new FlyingMoveControl(this, 10, false);
      this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
      this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
      this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
      this.goalSelector.addGoal(0, new FloatGoal(this));
      this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
      this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
      this.goalSelector.addGoal(2, new ParrotFollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
      this.goalSelector.addGoal(2, new OParrot.ParrotWanderGoal(this, 1.0D));
      this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
      this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MAX_HEALTH, 6.0D)
              .add(Attributes.FLYING_SPEED, (double)0.5F)
              .add(Attributes.MOVEMENT_SPEED, (double)0.2F);
   }

   protected PathNavigation createNavigation(Level p_29417_) {
      FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_29417_);
      flyingpathnavigation.setCanOpenDoors(false);
      flyingpathnavigation.setCanFloat(true);
      flyingpathnavigation.setCanPassDoors(true);

      return flyingpathnavigation;
   }

   private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

   private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {

      AnimationController<T> controller = tAnimationState.getController();

      if (tAnimationState.isMoving()) {
            controller.setAnimation(RawAnimation.begin().then("fly", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(3.0);
      } else {
         if (isInSittingPose()) {
            controller.setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.0);
         } else {
            controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            controller.setAnimationSpeed(1.3);
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

   @Override
   public void tick() {
      super.tick();

      regenHealthCounter++;

      if (this.getHealth() < this.getMaxHealth() && regenHealthCounter >= 150 && this.isTame()) {
         this.setHealth(this.getHealth() + 2);
         regenHealthCounter = 0;
         this.level().addParticle(ParticleTypes.HEART, this.getRandomX(0.6D), this.getRandomY(), this.getRandomZ(0.6D), 0.7D, 0.7D, 0.7D);
      }

   }

   public InteractionResult mobInteract(Player player, InteractionHand hand) {
      ItemStack itemstack = player.getItemInHand(hand);

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

      if (this.isTame() && itemstack.is(POTags.Items.PARROT_FOOD) && !this.isInLove()) {
         if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
         }

         if (!this.isSilent()) {
            this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
         }

         setInLove(player);

         return InteractionResult.sidedSuccess(this.level().isClientSide);
      }

      if (!this.isTame() && itemstack.is(POTags.Items.PARROT_FOOD)) {
         if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
         }

         if (!this.isSilent()) {
               this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.PARROT_EAT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
         }

         if (!this.level().isClientSide) {
            if (this.random.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
               this.tame(player);
               this.level().broadcastEntityEvent(this, (byte)7);
            } else {
               this.level().broadcastEntityEvent(this, (byte)6);
            }
         }

         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else if (itemstack.is(POISONOUS_FOOD)) {
         if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
         }

         this.addEffect(new MobEffectInstance(MobEffects.POISON, 900));
         if (player.isCreative() || !this.isInvulnerable()) {
            this.hurt(this.damageSources().playerAttack(player), Float.MAX_VALUE);
         }

         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player)) {
         if (!this.level().isClientSide) {
            this.setOrderedToSit(!this.isOrderedToSit());
         }

         return InteractionResult.sidedSuccess(this.level().isClientSide);
      } else {
         return super.mobInteract(player, hand);
      }
   }

   private boolean toldToWander = false;

   public boolean wasToldToWander() {
      return this.toldToWander;
   }

   public boolean getToldToWander() {
      return this.toldToWander;
   }

   public void setToldToWander(boolean toldToWander) {
      this.toldToWander = toldToWander;
   }

   protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) { }

   @Nullable
   public SoundEvent getAmbientSound() {
      return getAmbient(this.level(), this.level().random);
   }

   public static SoundEvent getAmbient(Level p_218239_, RandomSource p_218240_) {
      if (p_218239_.getDifficulty() != Difficulty.PEACEFUL && p_218240_.nextInt(1000) == 0) {
         List<EntityType<?>> list = Lists.newArrayList(MOB_SOUND_MAP.keySet());
         return getImitatedSound(list.get(p_218240_.nextInt(list.size())));
      } else {
         return SoundEvents.PARROT_AMBIENT;
      }
   }

   private static SoundEvent getImitatedSound(EntityType<?> p_29409_) {
      return MOB_SOUND_MAP.getOrDefault(p_29409_, SoundEvents.PARROT_AMBIENT);
   }

   protected SoundEvent getHurtSound(DamageSource p_29437_) {
      return SoundEvents.PARROT_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.PARROT_DEATH;
   }

   protected void playStepSound(BlockPos p_29419_, BlockState p_29420_) {
      this.playSound(SoundEvents.PARROT_STEP, 0.15F, 1.0F);
   }

   public float getVoicePitch() {
      return getPitch(this.random);
   }

   public static float getPitch(RandomSource p_218237_) {
      return (p_218237_.nextFloat() - p_218237_.nextFloat()) * 0.2F + 1.0F;
   }

   public SoundSource getSoundSource() {
      return SoundSource.NEUTRAL;
   }

   public boolean isPushable() {
      return true;
   }

   protected void doPush(Entity p_29367_) {
      if (!(p_29367_ instanceof Player)) {
         super.doPush(p_29367_);
      }
   }

   public boolean hurt(DamageSource p_29378_, float p_29379_) {
      if (this.isInvulnerableTo(p_29378_)) {
         return false;
      } else {
         if (!this.level().isClientSide) {
            this.setOrderedToSit(false);
         }

         return super.hurt(p_29378_, p_29379_);
      }
   }

   public boolean isFlying() {
      return !this.onGround();
   }

   // Generates the base texture
   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OParrot.class, EntityDataSerializers.INT);

   public int getVariant() {
      return this.entityData.get(VARIANT);
   }

   public void setVariant(int variant) {
      this.entityData.set(VARIANT, variant);
   }

   protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(VARIANT, 0);
      this.entityData.define(GENDER, 0);
   }

   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putInt("Variant", getVariant());
      tag.putInt("Gender", this.getGender());
      tag.putBoolean("Wandering", this.getToldToWander());
   }

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);

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
   @Nullable
   public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
      if (data == null) {
         data = new AgeableMobGroupData(0.2F);
      }
      Random random = new Random();
      setGender(random.nextInt(Gender.values().length));

      return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
   }

   @org.jetbrains.annotations.Nullable
   @Override
   public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
      return null;
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

   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(OParrot.class, EntityDataSerializers.INT);

   public int getGender() {
      return this.entityData.get(GENDER);
   }

   public void setGender(int gender) {
      this.entityData.set(GENDER, gender);
   }

   public boolean canParent() {
      return !this.isBaby() && this.getHealth() >= this.getMaxHealth() && this.isInLove();
   }
   
   public boolean canBeLeashed(Player p_30396_) {
      return super.canBeLeashed(p_30396_);
   }

   public Vec3 getLeashOffset() {
      return new Vec3(0.0D, (double)(0.6F * this.getEyeHeight()), (double)(this.getBbWidth() * 0.4F));
   }

   static class ParrotWanderGoal extends WaterAvoidingRandomFlyingGoal {
      public ParrotWanderGoal(PathfinderMob p_186224_, double p_186225_) {
         super(p_186224_, p_186225_);
      }

      @Nullable
      protected Vec3 getPosition() {
         Vec3 vec3 = null;
         if (this.mob.isInWater()) {
            vec3 = LandRandomPos.getPos(this.mob, 15, 15);
         }

         if (this.mob.getRandom().nextFloat() >= this.probability) {
            vec3 = this.getTreePos();
         }

         return vec3 == null ? super.getPosition() : vec3;
      }

      @Nullable
      private Vec3 getTreePos() {
         BlockPos blockpos = this.mob.blockPosition();
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
         BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

         for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 3.0D), Mth.floor(this.mob.getY() - 6.0D), Mth.floor(this.mob.getZ() - 3.0D), Mth.floor(this.mob.getX() + 3.0D), Mth.floor(this.mob.getY() + 6.0D), Mth.floor(this.mob.getZ() + 3.0D))) {
            if (!blockpos.equals(blockpos1)) {
               BlockState blockstate = this.mob.level().getBlockState(blockpos$mutableblockpos1.setWithOffset(blockpos1, Direction.DOWN));
               boolean flag = blockstate.getBlock() instanceof LeavesBlock || blockstate.is(BlockTags.LOGS);
               if (flag && this.mob.level().isEmptyBlock(blockpos1) && this.mob.level().isEmptyBlock(blockpos$mutableblockpos.setWithOffset(blockpos1, Direction.UP))) {
                  return Vec3.atBottomCenterOf(blockpos1);
               }
            }
         }

         return null;
      }
   }
  
}