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
import net.minecraft.util.math.Direction;

import static net.minecraft.data.client.BlockStateModelGenerator.createAxisRotatedBlockState;
import static net.minecraft.data.client.BlockStateModelGenerator.createBooleanModelMap;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //Write here to generate one-textured blocks and other things
        registerOrientablePillar(blockStateModelGenerator, ModBlocks.POLISHED_SOULSTONE_PILLAR);
        registerMirrorAxisRotated(blockStateModelGenerator, ModBlocks.SOULSTONE_PILLAR, TexturedModel.END_FOR_TOP_CUBE_COLUMN);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_SOULSTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_SOULSTONE);
        registerUniqueSideSlab(blockStateModelGenerator, ModBlocks.SMOOTH_SOULSTONE_SLAB, ModBlocks.SMOOTH_SOULSTONE, ModBlocks.SMOOTH_SOULSTONE_SLAB);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SOULSTONE)
            .stairs(ModBlocks.SOULSTONE_STAIRS)
            .slab(ModBlocks.SOULSTONE_SLAB)
            .wall(ModBlocks.SOULSTONE_WALL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.POLISHED_SOULSTONE)
            .stairs(ModBlocks.POLISHED_SOULSTONE_STAIRS)
            .slab(ModBlocks.POLISHED_SOULSTONE_SLAB)
            .wall(ModBlocks.POLISHED_SOULSTONE_WALL);

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

    private void registerOrientablePillar(BlockStateModelGenerator g, Block block) {
        TextureMap mirrorTextureMap = new TextureMap()
            .put(TextureKey.END, TextureMap.getSubId(block, "_top"))
            .put(TextureKey.SIDE, TextureMap.getSubId(block, ""));
        Identifier modelId = TexturedModel.END_FOR_TOP_CUBE_COLUMN.upload(block, g.modelCollector);
        Identifier mirrorModelId = Models.CUBE_COLUMN_MIRRORED.upload(block, mirrorTextureMap, g.modelCollector);
        g.blockStateCollector.accept( VariantsBlockStateSupplier.create(block).coordinate(
            BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, mirrorModelId).put(VariantSettings.X, VariantSettings.Rotation.R180))
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                .register(Direction.NORTH, BlockStateVariant.create()
                    .put(VariantSettings.MODEL, mirrorModelId)
                    .put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.EAST, BlockStateVariant.create()
                    .put(VariantSettings.MODEL, mirrorModelId)
                    .put(VariantSettings.X, VariantSettings.Rotation.R90)
                    .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.SOUTH, BlockStateVariant.create()
                    .put(VariantSettings.MODEL, modelId)
                    .put(VariantSettings.X, VariantSettings.Rotation.R90)
                    .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.WEST, BlockStateVariant.create()
                    .put(VariantSettings.MODEL, modelId)
                    .put(VariantSettings.X, VariantSettings.Rotation.R90)
                    .put(VariantSettings.Y, VariantSettings.Rotation.R270))
            )
        );
    }

    private void registerMirrorAxisRotated(BlockStateModelGenerator g, Block block, TexturedModel.Factory verticalModelFactory) {
        TextureMap mirrorTextureMap = new TextureMap()
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(block, ""));
        Identifier identifier = verticalModelFactory.upload(block, g.modelCollector);
        Identifier identifier2 = Models.CUBE_COLUMN_MIRRORED.upload(block, mirrorTextureMap, g.modelCollector);
        g.blockStateCollector.accept(createAxisRotatedBlockState(block, identifier, identifier2));
    }

    private void registerUniqueSideSlab(BlockStateModelGenerator g, Block upload, Block block, Block sideBlock){
        //Needs side texture
        TextureMap textureMap = new TextureMap()
                .put(TextureKey.BOTTOM, TextureMap.getSubId(block, ""))
                .put(TextureKey.TOP, TextureMap.getSubId(block, ""))
                .put(TextureKey.SIDE, TextureMap.getSubId(sideBlock, ""));
        Identifier slab = Models.SLAB.upload(upload, textureMap, g.modelCollector);
        Identifier slabTop = Models.SLAB_TOP.upload(upload, textureMap, g.modelCollector);
        g.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(upload, slab, slabTop, TextureMap.getSubId(block, "")));
        g.registerParentedItemModel(upload, slab);
    }
}
