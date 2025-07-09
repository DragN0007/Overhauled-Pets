package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.dog.CommonDog;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class CockatielModel extends DefaultedEntityGeoModel<Cockatiel> {

    public CockatielModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "cockatiel"), true);
    }

    @Override
    public void setCustomAnimations(Cockatiel animatable, long instanceId, AnimationState<Cockatiel> animationState) {

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
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_grey.png")),
        PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_pink.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/cockatiel/cockatiel_white.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/cockatiel.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/cockatiel.animation.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/parrot_baby.png");

    @Override
    public ResourceLocation getModelResource(Cockatiel object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Cockatiel object) {
        if (object.isBaby()) {
            return BABY_TEXTURE;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Cockatiel animatable) {
        return ANIMATION;
    }
}

