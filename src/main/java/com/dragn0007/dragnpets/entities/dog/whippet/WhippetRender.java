package com.dragn0007.dragnpets.entities.dog.whippet;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WhippetRender extends GeoEntityRenderer<Whippet> {

    public WhippetRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WhippetModel());
        this.addRenderLayer(new WhippetMarkingLayer(this));
        this.addRenderLayer(new WhippetCollarLayer(this));
    }

    @Override
    public void render(Whippet entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


