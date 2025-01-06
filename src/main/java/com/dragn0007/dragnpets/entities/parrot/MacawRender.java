package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnpets.entities.ocelot.OOcelot;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotCollarLayer;
import com.dragn0007.dragnpets.entities.ocelot.OOcelotModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MacawRender extends GeoEntityRenderer<Macaw> {

    public MacawRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MacawModel());
    }

    @Override
    public void render(Macaw entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


