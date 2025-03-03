package com.dragn0007.dragnpets.entities.cat.kornish_rex;

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

public class KornishRexMarkingLayer extends GeoRenderLayer<KornishRex> {
    public KornishRexMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, KornishRex animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

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
        BLACK_STRIPES(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_black_stripes.png")),
        CALICO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_calico.png")),
        JELLIE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_jellie.png")),
        MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_mittens.png")),
        WHITE_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_white_belly.png")),
        WHITE_STRIPES(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/ov_white_stripes.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
