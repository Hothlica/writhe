package net.hothlica.writhe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.hothlica.writhe.registry.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class WritheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WREATHEN_VINES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WREATHEN_VINES_PLANT, RenderLayer.getCutout());
    }
}
