package com.dragn0007.dragnpets.entities.parrot;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RingneckModel extends DefaultedEntityGeoModel<Ringneck> {

    public RingneckModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "ringneck"), true);
    }

    @Override
    public void setCustomAnimations(Ringneck animatable, long instanceId, AnimationState<Ringneck> animationState) {

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
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_blue.png")),
        CINNAMON(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_cinnamon.png")),
        CYAN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_cyan.png")),
        GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_green.png")),
        LIGHT_BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_light_blue.png")),
        POWDER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_powder.png")),
        SLATE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_slate.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/ringneck/ringneck_yellow.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/ringneck.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/ringneck.animation.json");
    public static final ResourceLocation BABY_TEXTURE = new ResourceLocation(PetsOverhaul.MODID, "textures/entity/parrot/parrot_baby.png");

    @Override
    public ResourceLocation getModelResource(Ringneck object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(Ringneck object) {
        if (object.isBaby()) {
            return BABY_TEXTURE;
        }
        return object.getTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(Ringneck animatable) {
        return ANIMATION;
    }
}

