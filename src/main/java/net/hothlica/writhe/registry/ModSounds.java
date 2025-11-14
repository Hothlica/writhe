package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent STEPPING_STONE_USE = register("stepping_stone_use");

    public static void init() {}

    public static SoundEvent register(String path) {
        Identifier id = Writhe.id(path);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
