package com.dragn0007.dragnpets.entities.dog.cocker_spaniel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CockerSpanielRender extends GeoEntityRenderer<CockerSpaniel> {

    public CockerSpanielRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CockerSpanielModel());
        this.addRenderLayer(new CockerSpanielMarkingLayer(this));
        this.addRenderLayer(new CockerSpanielCollarLayer(this));
    }

    @Override
    public void render(CockerSpaniel entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


