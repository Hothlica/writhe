package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;

import net.hothlica.writhe.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class ModBlocks {
    //Transparent block list
    public static List<Block> BLOCK_CUTOUT = new ArrayList<>();
    // REGISTER BLOCKS HERE

    // Soulstone
    public static Block SOULSTONE = register("soulstone", new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_STAIRS = register("soulstone_stairs", new StairsBlock(ModBlocks.SOULSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_SLAB = register("soulstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_WALL = register("soulstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SOULSTONE_PILLAR = register("soulstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

    // Polished Soulstone
    public static Block POLISHED_SOULSTONE = register("polished_soulstone", new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_STAIRS = register("polished_soulstone_stairs", new StairsBlock(ModBlocks.POLISHED_SOULSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_SLAB = register("polished_soulstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_WALL = register("polished_soulstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block POLISHED_SOULSTONE_PILLAR = register("polished_soulstone_pillar", new OrientablePillarBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

    // Other soulstone variants
    public static Block CHISELED_SOULSTONE = register("chiseled_soulstone", new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SMOOTH_SOULSTONE = register("smooth_soulstone", new Block(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
    public static Block SMOOTH_SOULSTONE_SLAB = register("smooth_soulstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

    // Other natural blocks
    public static Block PUTRESCENT_NETHERRACK = register("putrescent_netherrack", new ModNyliumBlock(AbstractBlock.Settings.copy(Blocks.WARPED_NYLIUM).sounds(new BlockSoundGroup(1.0f, 1.0f, SoundEvents.BLOCK_NYLIUM_BREAK, SoundEvents.BLOCK_HONEY_BLOCK_STEP, SoundEvents.BLOCK_NYLIUM_PLACE, SoundEvents.BLOCK_NYLIUM_HIT, SoundEvents.BLOCK_NYLIUM_FALL))));
    public static Block GOLDSHROOM_BLOCK = register("goldshroom_block", new PillarBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).luminance(state -> 8)));

    // Common plants
    public static Block WREATHEN_VINES = cutout(registerWithoutItem("wreathen_vines", new WreathenVinesHeadBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES))));
    public static Block WREATHEN_VINES_PLANT = cutout(registerWithoutItem("wreathen_vines_plant", new WreathenVinesBodyBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES_PLANT))));
    public static Block GOLDSHROOM = cutout(register("goldshroom", new FungusBlock(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("huge_red_mushroom")), PUTRESCENT_NETHERRACK, AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM))));
    public static Block PUTRESCENT_GRASS = cutout(register("putrescent_grass", new ModShortPlantBlock(AbstractBlock.Settings.copy(Blocks.FERN))));
    public static Block TALL_PUTRESCENT_GRASS = cutout(register("tall_putrescent_grass", new ModTallPlantBlock(AbstractBlock.Settings.copy(Blocks.LARGE_FERN).luminance(state -> 5))));

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

    private static Block cutout(Block block) {
        BLOCK_CUTOUT.add(block);
        return block;
    }
}
