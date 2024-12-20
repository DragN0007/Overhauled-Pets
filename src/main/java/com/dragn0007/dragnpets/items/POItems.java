package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class POItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PetsOverhaul.MODID);

    //Spawn Eggs
    public static final RegistryObject<Item> O_WOLF_SPAWN_EGG = ITEMS.register("o_wolf_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_WOLF_ENTITY, 0xbbbbbb, 0x6d6d6d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_OCELOT_SPAWN_EGG = ITEMS.register("o_ocelot_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_OCELOT_ENTITY, 0xf6d587, 0x5e381b, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_FOX_SPAWN_EGG = ITEMS.register("o_fox_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_FOX_ENTITY, 0xd5701c, 0x62483c, new Item.Properties().stacksTo(64)));


    //Food
    public static final RegistryObject<Item> WOLF = ITEMS.register("wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_WOLF = ITEMS.register("cooked_wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> CAT = ITEMS.register("cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAT = ITEMS.register("cooked_cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));


    //Mod Item Tab Icon (UNOBTAINABLE)
    public static final RegistryObject<Item> PETS_OVERHAUL = ITEMS.register("pets_overhaul",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}