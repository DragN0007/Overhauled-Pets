package com.dragn0007.dragnpets.entities.fox;

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

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getOverlayLocation());
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
        BLACK_FULL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/black_full.png")),
        BLACK_HALF(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/black_half.png")),
        BLACK_MINIMAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/black_minimal.png")),
        BLUE_FULL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/blue_full.png")),
        BLUE_HALF(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/blue_half.png")),
        BLUE_MINIMAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/blue_minimal.png")),
        CREAM_FULL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/cream_full.png")),
        CREAM_HALF(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/cream_half.png")),
        CREAM_MINIMAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/cream_minimal.png")),
        SILVER_FULL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/silver_full.png")),
        SILVER_HALF(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/silver_half.png")),
        SILVER_MINIMAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/silver_minimal.png")),
        ;

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
