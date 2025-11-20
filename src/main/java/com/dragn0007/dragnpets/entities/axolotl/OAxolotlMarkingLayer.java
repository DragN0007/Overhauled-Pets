package com.dragn0007.dragnpets.entities.axolotl;

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

public class OAxolotlMarkingLayer extends GeoRenderLayer<OAxolotl> {
    public OAxolotlMarkingLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, OAxolotl animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

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
        // old (1.0 release)
        NONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/none.png")),
        BELLY_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_belly.png")),
        BELLY_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_belly.png")),
        DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_dalamation.png")),
        FIREFLY_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_firefly.png")),
        FIREFLY_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_firefly.png")),
        MOSIAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mosiac.png")),
        MOSIAC_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/complex_mosiac.png")),
        PIEBALD_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_piebald.png")),
        PIEBALD_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_piebald.png")),
        PIEBALD_2_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_pinto.png")),
        PIEBALD_2_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_pinto.png")),
        SPLASH_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_splash.png")),
        SPLASH_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_splash.png")),
        SPOTTED_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/black_spotted.png")),
        SPOTTED_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/white_spotted.png")),

        // new (2.0 update)
        BLUE_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_belly.png")),
        BLUE_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_dalamation.png")),
        BLUE_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_firefly.png")),
        BLUE_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_piebald.png")),
        BLUE_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_pinto.png")),
        BLUE_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_splash.png")),
        BLUE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/blue_spotted.png")),

        BROWN_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_belly.png")),
        BROWN_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_dalamation.png")),
        BROWN_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_firefly.png")),
        BROWN_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_piebald.png")),
        BROWN_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_pinto.png")),
        BROWN_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_splash.png")),
        BROWN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/brown_spotted.png")),

        CHOCOLATE_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_belly.png")),
        CHOCOLATE_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_dalamation.png")),
        CHOCOLATE_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_firefly.png")),
        CHOCOLATE_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_piebald.png")),
        CHOCOLATE_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_pinto.png")),
        CHOCOLATE_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_splash.png")),
        CHOCOLATE_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/chocolate_spotted.png")),

        CREAM_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_belly.png")),
        CREAM_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_dalamation.png")),
        CREAM_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_firefly.png")),
        CREAM_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_piebald.png")),
        CREAM_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_pinto.png")),
        CREAM_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_splash.png")),
        CREAM_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/cream_spotted.png")),

        FAWN_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_belly.png")),
        FAWN_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_dalamation.png")),
        FAWN_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_firefly.png")),
        FAWN_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_piebald.png")),
        FAWN_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_pinto.png")),
        FAWN_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_splash.png")),
        FAWN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/fawn_spotted.png")),

        GOLD_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_belly.png")),
        GOLD_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_dalamation.png")),
        GOLD_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_firefly.png")),
        GOLD_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_piebald.png")),
        GOLD_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_pinto.png")),
        GOLD_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_splash.png")),
        GOLD_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/gold_spotted.png")),

        LILAC_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_belly.png")),
        LILAC_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_dalamation.png")),
        LILAC_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_firefly.png")),
        LILAC_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_piebald.png")),
        LILAC_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_pinto.png")),
        LILAC_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_splash.png")),
        LILAC_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/lilac_spotted.png")),

        MAHOGANY_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_belly.png")),
        MAHOGANY_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_dalamation.png")),
        MAHOGANY_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_firefly.png")),
        MAHOGANY_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_piebald.png")),
        MAHOGANY_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_pinto.png")),
        MAHOGANY_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_splash.png")),
        MAHOGANY_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/mahogany_spotted.png")),

        RED_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_belly.png")),
        RED_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_dalamation.png")),
        RED_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_firefly.png")),
        RED_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_piebald.png")),
        RED_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_pinto.png")),
        RED_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_splash.png")),
        RED_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/red_spotted.png")),

        SEAL_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_belly.png")),
        SEAL_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_dalamation.png")),
        SEAL_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_firefly.png")),
        SEAL_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_piebald.png")),
        SEAL_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_pinto.png")),
        SEAL_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_splash.png")),
        SEAL_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/seal_spotted.png")),

        SILVER_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_belly.png")),
        SILVER_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_dalamation.png")),
        SILVER_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_firefly.png")),
        SILVER_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_piebald.png")),
        SILVER_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_pinto.png")),
        SILVER_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_splash.png")),
        SILVER_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/silver_spotted.png")),

        TAN_BELLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_belly.png")),
        TAN_DALAMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_dalamation.png")),
        TAN_FIREFLY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_firefly.png")),
        TAN_PIEBALD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_piebald.png")),
        TAN_PIEBALD_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_pinto.png")),
        TAN_SPLASH(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_splash.png")),
        TAN_SPOTTED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/tan_spotted.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
