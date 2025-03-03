package com.dragn0007.dragnpets.entities.ai;

import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
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

public class FollowRabbitGoal extends Goal {
   public final Mob mob;
   public final Predicate<Mob> followPredicate;
   @Nullable
   public ORabbit oRabbit;
   public final double speedModifier;
   public final PathNavigation navigation;
   public int timeToRecalcPath;
   public final float stopDistance;
   public float oldWaterCost;
   public final float areaSize;

   public FollowRabbitGoal(Mob p_25271_, double p_25272_, float p_25273_, float p_25274_) {
      this.mob = p_25271_;
      this.followPredicate = (p_25278_) -> {
         return p_25278_ != null && p_25271_.getClass() != p_25278_.getClass();
      };
      this.speedModifier = p_25272_;
      this.navigation = p_25271_.getNavigation();
      this.stopDistance = p_25273_;
      this.areaSize = p_25274_;
      this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
      if (!(p_25271_.getNavigation() instanceof GroundPathNavigation) && !(p_25271_.getNavigation() instanceof FlyingPathNavigation)) {
         throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
      }
   }

   public boolean canUse() {
      List<ORabbit> list = this.mob.level().getEntitiesOfClass(ORabbit.class, this.mob.getBoundingBox().inflate((double)this.areaSize), this.followPredicate);
      if (!list.isEmpty()) {
         for(ORabbit mob : list) {
            if (!mob.isInvisible()) {
               this.oRabbit = mob;
               return true;
            }
         }
      }

      return false;
   }

   public boolean canContinueToUse() {
      return this.oRabbit != null && !this.navigation.isDone() && this.mob.distanceToSqr(this.oRabbit) > (double)(this.stopDistance * this.stopDistance);
   }

   public void start() {
      this.timeToRecalcPath = 0;
      this.oldWaterCost = this.mob.getPathfindingMalus(BlockPathTypes.WATER);
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
   }

   public void stop() {
      this.oRabbit = null;
      this.navigation.stop();
      this.mob.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
   }

   public void tick() {
      if (this.oRabbit != null && !this.mob.isLeashed()) {
         this.mob.getLookControl().setLookAt(this.oRabbit, 10.0F, (float)this.mob.getMaxHeadXRot());
         if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            double d0 = this.mob.getX() - this.oRabbit.getX();
            double d1 = this.mob.getY() - this.oRabbit.getY();
            double d2 = this.mob.getZ() - this.oRabbit.getZ();
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (!(d3 <= (double)(this.stopDistance * this.stopDistance))) {
               this.navigation.moveTo(this.oRabbit, this.speedModifier);
            } else {
               this.navigation.stop();
               LookControl lookcontrol = this.oRabbit.getLookControl();
               if (d3 <= (double)this.stopDistance || lookcontrol.getWantedX() == this.mob.getX() && lookcontrol.getWantedY() == this.mob.getY() && lookcontrol.getWantedZ() == this.mob.getZ()) {
                  double d4 = this.oRabbit.getX() - this.mob.getX();
                  double d5 = this.oRabbit.getZ() - this.mob.getZ();
                  this.navigation.moveTo(this.mob.getX() - d4, this.mob.getY(), this.mob.getZ() - d5, this.speedModifier);
               }

            }
         }
      }
   }
}