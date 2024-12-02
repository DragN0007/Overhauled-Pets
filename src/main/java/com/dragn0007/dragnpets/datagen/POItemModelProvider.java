package com.dragn0007.dragnpets.datagen;

import com.dragn0007.dragnpets.PetsOverhaul;
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

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PetsOverhaul.MODID,"item/" + item.getId().getPath()));
    }
}