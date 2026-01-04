package com.dragn0007.dragnpets.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.entities.cat.OCat;
import com.dragn0007.dragnpets.entities.dog.ODog;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class CatBreedTooltip implements IEntityComponentProvider {

    public CatBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof OCat animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Shorthair";
            case 1: return "Kornish Rex";
            case 2: return "Maine Coon";
            case 3: return "Manx";
            case 4: return "Japanese Bobtail";
            case 5: return "Norwegian Forest";
            case 6: return "Oriental Longhair";
            case 7: return "Oriental Shorthair";
            case 8: return "Ragdoll";
            case 9: return "Russian Blue";
            case 10: return "Abyssinian";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
