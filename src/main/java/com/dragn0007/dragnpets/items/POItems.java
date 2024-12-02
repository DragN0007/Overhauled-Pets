package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class POItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PetsOverhaul.MODID);

    public static final RegistryObject<Item> O_WOLF_SPAWN_EGG = ITEMS.register("o_wolf_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_WOLF_ENTITY, 0xbbbbbb, 0x6d6d6d, new Item.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}