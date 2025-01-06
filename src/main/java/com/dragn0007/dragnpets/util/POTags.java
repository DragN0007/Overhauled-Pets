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


        public static final TagKey<Item> TRAINING_TREAT = forgeTag("training_treat");

        public static TagKey<Item> forgeTag (String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Entity_Types {



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
