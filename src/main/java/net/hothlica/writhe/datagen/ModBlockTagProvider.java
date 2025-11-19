package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hothlica.writhe.registry.ModBlocks;
import net.hothlica.writhe.registry.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.SOULSTONE_BLOCKS)
            .add(ModBlocks.SOULSTONE)
            .add(ModBlocks.SOULSTONE_STAIRS)
            .add(ModBlocks.SOULSTONE_SLAB)
            .add(ModBlocks.SOULSTONE_WALL)
            .add(ModBlocks.SOULSTONE_PILLAR)
            .add(ModBlocks.POLISHED_SOULSTONE)
            .add(ModBlocks.POLISHED_SOULSTONE_STAIRS)
            .add(ModBlocks.POLISHED_SOULSTONE_SLAB)
            .add(ModBlocks.POLISHED_SOULSTONE_WALL)
            .add(ModBlocks.POLISHED_SOULSTONE_PILLAR)
            .add(ModBlocks.CHISELED_SOULSTONE)
            .add(ModBlocks.SMOOTH_SOULSTONE)
            .add(ModBlocks.SMOOTH_SOULSTONE_SLAB);

        //Mineable via tool
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .addTag(ModTags.Blocks.SOULSTONE_BLOCKS)
            .add(ModBlocks.PUTRESCENT_NETHERRACK);

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add(ModBlocks.SOULSTONE_STAIRS)
            .add(ModBlocks.POLISHED_SOULSTONE_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
            .add(ModBlocks.SOULSTONE_SLAB)
            .add(ModBlocks.POLISHED_SOULSTONE_SLAB)
            .add(ModBlocks.SMOOTH_SOULSTONE_SLAB);

        getOrCreateTagBuilder(BlockTags.WALLS)
            .add(ModBlocks.SOULSTONE_WALL)
            .add(ModBlocks.POLISHED_SOULSTONE_WALL);
    }
}
