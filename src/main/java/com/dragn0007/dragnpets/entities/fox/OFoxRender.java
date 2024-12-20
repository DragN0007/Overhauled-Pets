package com.dragn0007.dragnpets.entities.fox;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OFoxRender extends GeoEntityRenderer<OFox> {

    public OFoxRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OFoxModel());
        this.addRenderLayer(new OFoxMarkingLayer(this));
    }

    @Override
    public void render(OFox entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


