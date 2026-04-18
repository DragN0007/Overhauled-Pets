package com.dragn0007.dragnpets.mixin;

import com.dragn0007.dragnlivestock.entities.ai.OAvoidEntityGoal;
import com.dragn0007.dragnlivestock.entities.cow.OCow;
import com.dragn0007.dragnlivestock.entities.util.AbstractOMount;
import com.dragn0007.dragnlivestock.util.LOTags;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OCow.class)
public abstract class OCowMixin extends AbstractOMount {

    protected OCowMixin(EntityType<? extends AbstractOMount> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"))
    protected void onRegisterGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(1, new OAvoidEntityGoal<>(this, LivingEntity.class, 15.0F, 2.0F, 2.3D, livingEntity ->
                livingEntity instanceof ODog dog && dog.isTame() && !this.isLeashed() && dog.isHerdingDog())
        );
    }
}
