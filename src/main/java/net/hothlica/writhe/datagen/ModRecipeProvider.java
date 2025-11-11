package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hothlica.writhe.registry.*;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter consumer) {
        //Stepping Stone
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEPPING_STONE, 1)
                .input(ModItems.BERRYSHARD)
                .input(ItemTags.STONE_CRAFTING_MATERIALS)
                .criterion("has_berryshard", conditionsFromItem(ModItems.BERRYSHARD))
                .offerTo(consumer);

        //Soulstone
        createStairsRecipe(ModBlocks.SOULSTONE_STAIRS, Ingredient.ofItems(ModBlocks.SOULSTONE))
                .criterion("has_soulstone", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_SLAB, Ingredient.ofItems(ModBlocks.SOULSTONE))
                .criterion("has_packed_snow_bricks", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);
        getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_WALL, Ingredient.ofItems(ModBlocks.SOULSTONE))
                .criterion("has_polished_packed_snow", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);

        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_STAIRS, ModBlocks.SOULSTONE);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_SLAB, ModBlocks.SOULSTONE, 2);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_WALL, ModBlocks.SOULSTONE);
    }


}
