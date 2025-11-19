package net.hothlica.writhe.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hothlica.writhe.Writhe;

import net.hothlica.writhe.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;


public class ModBlocks {

    // REGISTER BLOCKS HERE

    // Soulstone
    public static Block SOULSTONE = register("soulstone", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_STAIRS = register("soulstone_stairs", new StairsBlock(ModBlocks.SOULSTONE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_SLAB = register("soulstone_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_WALL = register("soulstone_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_PILLAR = register("soulstone_pillar", new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));

    // Polished Soulstone
    public static Block POLISHED_SOULSTONE = register("polished_soulstone", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_STAIRS = register("polished_soulstone_stairs", new StairsBlock(ModBlocks.POLISHED_SOULSTONE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_SLAB = register("polished_soulstone_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_WALL = register("polished_soulstone_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_PILLAR = register("polished_soulstone_pillar", new OrientablePillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));

    // Other soulstone variants
    public static Block CHISELED_SOULSTONE = register("chiseled_soulstone", new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_BASALT)));

    // Other natural blocks
    public static Block PUTRESCENT_NETHERRACK = register("putrescent_netherrack", new Block(FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM)
            .sounds(new BlockSoundGroup(1.0f, 1.0f, SoundEvents.BLOCK_NYLIUM_BREAK, SoundEvents.BLOCK_HONEY_BLOCK_STEP, SoundEvents.BLOCK_NYLIUM_PLACE, SoundEvents.BLOCK_NYLIUM_HIT, SoundEvents.BLOCK_NYLIUM_FALL))));

    // Common plants
    public static Block WREATHEN_VINES = registerWithoutItem("wreathen_vines", new WreathenVinesHeadBlock(FabricBlockSettings.copyOf(Blocks.CAVE_VINES)));
    public static Block WREATHEN_VINES_PLANT = registerWithoutItem("wreathen_vines_plant", new WreathenVinesBodyBlock(FabricBlockSettings.copyOf(Blocks.CAVE_VINES_PLANT)));

    // Initialize and register methods
    public static void init(){}

    public static Block register(String id, Block block) {
        block = registerWithoutItem(id, block);
        ModItems.registerBlockItem(block);
        return block;
    }

    public static Block registerWithoutItem(String id, Block block) {
        return Registry.register(Registries.BLOCK, Writhe.id(id), block);
    }

}
