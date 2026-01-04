package com.dragn0007.dragnpets.compat.jade.breed;

import com.dragn0007.dragnlivestock.LivestockOverhaul;
import com.dragn0007.dragnpets.entities.dog.ODog;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class DogBreedTooltip implements IEntityComponentProvider {

    public DogBreedTooltip() {
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        if (entityAccessor.getEntity() instanceof ODog animal) {
            String breeds = getBreeds(animal.getBreed());
            tooltip.add(Component.translatable(breeds));
        }
    }

    private String getBreeds(int breed) {
        switch (breed) {
            case 0: return "Mutt";
            case 1: return "American Ridgeback";
            case 2: return "Australian Shepherd";
            case 3: return "Beagle";
            case 4: return "Bernese Mountain Dog";
            case 5: return "Bloodhound";
            case 6: return "Border Collie";
            case 7: return "Cocker Spaniel";
            case 8: return "Coonhound";
            case 9: return "Doberman Pinscher";
            case 10: return "Foxhound";
            case 11: return "Husky";
            case 12: return "Jack Russell Terrier";
            case 13: return "Labrador Retriever";
            case 14: return "Great Pyrenees";
            case 15: return "Rottweiler";
            case 16: return "Whippet";
            case 17: return "American Pitbull";
            case 18: return "German Shepherd";
            case 19: return "Borzoi";
            case 20: return "Newfoundland";
            case 21: return "Kangal Shepherd";
            case 22: return "Belgian Malinois";
            case 23: return "Tibetan Mastiff";
            case 24: return "Collie";
            default: return "Unknown";
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(LivestockOverhaul.MODID, "o_tooltips");
    }
}
