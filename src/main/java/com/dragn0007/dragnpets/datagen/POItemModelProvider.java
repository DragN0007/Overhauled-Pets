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
    protected void registerModels() {
        simpleItem(POItems.PETS_OVERHAUL);

        simpleItem(POItems.WOLF);
        simpleItem(POItems.COOKED_WOLF);
        simpleItem(POItems.CAT);
        simpleItem(POItems.COOKED_CAT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PetsOverhaul.MODID,"item/" + item.getId().getPath()));
    }
}