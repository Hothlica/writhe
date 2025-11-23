package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hothlica.writhe.registry.*;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter consumer) {

        // Stepping Stone
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEPPING_STONE, 1)
                .input(ModItems.BERRYSHARD)
                .input(ItemTags.STONE_CRAFTING_MATERIALS)
                .criterion("has_berryshard", conditionsFromItem(ModItems.BERRYSHARD))
                .offerTo(consumer);

        // Goldshroom
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GOLD_NUGGET, 1).input(ModBlocks.GOLDSHROOM).criterion("has_goldshroom", conditionsFromItem(ModBlocks.GOLDSHROOM)).offerTo(consumer);

        // Soulstone
        offerSmelting(consumer, List.of(ModBlocks.SOULSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOULSTONE, 0.1f, 200, "minecraft:smelting");
        offerPolishedStoneRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE, ModBlocks.SOULSTONE);
        offerMosaicRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_PILLAR, ModBlocks.SOULSTONE_SLAB);

        createStairsRecipe(ModBlocks.SOULSTONE_STAIRS, Ingredient.ofItems(ModBlocks.SOULSTONE, ModBlocks.SOULSTONE_PILLAR))
                .criterion("has_soulstone", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_SLAB, Ingredient.ofItems(ModBlocks.SOULSTONE, ModBlocks.SOULSTONE_PILLAR))
                .criterion("has_soulstone", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);
        getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_WALL, Ingredient.ofItems(ModBlocks.SOULSTONE, ModBlocks.SOULSTONE_PILLAR))
                .criterion("has_soulstone", conditionsFromItem(ModBlocks.SOULSTONE))
                .offerTo(consumer);

        // Polished Soulstone
        offerChiseledBlockRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOULSTONE, ModBlocks.POLISHED_SOULSTONE);
        offerMosaicRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_PILLAR, ModBlocks.POLISHED_SOULSTONE_SLAB);

        createStairsRecipe(ModBlocks.POLISHED_SOULSTONE_STAIRS, Ingredient.ofItems(ModBlocks.POLISHED_SOULSTONE))
                .criterion("has_polished_soulstone", conditionsFromItem(ModBlocks.POLISHED_SOULSTONE))
                .offerTo(consumer);
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_SLAB, Ingredient.ofItems(ModBlocks.POLISHED_SOULSTONE))
                .criterion("has_polished_soulstone", conditionsFromItem(ModBlocks.POLISHED_SOULSTONE))
                .offerTo(consumer);
        getWallRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_WALL, Ingredient.ofItems(ModBlocks.POLISHED_SOULSTONE))
                .criterion("has_polished_soulstone", conditionsFromItem(ModBlocks.POLISHED_SOULSTONE))
                .offerTo(consumer);

        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE, ModBlocks.SOULSTONE);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_PILLAR, ModBlocks.SOULSTONE);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_STAIRS, ModBlocks.SOULSTONE);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_SLAB, ModBlocks.SOULSTONE, 2);
        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOULSTONE_WALL, ModBlocks.SOULSTONE);

        offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_SOULSTONE_SLAB, ModBlocks.SMOOTH_SOULSTONE, 2);

        Block[] soulstoneBlocks = {ModBlocks.SOULSTONE, ModBlocks.POLISHED_SOULSTONE};
        for (Block block : soulstoneBlocks) {
            offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_PILLAR, block);
            offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHISELED_SOULSTONE, block);
            offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_STAIRS, block);
            offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_SLAB, block, 2);
            offerStonecuttingRecipe(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_SOULSTONE_WALL, block);
        }
    }


}
