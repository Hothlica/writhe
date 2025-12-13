package net.hothlica.writhe.effect;

import net.hothlica.writhe.registry.ModDamageTypes;
import net.hothlica.writhe.registry.ModAttachmentTypes;
import net.hothlica.writhe.registry.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class WritheEffect extends StatusEffect {

    public WritheEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //apply faster over time
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isOnFire()) {
            reduceWritheTime(entity, 3);
        }
        int writheTicks = entity.getAttachedOrCreate(ModAttachmentTypes.WRITHE_TICKS);
        //Original equation: sin(x^2 / 4000) NOTE: equation gets too fast later on, but that's not a problem yet
        float timer = 4000.0f - (amplifier * 15);
        float halfTimer = timer / 2;
        float prevDerivative = (float) (((writheTicks - 1) / halfTimer) * Math.cos(((writheTicks - 1) * (writheTicks - 1)) / timer));
        float currDerivative = (float) ((writheTicks / halfTimer) * Math.cos((writheTicks * writheTicks) / timer));
        if (prevDerivative > 0 && currDerivative <= 0) {
            entity.damage(ModDamageTypes.create(entity, ModDamageTypes.WRITHE), 1.0f);
        }
        entity.setAttached(ModAttachmentTypes.WRITHE_TICKS, writheTicks + 1);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    //Helper method
    public static void reduceWritheTime(LivingEntity user, int reductedTime) {
        StatusEffectInstance instance = user.getStatusEffect(ModEffects.WRITHE);
        if (instance != null) {
            int newDuration;
            if (!instance.isInfinite()) newDuration = Math.max(0, instance.getDuration() - reductedTime);
            else newDuration = instance.getDuration();
            user.setStatusEffect(new StatusEffectInstance(ModEffects.WRITHE, newDuration, instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles(), instance.shouldShowIcon()), user.getAttacker());
        }
    }

    public static void onRemoveWrithe(LivingEntity entity) {
        entity.setAttached(ModAttachmentTypes.WRITHE_TICKS, 0);
    }
}
