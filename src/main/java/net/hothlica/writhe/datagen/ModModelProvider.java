package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.registry.ModBlocks;
import net.hothlica.writhe.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import static net.minecraft.data.client.BlockStateModelGenerator.createBooleanModelMap;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Write here to generate one-textured blocks and other things
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SOULSTONE)
                .stairs(ModBlocks.SOULSTONE_STAIRS)
                .slab(ModBlocks.SOULSTONE_SLAB)
                .wall(ModBlocks.SOULSTONE_WALL);

        registerFruitVines(blockStateModelGenerator, ModBlocks.WREATHEN_VINES, ModBlocks.WREATHEN_VINES_PLANT);

        //block item models for blocks that are not modeled by the datagen
        blockStateModelGenerator.registerParentedItemModel(ModBlocks.PUTRESCENT_NETHERRACK, Writhe.id("block/putrescent_netherrack"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //items
        itemModelGenerator.register(ModItems.BERRYSHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEPPING_STONE, Models.GENERATED);

    }

    //Custom model implementation
    private void registerFruitVines(BlockStateModelGenerator g, Block head, Block body) {
        Identifier idDarkHead = g.createSubModel(head, "", Models.CROSS, TextureMap::cross);
        Identifier idLitHead = g.createSubModel(head, "_lit", Models.CROSS, TextureMap::cross);
        g.blockStateCollector.accept(VariantsBlockStateSupplier.create(head).coordinate(createBooleanModelMap(Properties.BERRIES, idLitHead, idDarkHead)));
        Identifier idDarkBody = g.createSubModel(body, "", Models.CROSS, TextureMap::cross);
        Identifier idLitBody = g.createSubModel(body, "_lit", Models.CROSS, TextureMap::cross);
        g.blockStateCollector.accept(VariantsBlockStateSupplier.create(body).coordinate(createBooleanModelMap(Properties.BERRIES, idLitBody, idDarkBody)));
        //stops body from being registered as an item
        g.excludeFromSimpleItemModelGeneration(body);
    }
}
