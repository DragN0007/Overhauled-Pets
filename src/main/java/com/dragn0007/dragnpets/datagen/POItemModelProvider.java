package com.dragn0007.dragnpets.datagen;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class POItemModelProvider extends ItemModelProvider {
    public POItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PetsOverhaul.MODID, existingFileHelper);
    }

    @Override
    public void registerModels() {
        simpleItem(POItems.PETS_OVERHAUL);

        simpleItem(POItems.WOLF);
        simpleItem(POItems.COOKED_WOLF);
        simpleItem(POItems.CAT);
        simpleItem(POItems.COOKED_CAT);
        simpleItem(POItems.PARROT_THIGH);
        simpleItem(POItems.COOKED_PARROT_THIGH);
        simpleItem(POItems.TROPICAL_FISH_FILLET);
        simpleItem(POItems.COOKED_TROPICAL_FISH_FILLET);

        simpleItem(POItems.TROPICAL_FISH_ROE);

        simpleItem(POItems.LEATHER_DOG_ARMOR);
        simpleItem(POItems.CHAINMAIL_DOG_ARMOR);
        simpleItem(POItems.COPPER_DOG_ARMOR);
        simpleItem(POItems.IRON_DOG_ARMOR);
        simpleItem(POItems.RIOT_DOG_ARMOR);
        simpleItem(POItems.QUARTZ_DOG_ARMOR);
        simpleItem(POItems.GOLD_DOG_ARMOR);
        simpleItem(POItems.EMERALD_DOG_ARMOR);
        simpleItem(POItems.DIAMOND_DOG_ARMOR);
        simpleItem(POItems.NETHERITE_DOG_ARMOR);
        simpleItem(POItems.OBSIDIAN_DOG_ARMOR);

        simpleItem(POItems.BLACK_VEST);
        simpleItem(POItems.BLUE_VEST);
        simpleItem(POItems.BROWN_VEST);
        simpleItem(POItems.CYAN_VEST);
        simpleItem(POItems.GREEN_VEST);
        simpleItem(POItems.GREY_VEST);
        simpleItem(POItems.LIGHT_BLUE_VEST);
        simpleItem(POItems.LIGHT_GREY_VEST);
        simpleItem(POItems.LIME_VEST);
        simpleItem(POItems.MAGENTA_VEST);
        simpleItem(POItems.ORANGE_VEST);
        simpleItem(POItems.PINK_VEST);
        simpleItem(POItems.PURPLE_VEST);
        simpleItem(POItems.RED_VEST);
        simpleItem(POItems.WHITE_VEST);
        simpleItem(POItems.YELLOW_VEST);
    }

    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PetsOverhaul.MODID,"item/" + item.getId().getPath()));
    }
}