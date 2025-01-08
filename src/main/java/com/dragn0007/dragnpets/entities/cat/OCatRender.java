package com.dragn0007.dragnpets.entities.cat;

import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotCollarLayer;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OCatRender extends GeoEntityRenderer<OCat> {

    public OCatRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OCatModel());
        this.addRenderLayer(new OCatCollarLayer(this));
        this.addRenderLayer(new OCatMarkingLayer(this));
        this.addRenderLayer(new OCatEyeLayer(this));
    }

    @Override
    public void render(OCat entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

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


