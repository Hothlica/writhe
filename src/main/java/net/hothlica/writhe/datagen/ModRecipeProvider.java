package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hothlica.writhe.registry.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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

    }
}
