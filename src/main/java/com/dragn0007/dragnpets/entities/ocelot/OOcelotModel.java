package com.dragn0007.dragnpets.entities.ocelot;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OOcelotModel extends DefaultedEntityGeoModel<OOcelot> {

    public OOcelotModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o_ocelot"), true);
    }

    @Override
    public void setCustomAnimations(OOcelot animatable, long instanceId, AnimationState<OOcelot> animationState) {

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
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/black.png")),
        CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/cream.png")),
        LILAC(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/lilac.png")),
        SILVER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/silver.png")),
        WHITE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/white.png")),
        YELLOW(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/ocelot/yellow.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static OOcelotModel.Variant variantFromOrdinal(int variant) { return OOcelotModel.Variant.values()[variant % OOcelotModel.Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/o_ocelot.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/o_cat.animation.json");

    @Override
    public ResourceLocation getModelResource(OOcelot object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OOcelot object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OOcelot animatable) {
        return ANIMATION;
    }
}

