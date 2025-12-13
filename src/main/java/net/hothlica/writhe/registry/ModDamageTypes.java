package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageTypes {

    public static void init(){}

    public static final RegistryKey<DamageType> WRITHE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Writhe.id("writhe"));

    public static DamageSource create(LivingEntity entity, RegistryKey<DamageType> key) {
        return new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
