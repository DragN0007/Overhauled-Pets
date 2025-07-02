package com.dragn0007.dragnpets.entities.dog;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CommonDogModel extends DefaultedEntityGeoModel<CommonDog> {

    public CommonDogModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_dog"), true);
    }

    @Override
    public void setCustomAnimations(CommonDog animatable, long instanceId, AnimationState<CommonDog> animationState) {

        CoreGeoBone neck = getAnimationProcessor().getBone("neck");
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (neck != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            neck.setRotX(neck.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            neck.setRotY(neck.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(head.getRotX() + (entityData.headPitch() * Mth.DEG_TO_RAD));
            float maxYaw = Mth.clamp(entityData.netHeadYaw(), -25.0f, 25.0f);
            head.setRotY(head.getRotY() + (maxYaw * Mth.DEG_TO_RAD));
        }
    }

    public enum Variant {
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/brown.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/chocolate.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/cream.png")),
        FAWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/fawn.png")),
        GOLD_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/gold_red.png")),
        LILAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/lilac.png")),
        MAHOGANY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/mahogany.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/red.png")),
        SEAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/seal.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/silver.png")),
        TAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/tan.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/dog/o_dog.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/dog.animation.json");

    @Override
    public ResourceLocation getModelResource(CommonDog object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(CommonDog object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(CommonDog animatable) {
        return ANIMATION;
    }
}

