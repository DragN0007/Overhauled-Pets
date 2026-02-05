package com.dragn0007.dragnpets.entities.ai;

import com.dragn0007.dragnlivestock.entities.farm_goat.FarmGoat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class FollowGoatGoal extends Goal {
   public final ODog mob;
   public final Predicate<Mob> followPredicate;
   @Nullable
   public FarmGoat goat;
   public final double speedModifier;
   public final PathNavigation navigation;
   public int timeToRecalcPath;
   public final float stopDistance;
   public float oldWaterCost;
   public final float areaSize;

   public FollowGoatGoal(ODog dog, double speedMod, float stopDist, float areaSize) {
      this.mob = dog;
      this.followPredicate = (p_25278_) -> {
         return p_25278_ != null && dog.getClass() != p_25278_.getClass();
      };
      this.speedModifier = speedMod;
      this.navigation = dog.getNavigation();
      this.stopDistance = stopDist;
      this.areaSize = areaSize;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
      if (!(dog.getNavigation() instanceof GroundPathNavigation) && !(dog.getNavigation() instanceof FlyingPathNavigation)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
      }
   }

   public boolean canUse() {
      List<FarmGoat> list = this.mob.level().getEntitiesOfClass(FarmGoat.class, this.mob.getBoundingBox().inflate((double)this.areaSize), this.followPredicate);

      if (!list.isEmpty() && mob.isLivestockGuardian()) {
         for(FarmGoat mob : list) {
            if (!mob.isInvisible()) {
               this.goat = mob;
               return true;
            }
         }
      }

      return false;
   }

   public boolean canContinueToUse() {
      return this.goat != null && mob.isLivestockGuardian() && !this.navigation.isDone() && this.mob.distanceToSqr(this.goat) > (double)(this.stopDistance * this.stopDistance);
   }

   public void start() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public void stop() {
      this.goat = null;
      this.navigation.stop();
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
   }

   public void tick() {
      if (this.goat != null && !this.mob.isLeashed()) {
         this.mob.getLookControl().setLookAt(this.goat, 10.0F, (float)this.mob.getMaxHeadXRot());
         if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            double d0 = this.mob.getX() - this.goat.getX();
            double d1 = this.mob.getY() - this.goat.getY();
            double d2 = this.mob.getZ() - this.goat.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (!(d3 <= (double)(this.stopDistance * this.stopDistance))) {
               this.navigation.moveTo(this.goat, this.speedModifier);
            } else {
               this.navigation.stop();
               LookControl lookcontrol = this.goat.getLookControl();
               if (d3 <= (double)this.stopDistance || lookcontrol.getWantedX() == this.mob.getX() && lookcontrol.getWantedY() == this.mob.getY() && lookcontrol.getWantedZ() == this.mob.getZ()) {
                  double d4 = this.goat.getX() - this.mob.getX();
                  double d5 = this.goat.getZ() - this.mob.getZ();
                  this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ() - d5, this.speedModifier);
               }

            }
         }
      }
   }
}