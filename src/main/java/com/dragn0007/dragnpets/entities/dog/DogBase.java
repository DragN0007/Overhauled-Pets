package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.UUID;

public class DogBase extends TamableAnimal implements NeutralMob, GeoEntity {


   protected DogBase(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
      super(p_21803_, p_21804_);
   }

   @Nullable
   @Override
   public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
      return null;
   }

   @Override
   public int getRemainingPersistentAngerTime() {
      return 0;
   }

   @Override
   public void setRemainingPersistentAngerTime(int p_21673_) {

   }

   @Nullable
   @Override
   public UUID getPersistentAngerTarget() {
      return null;
   }

   @Override
   public void setPersistentAngerTarget(@Nullable UUID p_21672_) {

   }

   @Override
   public void startPersistentAngerTimer() {

   }

   @Override
   public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

   }

   @Override
   public AnimatableInstanceCache getAnimatableInstanceCache() {
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
   public static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(DogBase.class, EntityDataSerializers.INT);
   public int getGender() {
      return this.entityData.get(GENDER);
   }
   public void setGender(int gender) {
      this.entityData.set(GENDER, gender);
   }

   public boolean canParent() {
      return !this.isBaby() && this.isInLove();
   }

   public boolean canMate(Animal animal) {
      if (animal == this) {
         return false;
      } else if (!(animal instanceof DogBase)) {
         return false;
      } else {
         if (!LivestockOverhaulCommonConfig.GENDERS_AFFECT_BREEDING.get()) {
            return this.canParent() && ((DogBase) animal).canParent();
         } else {
            DogBase partner = (DogBase) animal;
            if (this.canParent() && partner.canParent() && this.getGender() != partner.getGender()) {
               return this.isFemale();
            }
         }
      }
      return false;
   }
}