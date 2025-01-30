package com.dragn0007.dragnpets.entities.dog;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HuskyRender extends GeoEntityRenderer<Husky> {

    public HuskyRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HuskyModel());
        this.addRenderLayer(new HuskyCollarLayer(this));
        this.addRenderLayer(new HuskyHarnessLayer(this));
    }

    @Override
    public void render(Husky entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isTame()) {
            model.getBone("collar").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("collar").ifPresent(b -> b.setHidden(true));
        }

        if (entity.isHitchedToSled() && !entity.isChested()) {
            model.getBone("chests").ifPresent(b -> b.setHidden(true));
        } else if (entity.isChested()) {
            model.getBone("chests").ifPresent(b -> b.setHidden(false));
        }

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


