package com.dragn0007.dragnpets.entities.wolf;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnlivestock.entities.rabbit.ORabbit;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OWolfModel extends DefaultedEntityGeoModel<OWolf> {

    public OWolfModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_wolf"), true);
    }

    @Override
    public void setCustomAnimations(OWolf animatable, long instanceId, AnimationState<OWolf> animationState) {

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
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/black.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/brown.png")),
        CHOCOLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/chocolate.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/cream.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/grey.png")),
        MAHOGANY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/mahogany.png")),
        RED(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/red.png")),
        TIMBER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/timber.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/wolf/white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/o_wolf.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/o_wolf.animation.json");

    @Override
    public ResourceLocation getModelResource(OWolf object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OWolf object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OWolf animatable) {
        return ANIMATION;
    }
}

