package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MacawOnShoulderLayer<T extends Player>
        extends RenderLayer<T, PlayerModel<T>> {

    private final MacawRender geckoRenderer;
    private Macaw leftDummy;
    private Macaw rightDummy;

    public MacawOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> parent, EntityRendererProvider.Context ctx) {
        super(parent);
        this.geckoRenderer = new MacawRender(ctx);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        renderOneShoulder(poseStack, buffer, packedLight, player, partialTicks, true);
        renderOneShoulder(poseStack, buffer, packedLight, player, partialTicks, false);
    }

    private void renderOneShoulder(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T player, float partialTicks, boolean left) {
        CompoundTag tag = left ? player.getShoulderEntityLeft() : player.getShoulderEntityRight();
        if (tag.isEmpty()) return;
        Macaw dummy = left ? leftDummy : rightDummy;
        if (dummy == null) {
            dummy = new Macaw(POEntityTypes.MACAW_ENTITY.get(), player.level());
            if (left) leftDummy = dummy; else rightDummy = dummy;
        }
        if (EntityType.byString(tag.getString("id")).filter(t -> t == POEntityTypes.MACAW_ENTITY.get()).isEmpty()) {
            return;
        }
        dummy.setRidingShoulder(true);
        dummy.readAdditionalSaveData(tag);
        dummy.tickCount = player.tickCount;
        dummy.setYHeadRot(player.getYHeadRot());

        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        if (left) {
            if (player.isCrouching()) {
                poseStack.translate(0.0F, -0.2F, 0.0F);
            }
            poseStack.translate(0.4F, 0.0F, 0.0F);
        } else {
            if (player.isCrouching()) {
                poseStack.translate(0.0F, -0.2F, 0.0F);
            }
            poseStack.translate(-0.4F, 0.0F, 0.0F);
        }
        poseStack.scale(1F, 1F, 1F);
        geckoRenderer.render(dummy, 0f, partialTicks, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
