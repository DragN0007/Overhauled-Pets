package com.dragn0007.dragnpets.entities.axolotl;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.items.POItems;
import com.dragn0007.dragnpets.util.POTags;
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
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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
import java.util.Random;

public class OAxolotl extends Animal implements GeoEntity, Bucketable {

	public OAxolotl(EntityType<? extends OAxolotl> type, Level level) {
		super(type, level);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new OAxolotl.AxolotlMoveControl(this);
		this.setMaxUpStep(0.0F);
	}

	@Override
	public float getStepHeight() {
		return 0.0F;
	}

	static class AxolotlMoveControl extends SmoothSwimmingMoveControl {
		private final OAxolotl oAxolotl;

		public AxolotlMoveControl(OAxolotl oAxolotl) {
			super(oAxolotl, 85, 10, 0.1F, 0.5F, false);
			this.oAxolotl = oAxolotl;
		}
	}

	public void travel(Vec3 vec3) {
		if (this.isControlledByLocalInstance() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), vec3);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
		} else {
			super.travel(vec3);
		}

	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(LivestockOverhaul.MODID, "entities/o_axolotl");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/axolotl");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	@Override
	public Vec3 getLeashOffset() {
		return new Vec3(0D, (double)this.getEyeHeight() * 0.8F, (double)(this.getBbWidth() * 0.9F));
		//              ^ Side offset                      ^ Height offset                   ^ Length offset
	}

	public int getMaxAirSupply() {
		return 3000;
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public MobType getMobType() {
		return MobType.WATER;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 14.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D);

	}

	public static final Ingredient FOOD_ITEMS = Ingredient.of(POTags.Items.AXOLOTL_FOOD);

	public void registerGoals() {
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 1.8F, 1.8F, livingEntity -> {
			boolean isOWolf = livingEntity.getType().is(LOTags.Entity_Types.WOLVES);
			boolean isOCat = livingEntity.getType().is(LOTags.Entity_Types.CATS);
			boolean isWolf = livingEntity instanceof Wolf;
			return isOWolf || isWolf || isOCat;
		}));
	}

	@Override
	public boolean hurt(DamageSource damageSource, float v) {
		if (damageSource.is(DamageTypes.DROWN)) {
			return false;
		}
		return super.hurt(damageSource, v);
	}

	public InteractionResult mobInteract(Player p_149155_, InteractionHand p_149156_) {
		return Bucketable.bucketMobPickup(p_149155_, p_149156_, this).orElse(super.mobInteract(p_149155_, p_149156_));
	}

	public boolean removeWhenFarAway(double p_149183_) {
		return !this.fromBucket() && !this.hasCustomName();
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(software.bernie.geckolib.core.animation.AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if (tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(2.0);
			} else {
				controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP));
				controller.setAnimationSpeed(1.0);
			}
		} else {
			controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP));
			controller.setAnimationSpeed(0.6);
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

	protected SoundEvent getHurtSound(DamageSource p_149161_) {
		return SoundEvents.AXOLOTL_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return SoundEvents.AXOLOTL_DEATH;
	}

	@Nullable
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? SoundEvents.AXOLOTL_IDLE_WATER : SoundEvents.AXOLOTL_IDLE_AIR;
	}

	protected SoundEvent getSwimSplashSound() {
		return SoundEvents.AXOLOTL_SPLASH;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.AXOLOTL_SWIM;
	}

	public boolean isFood(ItemStack p_28271_) {
		return FOOD_ITEMS.test(p_28271_);
	}

	public boolean isBaby() {
		return false;
	}

	public void setBaby(boolean p_218500_) {
	}

	// Generates the base texture
	public ResourceLocation getTextureLocation() {
		return OAxolotlModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayLocation() {
		return OAxolotlMarkingLayer.Overlay.overlayFromOrdinal(getOverlayVariant()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OAxolotl.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OAxolotl.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(OAxolotl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> GILL_LENGTH = SynchedEntityData.defineId(OAxolotl.class, EntityDataSerializers.INT);

	public enum Gills {
		SHORT,
		LONG
	}

	public boolean hasShortGills() {
		return this.getGills() == 0;
	}

	public boolean hasLongGills() {
		return this.getGills() == 1;
	}

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}
	public int getOverlayVariant() {
		return this.entityData.get(OVERLAY);
	}
	public int getGills() {
		return this.entityData.get(GILL_LENGTH);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}
	public void setOverlayVariant(int overlayVariant) {
		this.entityData.set(OVERLAY, overlayVariant);
	}
	public void setGills(int gills) {
		this.entityData.set(GILL_LENGTH, gills);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlayVariant(tag.getInt("Overlay"));
		}

		if (tag.contains("Gills")) {
			setGills(tag.getInt("Gills"));
		}

		this.setFromBucket(tag.getBoolean("FromBucket"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlayVariant());
		tag.putBoolean("FromBucket", this.fromBucket());
		tag.putInt("Gills", getGills());
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OAxolotlModel.Variant.values().length));
		setOverlayVariant(random.nextInt(OAxolotlMarkingLayer.Overlay.values().length));
		setGills(random.nextInt(OAxolotl.Gills.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(FROM_BUCKET, false);
		this.entityData.define(GILL_LENGTH, 0);
	}

	public void saveToBucketTag(ItemStack itemStack) {
		Bucketable.saveDefaultDataToBucketTag(this, itemStack);
		CompoundTag compoundtag = itemStack.getOrCreateTag();
		compoundtag.putInt("Variant", this.getVariant());
		compoundtag.putInt("Overlay", this.getOverlayVariant());
		compoundtag.putInt("Age", this.getAge());
	}

	public void loadFromBucketTag(CompoundTag compoundTag) {
		Bucketable.loadDefaultDataFromBucketTag(this, compoundTag);
		this.setVariant(compoundTag.getInt("Variant"));
		this.setOverlayVariant(compoundTag.getInt("Overlay"));
		if (compoundTag.contains("Age")) {
			this.setAge(compoundTag.getInt("Age"));
		}
	}

	public boolean canParent() {
		return !this.isBaby() && this.isInLove();
	}

	public boolean canMate(Animal animal) {
			return animal instanceof OAxolotl && canParent();
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		OAxolotl oAxolotl = (OAxolotl) ageableMob;
		if (ageableMob instanceof OAxolotl) {
			OAxolotl oAxolotl1 = (OAxolotl) ageableMob;
			oAxolotl = EntityTypes.O_AXOLOTL_ENTITY.get().create(serverLevel);

			int i = this.random.nextInt(9);
			int variant;
			if (i < 4) {
				variant = this.getVariant();
			} else if (i < 8) {
				variant = oAxolotl1.getVariant();
			} else {
				variant = this.random.nextInt(OAxolotlModel.Variant.values().length);
			}

			int j = this.random.nextInt(9);
			int overlay;
			if (j < 4) {
				overlay = this.getOverlayVariant();
			} else if (j < 8) {
				overlay = oAxolotl1.getOverlayVariant();
			} else {
				overlay = this.random.nextInt(OAxolotlMarkingLayer.Overlay.values().length);
			}

			int k = this.random.nextInt(9);
			int gills;
			if (k < 4) {
				gills = this.getGills();
			} else if (k < 8) {
				gills = oAxolotl1.getGills();
			} else {
				gills = this.random.nextInt(OAxolotl.Gills.values().length);
			}

			oAxolotl.setVariant(variant);
			oAxolotl.setOverlayVariant(overlay);
			oAxolotl.setGills(gills);
		}

		return oAxolotl;
	}

	public boolean fromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean p_149196_) {
		this.entityData.set(FROM_BUCKET, p_149196_);
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(POItems.O_AXOLOTL_BUCKET.get());
	}

	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_AXOLOTL;
	}
}
