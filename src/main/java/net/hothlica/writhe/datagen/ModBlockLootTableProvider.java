package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.hothlica.writhe.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        //will drop itself
        //addDrop(ModBlocks.);

        //With silk touch vs without silktouch & NOT an ore
        addDrop(ModBlocks.PUTRESCENT_NETHERRACK, drops(ModBlocks.PUTRESCENT_NETHERRACK, Blocks.NETHERRACK));
    }
}
