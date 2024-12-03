package com.dragn0007.dragnpets.datagen;

import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class PORecipeMaker extends RecipeProvider implements IConditionBuilder {
    public PORecipeMaker(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 100)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 200)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.WOLF.get()), RecipeCategory.MISC, POItems.COOKED_WOLF.get(), 0.35F, 600)
                .unlockedBy("has_wolf", has(POItems.WOLF.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_wolf_campfire_cooking"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 100)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_smoking"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 200)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(POItems.CAT.get()), RecipeCategory.MISC, POItems.COOKED_CAT.get(), 0.35F, 600)
                .unlockedBy("has_cat", has(POItems.CAT.get())).save(pFinishedRecipeConsumer, new ResourceLocation("dragnpets", "cooked_cat_campfire_cooking"));
    }
}