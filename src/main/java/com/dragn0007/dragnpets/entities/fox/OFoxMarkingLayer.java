package com.dragn0007.dragnpets.entities.fox;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OFoxMarkingLayer extends GeoRenderLayer<OFox> {
    public OFoxMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OFox animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayResource());
        poseStack.pushPose();
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);
        poseStack.popPose();
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderMarkingType,
                bufferSource.getBuffer(renderMarkingType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
    }

    public enum Overlay {
        NONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/none.png")),
        GOLD_CROSS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_gold_cross.png")),
        MARBLED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_marbled.png")),
        PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_piebald.png")),
        SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_spotted.png")),
        STANDARD_CROSS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_standard_cross.png")),
        WHITE_MARK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/ov_white_mark.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
