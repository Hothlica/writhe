package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hothlica.writhe.block.*;
import net.hothlica.writhe.registry.ModBlocks;
import net.hothlica.writhe.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //will drop itself
        addDrop(ModBlocks.SOULSTONE);
        addDrop(ModBlocks.SOULSTONE_STAIRS);
        addDrop(ModBlocks.SOULSTONE_SLAB);
        addDrop(ModBlocks.SOULSTONE_WALL);

        //With silk touch vs without silktouch & NOT an ore
        addDrop(ModBlocks.PUTRESCENT_NETHERRACK, drops(ModBlocks.PUTRESCENT_NETHERRACK, Blocks.NETHERRACK));

        this.addDrop(ModBlocks.WREATHEN_VINES, block -> this.berryshardDrops(block));
        this.addDrop(ModBlocks.WREATHEN_VINES_PLANT, block -> this.berryshardDrops(block));


    }

    //Custom loot tables
    public LootTable.Builder berryshardDrops(Block drop) {
        return LootTable.builder().pool(LootPool.builder()
            .with(ItemEntry.builder(ModItems.BERRYSHARD))
            .conditionally(BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(WreathenVines.BERRIES, true)))
        );
    }
}
