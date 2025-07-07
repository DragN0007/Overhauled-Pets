package com.dragn0007.dragnpets.entities.dog.bernese;

import com.dragn0007.dragnpets.PetsOverhaul;
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
public class BerneseDecorLayer extends GeoRenderLayer<Bernese> {
    public static final ResourceLocation[] VEST = new ResourceLocation[]{
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/white.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/orange.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/magenta.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/light_blue.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/yellow.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/lime.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/pink.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/grey.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/light_grey.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/cyan.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/purple.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/blue.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/brown.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/green.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/red.png"),
            new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/vest/black.png")
    };


    public BerneseDecorLayer(GeoRenderer<Bernese> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, Bernese animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        DyeColor dyeColor = animatable.getVestColor();
        ResourceLocation resourceLocation = null;

        if (animatable.hasVest()) {
            if (dyeColor != null) {
                resourceLocation = VEST[dyeColor.getId()];
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
        }
    }
}
