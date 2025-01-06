package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.entities.EntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;

import javax.annotation.Nullable;
import java.util.Random;

public class Ringneck extends OParrot implements GeoEntity, FlyingAnimal {

   public Ringneck(EntityType<? extends Ringneck> entityType, Level level) {
      super(entityType, level);
      this.setTame(false);
   }

   public static AttributeSupplier.Builder createAttributes() {
      return Mob.createMobAttributes()
              .add(Attributes.MAX_HEALTH, 6.0D)
              .add(Attributes.FLYING_SPEED, (double)1.0F)
              .add(Attributes.MOVEMENT_SPEED, (double)0.2F);
   }

   // Generates the base texture
   public ResourceLocation getTextureResource() {
      return RingneckModel.Variant.variantFromOrdinal(getVariant()).resourceLocation;
   }

   public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Ringneck.class, EntityDataSerializers.INT);

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
   }

   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);

      if (tag.contains("Variant")) {
         setVariant(tag.getInt("Variant"));
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
      setVariant(random.nextInt(RingneckModel.Variant.values().length));
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

   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(Ringneck.class, EntityDataSerializers.INT);

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
      } else if (!(animal instanceof Ringneck)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((Ringneck) animal).canParent();
         } else {
            Ringneck partner = (Ringneck) animal;
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
      Ringneck parrot1 = (Ringneck) ageableMob;
      if (ageableMob instanceof Ringneck) {
         Ringneck parrot = (Ringneck) ageableMob;
         parrot1 = EntityTypes.RINGNECK_ENTITY.get().create(serverLevel);

         int i = this.random.nextInt(9);
         int variant;
         if (i < 4) {
            variant = this.getVariant();
         } else if (i < 8) {
            variant = parrot.getVariant();
         } else {
            variant = this.random.nextInt(RingneckModel.Variant.values().length);
         }

         int gender;
         gender = this.random.nextInt(Ringneck.Gender.values().length);

         parrot1.setVariant(variant);
         parrot1.setGender(gender);
      }

      return parrot1;
   }
  
}