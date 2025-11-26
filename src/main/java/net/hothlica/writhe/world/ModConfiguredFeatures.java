package net.hothlica.writhe.world;

import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.registry.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GOLDSHROOM_KEY = registerKey("huge_goldshroom");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        register(context, HUGE_GOLDSHROOM_KEY, Feature.HUGE_BROWN_MUSHROOM, new HugeMushroomFeatureConfig(
            BlockStateProvider.of(ModBlocks.GOLDSHROOM_BLOCK.getDefaultState()),
            BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)),
            3
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String id){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Writhe.id(id));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
