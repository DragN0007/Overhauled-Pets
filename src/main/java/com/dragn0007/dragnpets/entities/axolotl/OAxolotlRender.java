package com.dragn0007.dragnpets.entities.axolotl;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OAxolotlRender extends GeoEntityRenderer<OAxolotl> {

    public OAxolotlRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OAxolotlModel());
        this.addRenderLayer(new OAxolotlMarkingLayer(this));
    }

    @Override
    public void render(OAxolotl entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        if(entity.hasShortGills()) {
            model.getBone("top_gills").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("top_gills").ifPresent(b -> b.setScaleX(0.5F));
            model.getBone("right_gills").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("right_gills").ifPresent(b -> b.setScaleX(0.5F));
            model.getBone("left_gills").ifPresent(b -> b.setScaleY(0.5F));
            model.getBone("left_gills").ifPresent(b -> b.setScaleX(0.5F));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


