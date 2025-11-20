package net.hothlica.writhe.effect;

import net.hothlica.writhe.registry.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;

public class AtaraxiaEffect extends StatusEffect {

    public AtaraxiaEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(entity.defaultMaxHealth);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration == 1;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        ArrayList<StatusEffectInstance> initialEffects = new ArrayList<StatusEffectInstance>(entity.getStatusEffects());
        entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(entity.getMaxHealth() + (initialEffects.size() - 1) * 2);
        for (StatusEffectInstance instance : initialEffects) {
            if (instance.getEffectType() != ModEffects.ATARAXIA)
                entity.removeStatusEffect(instance.getEffectType());
        }
    }
}
