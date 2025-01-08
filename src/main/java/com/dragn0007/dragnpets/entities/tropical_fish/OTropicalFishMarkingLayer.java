package com.dragn0007.dragnpets.entities.tropical_fish;

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

public class OTropicalFishMarkingLayer extends GeoRenderLayer<OTropicalFish> {
    public OTropicalFishMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OTropicalFish animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

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
        BLACK_FRONT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/black_front.png")),
        BLACK_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/black_spotted.png")),
        BLACK_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/black_striped.png")),
        WHITE_FRONT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/white_front.png")),
        WHITE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/white_spotted.png")),
        WHITE_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/tropical_fish/overlay/white_striped.png")),
        ;

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
