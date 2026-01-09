package com.dragn0007.dragnpets.mixin;

import com.dragn0007.dragnpets.entities.dog.ODog;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin extends Block implements BucketPickup {

    public PowderSnowBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(method = "canEntityWalkOnPowderSnow", at = @At("HEAD"))
    private static void canEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof ODog dog && dog.getBreed() == 11) {
            cir.setReturnValue(true);
        }
    }
}
