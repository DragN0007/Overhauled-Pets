package com.dragn0007.dragnpets.items;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.items.custom.TropicalFishRoeItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
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
    public static final RegistryObject<Item> O_AXOLOTL_SPAWN_EGG = ITEMS.register("o_axolotl_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_AXOLOTL_ENTITY, 0xeed0cb, 0xc7a49e, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_TROPICAL_FISH_SPAWN_EGG = ITEMS.register("o_tropical_fish_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_TROPICAL_FISH_ENTITY, 0xce7b22, 0xffffff, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> O_CAT_SPAWN_EGG = ITEMS.register("o_cat_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.O_CAT_ENTITY, 0xd5913f, 0xa7631a, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MACAW_SPAWN_EGG = ITEMS.register("macaw_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.MACAW_ENTITY, 0xab2f23, 0xda9e35, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> COCKATIEL_SPAWN_EGG = ITEMS.register("cockatiel_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.COCKATIEL_ENTITY, 0x9d9d9d, 0xfff469, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> RINGNECK_SPAWN_EGG = ITEMS.register("ringneck_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.RINGNECK_ENTITY, 0xa3a855, 0x2f3515, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DOBERMAN_SPAWN_EGG = ITEMS.register("doberman_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityTypes.DOBERMAN_ENTITY, 0x232323, 0x8c4716, new Item.Properties().stacksTo(64)));


    //Food
    public static final RegistryObject<Item> WOLF = ITEMS.register("wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_WOLF = ITEMS.register("cooked_wolf",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(1).build())));
    public static final RegistryObject<Item> CAT = ITEMS.register("cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_CAT = ITEMS.register("cooked_cat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1).build())));
    public static final RegistryObject<Item> PARROT_THIGH = ITEMS.register("parrot_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1).build())));
    public static final RegistryObject<Item> COOKED_PARROT_THIGH = ITEMS.register("cooked_parrot_thigh",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1).build())));
    public static final RegistryObject<Item> TROPICAL_FISH_FILLET = ITEMS.register("tropical_fish_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).build())));
    public static final RegistryObject<Item> COOKED_TROPICAL_FISH_FILLET = ITEMS.register("cooked_tropical_fish_fillet",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> TROPICAL_FISH_ROE = ITEMS.register("tropical_fish_roe",
            () -> new TropicalFishRoeItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).build())));

    public static final RegistryObject<Item> O_AXOLOTL_BUCKET = ITEMS.register("o_axolotl_bucket",
            () -> new MobBucketItem(EntityTypes.O_AXOLOTL_ENTITY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_AXOLOTL, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> O_TROPICAL_FISH_BUCKET = ITEMS.register("o_tropical_fish_bucket",
            () -> new MobBucketItem(EntityTypes.O_TROPICAL_FISH_ENTITY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_FILL_FISH, new Item.Properties().stacksTo(1)));


    //Mod Item Tab Icon (UNOBTAINABLE)
    public static final RegistryObject<Item> PETS_OVERHAUL = ITEMS.register("pets_overhaul",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}