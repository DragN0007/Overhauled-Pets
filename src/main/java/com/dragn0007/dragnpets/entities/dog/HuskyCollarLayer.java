package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnpets.PetsOverhaul;
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
public class HuskyCollarLayer extends GeoRenderLayer<Husky> {
    public static final ResourceLocation[] TEXTURE_LOCATION = new ResourceLocation[]{
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/white.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/orange.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/magenta.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/light_blue.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/yellow.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/lime.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/pink.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/grey.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/light_grey.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/cyan.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/purple.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/blue.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/brown.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/green.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/red.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/black.png")
    };

    private static final ResourceLocation FEMALE_INDICATOR = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/female_indicator.png");
    private static final ResourceLocation MALE_INDICATOR = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/collar/male_indicator.png");


    public HuskyCollarLayer(GeoRenderer<Husky> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Husky animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getCollarColor();
        ResourceLocation resourceLocation = null;

        if (dyeColor != null) {
            resourceLocation = TEXTURE_LOCATION[dyeColor.getId()];
        }

        if (resourceLocation == null || !PetsOverhaulClientConfig.RENDER_COLLARS.get()) {
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
            ResourceLocation genderIndicator = animatable.isFemale() ? FEMALE_INDICATOR : MALE_INDICATOR;


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
