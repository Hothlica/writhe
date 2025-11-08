package net.hothlica.writhe.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hothlica.writhe.Writhe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;


public class ModBlocks {

    //Register blocks here
    public static Block PUTRESCENT_NETHERRACK = register("putrescent_netherrack", new Block(FabricBlockSettings.copyOf(Blocks.WARPED_NYLIUM)));

    //Initialize and register methods
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
