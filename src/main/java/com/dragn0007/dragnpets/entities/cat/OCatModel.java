package com.dragn0007.dragnpets.entities.cat;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OCatModel extends DefaultedEntityGeoModel<OCat> {

    public OCatModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_cat"), true);
    }

    @Override
    public void setCustomAnimations(OCat animatable, long instanceId, AnimationState<OCat> animationState) {

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
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/brown.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/chocolate.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/cream.png")),
        GOLD_RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/gold_red.png")),
        LILAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/lilac.png")),
        MAHOGANY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/mahogany.png")),
        ORANGE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/orange.png")),
        RAGDOLL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/ragdoll.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/red.png")),
        SEAL(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/seal.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/silver.png")),
        TAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/tan.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/cat/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

//    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/cat/o_cat.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/o_cat.animation.json");

    @Override
    public ResourceLocation getModelResource(OCat object) {
        return CatBreed.breedFromOrdinal(object.getBreed()).resourceLocation;
    }

    @Override
    public ResourceLocation getTextureResource(OCat object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OCat animatable) {
        return ANIMATION;
    }
}

