package com.dragn0007.dragnpets.entities.cat.kornish_rex;

import com.dragn0007.dragnpets.entities.cat.OCatCollarLayer;
import com.dragn0007.dragnpets.util.PetsOverhaulClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

@OnlyIn(Dist.CLIENT)
public class KornishRexCollarLayer extends GeoRenderLayer<KornishRex> {


    public KornishRexCollarLayer(GeoRenderer<KornishRex> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, KornishRex animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getCollarColor();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = OCatCollarLayer.TEXTURE_LOCATION[dyeColor.getId()];
        }

        if (resourceLocation == null || !PetsOverhaulClientConfig.RENDER_COLLARS.get() || !animatable.isCollared()) {
            return;
        }

        RenderType renderType1 = RenderType.entityCutout(resourceLocation);
        poseStack.pushPose();
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0.0d, 0.0d, 0.0d);
        poseStack.popPose();
        getRenderer().reRender(getDefaultBakedModel(animatable),
                poseStack,
                bufferSource,
                animatable,
                renderType1,
                bufferSource.getBuffer(renderType1), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);


        if (PetsOverhaulClientConfig.COLLAR_GENDER_INDICATOR.get()) {
            ResourceLocation genderIndicator = animatable.isFemale() ? OCatCollarLayer.FEMALE_INDICATOR : OCatCollarLayer.MALE_INDICATOR;


            RenderType genderRenderType = RenderType.entityCutout(genderIndicator);
            poseStack.pushPose();
            poseStack.scale(1.0f, 1.0f, 1.0f);
            poseStack.translate(0.0d, 0.0d, 0.1d);
            poseStack.popPose();
            getRenderer().reRender(getDefaultBakedModel(animatable),
                    poseStack,
                    bufferSource,
                    animatable,
                    genderRenderType,
                    bufferSource.getBuffer(genderRenderType), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }
}
