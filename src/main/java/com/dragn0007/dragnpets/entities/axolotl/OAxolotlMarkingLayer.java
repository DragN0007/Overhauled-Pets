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
        NONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/fox/overlay/none.png")),
        BELLY_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_belly_black.png")),
        BELLY_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_belly_white.png")),
        DALMATION(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_dalmation.png")),
        FIREFLY_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_firefly_black.png")),
        FIREFLY_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_firefly_white.png")),
        MOSIAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_mosiac.png")),
        MOSIAC_2(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_mosiac_2.png")),
        PIEBALD_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_piebald_black.png")),
        PIEBALD_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_piebald_white.png")),
        PIEBALD_2_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_piebald_2_black.png")),
        PIEBALD_2_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_piebald_2_white.png")),
        SPLASH_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_splash_black.png")),
        SPLASH_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_splash_white.png")),
        SPOTTED_BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_spotted_black.png")),
        SPOTTED_WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/overlay/ov_spotted_white.png"));

        public final ResourceLocation resourceLocation;
        Overlay(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Overlay overlayFromOrdinal(int overlay) { return Overlay.values()[overlay % Overlay.values().length];
        }
    }

}
