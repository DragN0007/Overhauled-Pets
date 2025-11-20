package com.dragn0007.dragnpets.entities.ai;

import com.dragn0007.dragnlivestock.util.LivestockOverhaulCommonConfig;
import com.dragn0007.dragnpets.entities.dog.DogBase;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class DogFollowPackLeaderGoal extends Goal {
   public static final int INTERVAL_TICKS = 200;
   public final ODog mob;
   public int timeToRecalcPath;
   public int nextStartTick;

   public DogFollowPackLeaderGoal(ODog p_25249_) {
      this.mob = p_25249_;
      this.nextStartTick = this.nextStartTick(p_25249_);
   }

   public int nextStartTick(ODog cow) {
      return reducedTickDelay(200 + cow.getRandom().nextInt(200) % 20);
   }

   public boolean canUse() {
      if (this.mob.hasFollowers() || !LivestockOverhaulCommonConfig.ANIMALS_HERDING_ENABLED.get() || this.mob.isInSittingPose()) {
         return false;
      } else if (this.mob.isFollower()) {
         return true;
      } else if (this.nextStartTick > 0) {
         --this.nextStartTick;
         return false;
      } else {
         this.nextStartTick = this.nextStartTick(this.mob);
         Predicate<ODog> predicate = (follower) -> {
            return follower.canBeFollowed() || !follower.isFollower();
         };
         List<? extends ODog> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
         ODog dog = DataFixUtils.orElse(list.stream().filter(ODog::canBeFollowed).findAny(), this.mob);
         dog.addFollowers(list.stream().filter((cow) -> {
            return !cow.isFollower();
         }));
         return this.mob.isFollower();
      }
   }

   public boolean canContinueToUse() {
      return this.mob.isFollower() && this.mob.inRangeOfLeader() && LivestockOverhaulCommonConfig.ANIMALS_HERDING_ENABLED.get() && !this.mob.isInSittingPose();
   }

   public void start() {
      this.timeToRecalcPath = 0;
   }

   public void stop() {
      this.mob.stopFollowing();
   }

   public void tick() {
      if (--this.timeToRecalcPath <= 0) {
         this.timeToRecalcPath = this.adjustedTickDelay(10);

         ODog leader = this.mob.leader;
         if (mob.goalSelector.getRunningGoals().noneMatch(goal -> goal.getGoal() instanceof AvoidEntityGoal<?>)) {
            if (leader != null) {
               double distanceSq = this.mob.distanceToSqr(leader);
               double minDistanceSq = 3.0D * 3.0D;

               if (distanceSq > minDistanceSq) {
                  this.mob.pathToLeader();
               } else {
                  this.mob.getNavigation().stop();
               }
            }
         }
      }
   }
}