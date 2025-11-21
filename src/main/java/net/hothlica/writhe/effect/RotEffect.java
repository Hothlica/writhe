package net.hothlica.writhe.effect;

import net.hothlica.writhe.registry.ModDamageTypes;
import net.hothlica.writhe.registry.ModAttachmentTypes;
import net.hothlica.writhe.registry.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class RotEffect extends StatusEffect {

    public RotEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //apply faster over time
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isOnFire()) {
            reduceRotTIme(entity, 3);
        }
        int rotTicks = entity.getAttachedOrCreate(ModAttachmentTypes.ROT_TICKS);
        //Original equation: sin(x^2 / 4000) NOTE: equation gets too fast later on, but that's not a problem yet
        float timer = 4000.0f - (amplifier * 15);
        float halfTimer = timer / 2;
        float prevDerivative = (float) (((rotTicks - 1) / halfTimer) * Math.cos(((rotTicks - 1) * (rotTicks - 1)) / timer));
        float currDerivative = (float) ((rotTicks / halfTimer) * Math.cos((rotTicks * rotTicks) / timer));
        if (prevDerivative > 0 && currDerivative <= 0) {
            entity.damage(ModDamageTypes.create(entity, ModDamageTypes.ROT), 1.0f);
        }
        entity.setAttached(ModAttachmentTypes.ROT_TICKS, rotTicks + 1);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


    //Helper method
    public static void reduceRotTIme(LivingEntity user, int reductedTime) {
        StatusEffectInstance instance = user.getStatusEffect(ModEffects.ROT);
        int newDuration = 0;
        if (!instance.isInfinite()) newDuration = Math.max(0, instance.getDuration() - reductedTime);
        else newDuration = instance.getDuration();
        user.setStatusEffect(new StatusEffectInstance(ModEffects.ROT, newDuration, instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles(), instance.shouldShowIcon()), user.getAttacker());
    }

    public static void onRemoveRot(LivingEntity entity) {
        entity.setAttached(ModAttachmentTypes.ROT_TICKS, 0);
    }
}
