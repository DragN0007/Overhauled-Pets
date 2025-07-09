package com.dragn0007.dragnpets.entities.cat.manx;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ManxRender extends GeoEntityRenderer<Manx> {

    public ManxRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ManxModel());
        this.addRenderLayer(new ManxCollarLayer(this));
        this.addRenderLayer(new ManxMarkingLayer(this));
        this.addRenderLayer(new ManxEyeLayer(this));
    }

    @Override
    public void render(Manx entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isTame()) {
            model.getBone("collar").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("collar").ifPresent(b -> b.setHidden(true));
        }

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


