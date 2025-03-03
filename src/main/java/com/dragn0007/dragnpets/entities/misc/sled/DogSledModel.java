package com.dragn0007.dragnpets.entities.misc.sled;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DogSledModel<T extends DogSled> extends EntityModel<T> {
	public final ModelPart sled;
	public final ModelPart chest1;
	public final ModelPart chest1top;
	public final ModelPart chest2;
	public final ModelPart attachment1;
	public final ModelPart attachment2;
	public final ModelPart attachment3;

	public DogSledModel(ModelPart root) {
		this.sled = root.getChild("sled");
		this.chest1 = root.getChild("chest1");
		this.chest1top = root.getChild("chest1top");
		this.chest2 = root.getChild("chest2");
		this.attachment1 = root.getChild("attachment1");
		this.attachment2 = root.getChild("attachment2");
		this.attachment3 = root.getChild("attachment3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition sled = partdefinition.addOrReplaceChild("sled", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-11.0F, -2.0F, -22.0F, 22.0F, 2.0F, 42.0F, new CubeDeformation(0.01F))
				.texOffs(128, 27).addBox(-11.0F, -14.0F, 3.0F, 22.0F, 12.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(48, 144).addBox(-11.0F, -11.0F, 18.0F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(88, 148).addBox(-10.0F, -9.0F, 18.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(80, 148).addBox(8.0F, -9.0F, 18.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(140, 143).addBox(-11.0F, -7.0F, 18.0F, 22.0F, 5.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(64, 148).addBox(8.0F, -16.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(72, 148).addBox(-10.0F, -16.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(0, 144).addBox(-11.0F, -18.0F, 3.0F, 22.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(0, 44).addBox(8.0F, 2.0F, -25.0F, 2.0F, 2.0F, 48.0F, new CubeDeformation(0.01F))
				.texOffs(0, 148).addBox(8.0F, 0.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(8, 148).addBox(-10.0F, 0.0F, 21.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(16, 148).addBox(-10.0F, 0.0F, 13.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(0, 94).addBox(-10.0F, 2.0F, -25.0F, 2.0F, 2.0F, 48.0F, new CubeDeformation(0.01F))
				.texOffs(24, 148).addBox(-10.0F, 0.0F, -25.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(32, 148).addBox(-10.0F, 0.0F, -17.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(40, 148).addBox(8.0F, 0.0F, -25.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(48, 148).addBox(8.0F, 0.0F, 21.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(56, 148).addBox(8.0F, 0.0F, 13.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)),
				PartPose.offset(0.0F, 20.0F, 9.0F));

		PartDefinition chest1 = partdefinition.addOrReplaceChild("chest1", CubeListBuilder.create().texOffs(181, 124).addBox(-10.0F, -7.0F, -3.0F, 20.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 3.0F));

		PartDefinition chest1top = partdefinition.addOrReplaceChild("chest1top", CubeListBuilder.create().texOffs(100, 120).addBox(-10.0F, -11.0F, -3.0F, 20.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(152, 128).addBox(-1.0F, -8.0F, -4.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, 3.0F));

		PartDefinition chest2 = partdefinition.addOrReplaceChild("chest2", CubeListBuilder.create().texOffs(100, 143).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 18.0F, -7.0F));

		PartDefinition attachment1 = partdefinition.addOrReplaceChild("attachment1", CubeListBuilder.create().texOffs(100, 44).addBox(-1.0F, -0.7625F, -35.75F, 2.0F, 2.0F, 36.0F, new CubeDeformation(0.0F))
				.texOffs(128, 41).addBox(-8.0F, -0.7375F, -4.75F, 16.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.7625F, -13.25F));

		PartDefinition attachment2 = partdefinition.addOrReplaceChild("attachment2", CubeListBuilder.create().texOffs(100, 82).addBox(-1.0F, -0.7625F, -71.75F, 2.0F, 2.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.7625F, -13.25F));

		PartDefinition attachment3 = partdefinition.addOrReplaceChild("attachment3", CubeListBuilder.create().texOffs(128, 0).addBox(-1.0F, -0.7625F, -96.75F, 2.0F, 2.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.7625F, -13.25F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T dogSled, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

		boolean fiveOrMoreDogsHitched = dogSled.DOGS_HITCHED >= 5;
		boolean threeOrMoreDogsHitched = dogSled.DOGS_HITCHED >= 3 && dogSled.DOGS_HITCHED < 5;

		if (dogSled.DOGS_HITCHED >= 5) {
			attachment3.visible = fiveOrMoreDogsHitched;
			attachment2.visible = true;
			attachment1.visible = true;
		} else if (dogSled.DOGS_HITCHED >= 3 && dogSled.DOGS_HITCHED < 5) {
			attachment2.visible = threeOrMoreDogsHitched;
			attachment1.visible = true;
			attachment3.visible = false;
		} else {
			attachment3.visible = false;
			attachment2.visible = false;
			attachment1.visible = true;
		}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		sled.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		chest1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		chest1top.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		chest2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		attachment1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		attachment2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		attachment3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}