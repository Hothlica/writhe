package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.registry.ModBlocks;
import net.hothlica.writhe.registry.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Write here to generate all-same-sided blocks and other things

        //block item models
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.PUTRESCENT_NETHERRACK, Writhe.id("block/putrescent_netherrack"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //items
        itemModelGenerator.register(ModItems.BERRYSHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPING_STONE, Models.GENERATED);
    }
}
