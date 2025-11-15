package net.hothlica.writhe.effect;

import net.hothlica.writhe.entity.access.Rot;
import net.hothlica.writhe.registry.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class RotEffect extends StatusEffect {

    public RotEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    //apply faster over time
    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof Rot rot) {
            int rotTicks = rot.getRotTicks();
            //Original equation: sin(x^2 / 4000)
            double prevDerivative = ((rotTicks - 1) / 2000.0) * Math.cos(((rotTicks - 1) * (rotTicks - 1)) / 4000.0);
            double currDerivative = (rotTicks / 2000.0) * Math.cos((rotTicks * rotTicks) / 4000.0);
            if (prevDerivative > 0 && currDerivative <= 0) {
                entity.damage(ModDamageTypes.create(entity, ModDamageTypes.ROT), 1.0f);
            }
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
