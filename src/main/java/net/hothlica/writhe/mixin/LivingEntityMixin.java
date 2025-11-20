package net.hothlica.writhe.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.entity.access.Rot;
import net.hothlica.writhe.registry.ModEffects;
import net.hothlica.writhe.registry.ModTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements Rot {

    //Shadow
    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    //Entity variables/attributes
    @Unique private int rotTicks;
    @Unique private DamageSource damageSourceLook;

    //Rot
    @Override public int writhe$getRotTicks(){return rotTicks;}

    //Mixins
    @Inject(method = "tickStatusEffects", at = @At("TAIL"))
    private void tickWritheEffects(CallbackInfo ci) {
        if (this.hasStatusEffect(ModEffects.ROT)) this.rotTicks++;
        else this.rotTicks = 0;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeRot(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt(Writhe.MOD_ID + "_RotTicks", this.rotTicks);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readRot(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(Writhe.MOD_ID + "_RotTicks"))
            this.rotTicks = nbt.getInt(Writhe.MOD_ID + "_RotTicks");
    }

    @Inject(method = "canHaveStatusEffect", at = @At("HEAD"), cancellable = true)
    private void ataraxiaImmuneAllEffects(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (this.hasStatusEffect(ModEffects.ATARAXIA)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "modifyAppliedDamage", at = @At("HEAD"))
    private void damageSourceGet(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        this.damageSourceLook = source;
    }

    @WrapOperation(method = "modifyAppliedDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getInflictedDamage(FF)F"))
    private float halfProtEffectiveness(float amount, float k, Operation<Float> original) {
        if (this.damageSourceLook.isIn(ModTags.DamageTypes.HALF_BYPASSES_ENCHANTMENTS)) {
            float withProt = original.call(amount, k);
            return amount - ((amount - withProt) / 2.0f);
        }
        return original.call(amount, k);
    }
}
