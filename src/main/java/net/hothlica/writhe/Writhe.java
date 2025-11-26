package net.hothlica.writhe;

import net.fabricmc.api.ModInitializer;

import net.hothlica.writhe.registry.*;
import net.hothlica.writhe.world.gen.*;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Writhe implements ModInitializer {
	public static final String MOD_ID = "writhe";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModSounds.init();
		ModEffects.init();
		ModDamageTypes.init();
		ModTags.init();
		ModAttachmentTypes.init();
		ModWorldGeneration.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}