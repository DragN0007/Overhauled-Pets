package com.dragn0007.dragnpets.entities.ai;

import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractGeckolibVehicle;
import com.dragn0007.dragnlivestock.entities.wagon.base.AbstractWagon;
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
import java.util.Objects;
import java.util.function.Predicate;

public class FollowWagonGoal extends Goal {
   public final ODog mob;
   public final Predicate<AbstractWagon> followPredicate;
   @Nullable
   public AbstractWagon wagon;
   public final double speedModifier;
   public final PathNavigation navigation;
   public int timeToRecalcPath;
   public final float stopDistance;
   public float oldWaterCost;
   public final float areaSize;

   public FollowWagonGoal(ODog dog, double speedMod, float stopDist, float areaSize) {
      this.mob = dog;
      this.followPredicate = Objects::nonNull;
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
      List<AbstractWagon> list = this.mob.level().getEntitiesOfClass(AbstractWagon.class, this.mob.getBoundingBox().inflate((double)this.areaSize), this.followPredicate);
      
      if (!list.isEmpty() && this.mob.getBreed() == 26) {
         for(AbstractWagon mob : list) {
            if (!mob.isInvisible()) {
               this.wagon = mob;
               return true;
            }
         }
      }

      return false;
   }

   public boolean canContinueToUse() {
      return this.wagon != null && this.mob.getBreed() == 26 && !this.navigation.isDone() && this.mob.distanceToSqr(this.wagon) > (double)(this.stopDistance * this.stopDistance);
   }

   public void start() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public void stop() {
      this.wagon = null;
      this.navigation.stop();
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
   }

   public void tick() {
      if (this.wagon != null && !this.mob.isLeashed()) {
         this.mob.getLookControl().setLookAt(this.wagon, 10.0F, (float)this.mob.getMaxHeadXRot());
         if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            double d0 = this.mob.getX() - this.wagon.getX();
            double d1 = this.mob.getY() - this.wagon.getY();
            double d2 = this.mob.getZ() - this.wagon.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (!(d3 <= (double)(this.stopDistance * this.stopDistance))) {
               this.navigation.moveTo(this.wagon, this.speedModifier);
            } else {
               this.navigation.stop();

               if (d3 <= (double)this.stopDistance) {
                  double d4 = this.wagon.getX() - this.mob.getX();
                  double d5 = this.wagon.getZ() - this.mob.getZ();
                  this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ() - d5, this.speedModifier);
               }
            }
         }
      }
   }
}