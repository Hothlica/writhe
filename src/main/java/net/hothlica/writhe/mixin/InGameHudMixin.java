package net.hothlica.writhe.mixin;

import net.hothlica.writhe.client.gui.RotOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Unique private final RotOverlay rotOverlay = new RotOverlay();

    @Inject(method = "render", at = @At("HEAD"))
    private void renderBehindHud(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        rotOverlay.render(context, tickCounter);
    }
}
