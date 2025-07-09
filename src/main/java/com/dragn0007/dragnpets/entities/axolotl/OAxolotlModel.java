package com.dragn0007.dragnpets.entities.axolotl;

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

public class OAxolotlModel extends DefaultedEntityGeoModel<OAxolotl> {

    public OAxolotlModel() {
        super(new ResourceLocation(LivestockOverhaul.MODID, "o-axolotl"), true);
    }

    @Override
    public void setCustomAnimations(OAxolotl animatable, long instanceId, AnimationState<OAxolotl> animationState) {

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
        ALBINO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_albino.png")),
        BLACK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_black.png")),
        BLUE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_blue.png")),
        BROWN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_brown.png")),
        COPPER(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_copper.png")),
        GOLD(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_gold.png")),
        GREEN(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_green.png")),
        GREY(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_grey.png")),
        PINK(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_pink.png")),
        PURPLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_purple.png")),
        STONE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_stone.png")),
        MELANOID_ALBINO(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_melanoid_albino.png")),
        BLUE_CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_blue_cream.png")),
        BURGUNDY_CREAM(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_burgundy_cream.png")),
        GALAXY_PURPLE(new ResourceLocation(PetsOverhaul.MODID, "textures/entity/axolotl/axolotl_galaxy_purple.png"));

        public final ResourceLocation resourceLocation;
        Variant(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public static Variant variantFromOrdinal(int variant) { return Variant.values()[variant % Variant.values().length];
        }
    }

    public static final ResourceLocation MODEL = new ResourceLocation(PetsOverhaul.MODID, "geo/axolotl_overhaul.geo.json");
    public static final ResourceLocation ANIMATION = new ResourceLocation(PetsOverhaul.MODID, "animations/axolotl_overhaul.animation.json");

    @Override
    public ResourceLocation getModelResource(OAxolotl object) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(OAxolotl object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(OAxolotl animatable) {
        return ANIMATION;
    }
}

