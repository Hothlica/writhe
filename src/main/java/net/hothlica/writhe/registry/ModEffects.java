package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.effect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> ROT = register("rot", new RotEffect(StatusEffectCategory.HARMFUL, 800080));

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Writhe.id(id), statusEffect);
    }

    public static void init() {}
}
