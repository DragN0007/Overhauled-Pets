package com.dragn0007.dragnpets.entities.dog.australian_shepherd;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AustralianShepherdRender extends GeoEntityRenderer<AustralianShepherd> {

    public AustralianShepherdRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AustralianShepherdModel());
        this.addRenderLayer(new AustralianShepherdCollarLayer(this));
    }

    @Override
    public void render(AustralianShepherd entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


