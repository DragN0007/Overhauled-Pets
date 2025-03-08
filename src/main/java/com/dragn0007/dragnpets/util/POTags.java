package com.dragn0007.dragnpets.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class POTags {

    public static class Items {

        public static final TagKey<Item> DOG_FOOD = forgeTag("dog_food");
        public static final TagKey<Item> CAT_FOOD = forgeTag("cat_food");
        public static final TagKey<Item> FOX_FOOD = forgeTag("fox_food");
        public static final TagKey<Item> AXOLOTL_FOOD = forgeTag("axolotl_food");
        public static final TagKey<Item> PARROT_FOOD = forgeTag("parrot_food");

        public static final TagKey<Item> CAT_GIFT = forgeTag("cat_gift");
        public static final TagKey<Item> TRAINING_TREAT = forgeTag("training_treat");

        public static final TagKey<Item> ANIMAL_LOOT = forgeTag("animal_loot"); //picked up by retrieving dogs and some hunting dogs

        public static TagKey<Item> forgeTag (String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Entity_Types {
        public static final TagKey<EntityType<?>> O_WOLVES = forgeTag("o_wolves");
        public static final TagKey<EntityType<?>> WOLVES = forgeTag("wolves");
        public static final TagKey<EntityType<?>> O_FOXES = forgeTag("o_foxes");
        public static final TagKey<EntityType<?>> FOXES = forgeTag("foxes");
        public static final TagKey<EntityType<?>> CATS = forgeTag("cats");
        public static final TagKey<EntityType<?>> HERDING_DOGS = forgeTag("herding_dogs");
        public static final TagKey<EntityType<?>> DOGS = forgeTag("dogs");
        public static final TagKey<EntityType<?>> HUNTING_DOGS = forgeTag("hunting_dogs");
        public static final TagKey<EntityType<?>> GUARDIAN_DOGS_ATTACK = forgeTag("guardian_dogs_attack");
        public static final TagKey<EntityType<?>> GAME = forgeTag("game");
        public static final TagKey<EntityType<?>> FOXES_HUNT = forgeTag("foxes_hunt");
        public static final TagKey<EntityType<?>> CATS_HUNT = forgeTag("cats_hunt");

        public static TagKey<EntityType<?>> forgeTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {



        public static TagKey<Block> forgeTag (String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

}
