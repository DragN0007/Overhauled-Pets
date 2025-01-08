package com.dragn0007.dragnpets.entities.tropical_fish;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OTropicalFishRender extends GeoEntityRenderer<OTropicalFish> {

    public OTropicalFishRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OTropicalFishModel());
        this.addRenderLayer(new OTropicalFishMarkingLayer(this));
    }

    @Override
    public void render(OTropicalFish entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


