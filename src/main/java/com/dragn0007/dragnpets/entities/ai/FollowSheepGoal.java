package com.dragn0007.dragnpets.entities.ai;

import com.dragn0007.dragnlivestock.entities.sheep.OSheep;
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

public class FollowSheepGoal extends Goal {
   private final Mob mob;
   private final Predicate<Mob> followPredicate;
   @Nullable
   private OSheep oSheep;
   private final double speedModifier;
   private final PathNavigation navigation;
   private int timeToRecalcPath;
   private final float stopDistance;
   private float oldWaterCost;
   private final float areaSize;

   public FollowSheepGoal(Mob dog, double speedMod, float stopDist, float areaSize) {
      this.mob = dog;
      this.followPredicate = (p_25278_) -> {
         return p_25278_ != null && dog.getClass() != p_25278_.getClass();
      };
      this.speedModifier = speedMod;
      this.navigation = dog.getNavigation();
      this.stopDistance = stopDist;
      this.areaSize = areaSize;
      this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
      if (!(dog.getNavigation() instanceof GroundPathNavigation) && !(dog.getNavigation() instanceof FlyingPathNavigation)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
      }
   }

   public boolean canUse() {
      List<OSheep> list = this.mob.level().getEntitiesOfClass(OSheep.class, this.mob.getBoundingBox().inflate((double)this.areaSize), this.followPredicate);
      if (!list.isEmpty()) {
         for(OSheep mob : list) {
            if (!mob.isInvisible()) {
               this.oSheep = mob;
               return true;
            }
         }
      }

      return false;
   }

   public boolean canContinueToUse() {
      return this.oSheep != null && !this.navigation.isDone() && this.mob.distanceToSqr(this.oSheep) > (double)(this.stopDistance * this.stopDistance);
   }

   public void start() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public void stop() {
      this.oSheep = null;
      this.navigation.stop();
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
   }

   public void tick() {
      if (this.oSheep != null && !this.mob.isLeashed()) {
         this.mob.getLookControl().setLookAt(this.oSheep, 10.0F, (float)this.mob.getMaxHeadXRot());
         if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            double d0 = this.mob.getX() - this.oSheep.getX();
            double d1 = this.mob.getY() - this.oSheep.getY();
            double d2 = this.mob.getZ() - this.oSheep.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (!(d3 <= (double)(this.stopDistance * this.stopDistance))) {
               this.navigation.moveTo(this.oSheep, this.speedModifier);
            } else {
               this.navigation.stop();
               LookControl lookcontrol = this.oSheep.getLookControl();
               if (d3 <= (double)this.stopDistance || lookcontrol.getWantedX() == this.mob.getX() && lookcontrol.getWantedY() == this.mob.getY() && lookcontrol.getWantedZ() == this.mob.getZ()) {
                  double d4 = this.oSheep.getX() - this.mob.getX();
                  double d5 = this.oSheep.getZ() - this.mob.getZ();
                  this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ() - d5, this.speedModifier);
               }

            }
         }
      }
   }
}