package net.hothlica.writhe.mixin;

import net.hothlica.writhe.registry.ModEffects;
import net.hothlica.writhe.effect.RotEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoneyBottleItem.class)
public abstract class HoneyBottleItemMixin {
    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void lessenRotTime(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient() && user.hasStatusEffect(ModEffects.ROT)) {
            RotEffect.reduceRotTIme(user, 300);
        }
    }
}
