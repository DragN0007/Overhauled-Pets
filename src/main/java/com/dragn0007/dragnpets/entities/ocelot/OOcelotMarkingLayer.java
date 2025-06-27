package com.dragn0007.dragnpets.entities.ocelot;

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

public class OOcelotMarkingLayer extends GeoRenderLayer<OOcelot> {
    public OOcelotMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OOcelot animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

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
        BLACK_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_colorpoint.png")),
        BLACK_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_mittens.png")),
        BLACK_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_spotted.png")),
        BLACK_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_stripe.png")),
        BLUE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_colorpoint.png")),
        BLUE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_mittens.png")),
        BLUE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_spotted.png")),
        BLUE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_stripe.png")),
        BROWN_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_colorpoint.png")),
        BROWN_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_mittens.png")),
        BROWN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_spotted.png")),
        BROWN_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_stripe.png")),
        CHOCOLATE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_colorpoint.png")),
        CHOCOLATE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_mittens.png")),
        CHOCOLATE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_spotted.png")),
        CHOCOLATE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_stripe.png")),
        CREAM_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_mittens.png")),
        CREAM_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_spotted.png")),
        CREAM_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_stripe.png")),
        LILAC_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_colorpoint.png")),
        LILAC_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_mittens.png")),
        LILAC_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_spotted.png")),
        LILAC_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_tabby.png")),
        MAHOGANY_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_colorpoint.png")),
        MAHOGANY_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_mittens.png")),
        MAHOGANY_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_spotted.png")),
        MAHOGANY_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_stripe.png")),
        RED_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_colorpoint.png")),
        RED_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_mittens.png")),
        RED_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_spotted.png")),
        RED_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_stripe.png")),
        SEAL_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_colorpoint.png")),
        SEAL_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_mittens.png")),
        SEAL_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_spotted.png")),
        SEAL_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_stripe.png")),
        SILVER_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_colorpoint.png")),
        SILVER_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_mittens.png")),
        SILVER_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_spotted.png")),
        SILVER_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_stripe.png")),
        TAN_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_colorpoint.png")),
        TAN_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_mittens.png")),
        TAN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_spotted.png")),
        TAN_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_stripe.png")),
        WHITE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_colorpoint.png")),
        WHITE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_mittens.png")),
        WHITE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_spotted.png")),
        WHITE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_stripe.png")),
        PURE_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/pure_white.png")),
        TUXEDO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tuxedo.png")),
        ;

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
