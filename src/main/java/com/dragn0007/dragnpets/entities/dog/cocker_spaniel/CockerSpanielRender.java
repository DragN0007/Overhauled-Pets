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
        this.addRenderLayer(new CockerSpanielDecorLayer(this));
    }

    @Override
    public void render(CockerSpaniel entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if (entity.isTame()) {
            model.getBone("collar").ifPresent(b -> b.setHidden(false));
        } else {
            model.getBone("collar").ifPresent(b -> b.setHidden(true));
        }

        if (entity.getFluff() == 0) {
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("tail_fluff_1").ifPresent(b -> b.setHidden(true));
            model.getBone("tail_fluff_2").ifPresent(b -> b.setHidden(true));
        } else if (entity.getFluff() == 1) {
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(false));
            if (entity.getCropped() == 0 || entity.getCropped() == 1) {
                model.getBone("tail_fluff_1").ifPresent(b -> b.setHidden(false));
                model.getBone("tail_fluff_2").ifPresent(b -> b.setHidden(false));
            } else if (entity.getCropped() >= 2) {
                model.getBone("tail_fluff_1").ifPresent(b -> b.setHidden(true));
                model.getBone("tail_fluff_2").ifPresent(b -> b.setHidden(true));
            }
        } else {
            model.getBone("body_fluff").ifPresent(b -> b.setHidden(true));
            model.getBone("tail_fluff_1").ifPresent(b -> b.setHidden(true));
            model.getBone("tail_fluff_2").ifPresent(b -> b.setHidden(true));
        }

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);

            if (entity.getCropped() == 0) { // no crop
                model.getBone("tail").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("left_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("tail2").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(true));
                model.getBone("left_ear").ifPresent(b -> b.setHidden(true));
            } else if (entity.getCropped() == 1) { // ears only
                model.getBone("tail").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear2").ifPresent(b -> b.setHidden(true));
                model.getBone("left_ear2").ifPresent(b -> b.setHidden(true));
                model.getBone("tail2").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(false));
                model.getBone("left_ear").ifPresent(b -> b.setHidden(false));
            } else if (entity.getCropped() == 2) { // tail only
                model.getBone("tail").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("left_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("tail2").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(true));
                model.getBone("left_ear").ifPresent(b -> b.setHidden(true));
            } else if (entity.getCropped() == 3) { // full crop
                model.getBone("tail").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear2").ifPresent(b -> b.setHidden(true));
                model.getBone("left_ear2").ifPresent(b -> b.setHidden(true));
                model.getBone("tail2").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(false));
                model.getBone("left_ear").ifPresent(b -> b.setHidden(false));
            } else {
                model.getBone("tail").ifPresent(b -> b.setHidden(false));
                model.getBone("right_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("left_ear2").ifPresent(b -> b.setHidden(false));
                model.getBone("tail2").ifPresent(b -> b.setHidden(true));
                model.getBone("right_ear").ifPresent(b -> b.setHidden(true));
                model.getBone("left_ear").ifPresent(b -> b.setHidden(true));
            }
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


