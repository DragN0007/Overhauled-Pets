package com.dragn0007.dragnpets.entities.tropical_fish;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.horse.BreedModel;
import com.dragn0007.dragnlivestock.entities.util.AbstractSchoolingOFish;
import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotl;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlMarkingLayer;
import com.dragn0007.dragnpets.entities.axolotl.OAxolotlModel;
import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Random;

public class OTropicalFish extends AbstractSchoolingOFish implements GeoEntity {

	public OTropicalFish(EntityType<? extends OTropicalFish> type, Level level) {
		super(type, level);
	}

	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(PetsOverhaul.MODID, "entities/o_tropical_fish");
	private static final ResourceLocation VANILLA_LOOT_TABLE = new ResourceLocation("minecraft", "entities/tropical_fish");
	@Override
	public @NotNull ResourceLocation getDefaultLootTable() {
		if (LivestockOverhaulCommonConfig.USE_VANILLA_LOOT.get()) {
			return VANILLA_LOOT_TABLE;
		}
		return LOOT_TABLE;
	}

	public OTropicalFish getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
		return EntityTypes.O_TROPICAL_FISH_ENTITY.get().create(serverLevel);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 2.0D);
	}

	public int getMaxSchoolSize() {
		return 5;
	}

	@Override
	public void saveToBucketTag(ItemStack itemStack) {
		super.saveToBucketTag(itemStack);
		CompoundTag compoundTag = itemStack.getOrCreateTag();
		compoundTag.putInt("Variant", getVariant());
		compoundTag.putInt("Overlay", getOverlay());
		compoundTag.putInt("Species", getSpecies());

		compoundTag.putInt("Age", this.getAge());
	}

	@Override
	public void loadFromBucketTag(CompoundTag compoundTag) {
		Bucketable.loadDefaultDataFromBucketTag(this, compoundTag);
		this.setVariant(compoundTag.getInt("Variant"));
		this.setOverlay(compoundTag.getInt("Overlay"));
		this.setSpecies(compoundTag.getInt("Species"));

		if (compoundTag.contains("Age")) {this.setAge(compoundTag.getInt("Age"));}
	}

	public ItemStack getBucketItemStack() {
		return new ItemStack(POItems.O_TROPICAL_FISH_BUCKET.get());
	}

	public SoundEvent getAmbientSound() {
		return SoundEvents.TROPICAL_FISH_AMBIENT;
	}

	public SoundEvent getDeathSound() {
		return SoundEvents.TROPICAL_FISH_DEATH;
	}

	public SoundEvent getHurtSound(DamageSource p_29795_) {
		return SoundEvents.TROPICAL_FISH_HURT;
	}

	public SoundEvent getFlopSound() {
		return SoundEvents.TROPICAL_FISH_FLOP;
	}

	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
		double currentSpeed = this.getDeltaMovement().lengthSqr();
		double speedThreshold = 0.01;

		AnimationController<T> controller = tAnimationState.getController();

		if(!this.isInWater()) {
				controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP));
		}

		if(tAnimationState.isMoving()) {
			if (currentSpeed > speedThreshold) {
				controller.setAnimation(RawAnimation.begin().then("swim_sprint", Animation.LoopType.LOOP));
			} else if(currentSpeed < speedThreshold) {
					controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP));
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

	public enum Species {
		SMALL(new ResourceLocation(PetsOverhaul.MODID, "geo/tropical_fish_small.geo.json")),
		MEDIUM(new ResourceLocation(PetsOverhaul.MODID, "geo/tropical_fish.geo.json")),
		LARGE(new ResourceLocation(PetsOverhaul.MODID, "geo/tropical_fish_large.geo.json"));

		public final ResourceLocation resourceLocation;

		Species(ResourceLocation resourceLocation) {
			this.resourceLocation = resourceLocation;
		}

		public static OTropicalFish.Species speciesfromordinal(int ordinal) {
			return OTropicalFish.Species.values()[ordinal % OTropicalFish.Species.values().length];
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance instance, MobSpawnType spawnType, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
		if (data == null) {
			data = new AgeableMobGroupData(0.2F);
		}
		Random random = new Random();
		setVariant(random.nextInt(OTropicalFishModel.Variant.values().length));
		setOverlay(random.nextInt(OTropicalFishMarkingLayer.Overlay.values().length));
		setSpecies(random.nextInt(OTropicalFish.Species.values().length));

		return super.finalizeSpawn(serverLevelAccessor, instance, spawnType, data, tag);
	}

	// Generates the base texture
	public ResourceLocation getTextureResource() {
		return OTropicalFishModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
	}

	public ResourceLocation getOverlayResource() {
		return OTropicalFishMarkingLayer.Overlay.overlayFromOrdinal(getOverlay()).resourceLocation;
	}

	public ResourceLocation getSpeciesResource() {
		return Species.speciesfromordinal(getSpecies()).resourceLocation;
	}

	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(OTropicalFish.class, EntityDataSerializers.INT);

	public int getVariant() {
		return this.entityData.get(VARIANT);
	}

	public void setVariant(int variant) {
		this.entityData.set(VARIANT, variant);
	}

	public static final EntityDataAccessor<Integer> OVERLAY = SynchedEntityData.defineId(OTropicalFish.class, EntityDataSerializers.INT);

	public int getOverlay() {
		return this.entityData.get(OVERLAY);
	}

	public void setOverlay(int overlay) {
		this.entityData.set(OVERLAY, overlay);
	}

	public static final EntityDataAccessor<Integer> SPECIES = SynchedEntityData.defineId(OTropicalFish.class, EntityDataSerializers.INT);

	public int getSpecies() {
		return this.entityData.get(SPECIES);
	}

	public void setSpecies(int species) {
		this.entityData.set(SPECIES, species);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("Variant")) {
			setVariant(tag.getInt("Variant"));
		}

		if (tag.contains("Overlay")) {
			setOverlay(tag.getInt("Overlay"));
		}

		if (tag.contains("Species")) {
			setSpecies(tag.getInt("Species"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Variant", getVariant());
		tag.putInt("Overlay", getOverlay());
		tag.putInt("Species", getSpecies());
	}

	@Override
	public void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, 0);
		this.entityData.define(OVERLAY, 0);
		this.entityData.define(SPECIES, 0);
	}
}
