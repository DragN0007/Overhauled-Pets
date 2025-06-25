package com.dragn0007.dragnpets.entities.cat;

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

public class OCatMarkingLayer extends GeoRenderLayer<OCat> {
    public OCatMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OCat animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

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
        BLACK_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_splotched.png")),
        BLACK_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_spotted.png")),
        BLACK_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_stripe.png")),
        BLACK_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_striped.png")),
        BLACK_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/black_tabby.png")),
        BLUE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_colorpoint.png")),
        BLUE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_mittens.png")),
        BLUE_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_splotched.png")),
        BLUE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_spotted.png")),
        BLUE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_stripe.png")),
        BLUE_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_striped.png")),
        BLUE_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/blue_tabby.png")),
        BROWN_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_colorpoint.png")),
        BROWN_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_mittens.png")),
        BROWN_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_splotched.png")),
        BROWN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_spotted.png")),
        BROWN_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_stripe.png")),
        BROWN_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_striped.png")),
        BROWN_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/brown_tabby.png")),
        CHOCOLATE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_colorpoint.png")),
        CHOCOLATE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_mittens.png")),
        CHOCOLATE_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_splotched.png")),
        CHOCOLATE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_spotted.png")),
        CHOCOLATE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_stripe.png")),
        CHOCOLATE_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_striped.png")),
        CHOCOLATE_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/chocolate_tabby.png")),
        CREAM_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_mittens.png")),
        CREAM_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_splotched.png")),
        CREAM_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_spotted.png")),
        CREAM_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_stripe.png")),
        CREAM_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_striped.png")),
        CREAM_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/cream_tabby.png")),
        LILAC_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_colorpoint.png")),
        LILAC_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_mittens.png")),
        LILAC_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_splotched.png")),
        LILAC_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_spotted.png")),
        LILAC_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_stripe.png")),
        LILAC_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_striped.png")),
        LILAC_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/lilac_tabby.png")),
        MAHOGANY_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_colorpoint.png")),
        MAHOGANY_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_mittens.png")),
        MAHOGANY_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_splotched.png")),
        MAHOGANY_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_spotted.png")),
        MAHOGANY_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_stripe.png")),
        MAHOGANY_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_striped.png")),
        MAHOGANY_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/mahogany_tabby.png")),
        RED_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_colorpoint.png")),
        RED_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_mittens.png")),
        RED_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_splotched.png")),
        RED_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_spotted.png")),
        RED_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_stripe.png")),
        RED_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_striped.png")),
        RED_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/red_tabby.png")),
        SEAL_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_colorpoint.png")),
        SEAL_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_mittens.png")),
        SEAL_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_splotched.png")),
        SEAL_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_spotted.png")),
        SEAL_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_stripe.png")),
        SEAL_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_striped.png")),
        SEAL_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/seal_tabby.png")),
        SILVER_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_colorpoint.png")),
        SILVER_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_mittens.png")),
        SILVER_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_splotched.png")),
        SILVER_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_spotted.png")),
        SILVER_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_stripe.png")),
        SILVER_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_striped.png")),
        SILVER_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/silver_tabby.png")),
        TAN_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_colorpoint.png")),
        TAN_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_mittens.png")),
        TAN_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_splotched.png")),
        TAN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_spotted.png")),
        TAN_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_stripe.png")),
        TAN_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_striped.png")),
        TAN_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/tan_tabby.png")),
        WHITE_COLORPOINT(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_colorpoint.png")),
        WHITE_MITTENS(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_mittens.png")),
        WHITE_SPLOTCHED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_splotched.png")),
        WHITE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_spotted.png")),
        WHITE_STRIPE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_stripe.png")),
        WHITE_STRIPED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_striped.png")),
        WHITE_TABBY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/white_tabby.png")),
        PURE_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/pure_white.png")),
        CALICO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/common/overlay/calico.png")),
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
