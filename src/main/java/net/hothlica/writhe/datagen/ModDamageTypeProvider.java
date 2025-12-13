package net.hothlica.writhe.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hothlica.writhe.registry.ModDamageTypes;
import net.hothlica.writhe.registry.ModTags;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeProvider extends FabricTagProvider<DamageType> {
    public ModDamageTypeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .addOptional(ModDamageTypes.WRITHE);
        getOrCreateTagBuilder(DamageTypeTags.NO_KNOCKBACK)
                .addOptional(ModDamageTypes.WRITHE);
        getOrCreateTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN)
                .addOptional(ModDamageTypes.WRITHE);
        getOrCreateTagBuilder(ModTags.DamageTypes.HALF_BYPASSES_ENCHANTMENTS)
                .addOptional(ModDamageTypes.WRITHE);
    }
}
