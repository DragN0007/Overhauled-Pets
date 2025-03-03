package com.dragn0007.dragnpets.entities.cat.kornish_rex;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KornishRexRender extends GeoEntityRenderer<KornishRex> {

    public KornishRexRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new KornishRexModel());
        this.addRenderLayer(new KornishRexCollarLayer(this));
        this.addRenderLayer(new KornishRexMarkingLayer(this));
        this.addRenderLayer(new KornishRexEyeLayer(this));
    }

    @Override
    public void render(KornishRex entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


