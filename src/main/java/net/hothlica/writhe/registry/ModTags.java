package net.hothlica.writhe.registry;

import net.hothlica.writhe.Writhe;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {

    public static class DamageTypes {
        public static final TagKey<DamageType> HALF_BYPASSES_ENCHANTMENTS = create("half_bypasses_enchantments");

        private static TagKey<DamageType> create(String id) {
            return TagKey.of(RegistryKeys.DAMAGE_TYPE, Writhe.id(id));
        }
    }

    public static void init(){}
}
