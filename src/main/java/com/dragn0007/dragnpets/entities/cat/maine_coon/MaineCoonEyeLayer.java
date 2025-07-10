package com.dragn0007.dragnpets.entities.cat.maine_coon;

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

public class MaineCoonEyeLayer extends GeoRenderLayer<MaineCoon> {
    public MaineCoonEyeLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, MaineCoon animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        RenderType renderMarkingType = RenderType.entityCutout(animatable.getEyeLocation());
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

    public enum Eyes {
        ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/orange.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/yellow.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/brown.png")),
        DARK_BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/dark_brown.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/blue.png")),
        GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/eyes/green.png"));

        public final ResourceLocation resourceLocation;
        Eyes(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Eyes overlayFromOrdinal(int overlay) { return Eyes.values()[overlay % Eyes.values().length];
        }
    }

}
