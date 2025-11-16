package net.hothlica.writhe.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.hothlica.writhe.Writhe;
import net.hothlica.writhe.entity.access.Rot;
import net.hothlica.writhe.registry.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class RotOverlay implements LayeredDrawer.Layer {

    private static final Identifier VIGNETTE_TEXTURE = Identifier.ofVanilla("textures/misc/vignette.png");

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        if (client.player.hasStatusEffect(ModEffects.ROT) && client.player instanceof Rot rot) {
            int currDuration = client.player.getStatusEffect(ModEffects.ROT).getDuration();
            int rotTicks = rot.getRotTicks();
            float fadeOut = Math.min(currDuration / 100f, 1f); // Will be 1.0f until last 100 ticks
            float opacity = Math.min(rotTicks * fadeOut / 200f, 1f); // Will fade in for 200 ticks
            renderOverlay(context, opacity);
        }
    }

    private void renderOverlay(DrawContext context, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        if (opacity > 0.0F) {
            opacity = MathHelper.clamp(opacity, 0.0F, 1.0F);
            context.setShaderColor(0.0F, opacity, 0F, 1.0F);
        } else {
            context.setShaderColor(0, 0, 0, 1.0F);
        }
        context.drawTexture(VIGNETTE_TEXTURE, 0, 0, -90, 0.0F, 0.0F, context.getScaledWindowWidth(), context.getScaledWindowHeight(), context.getScaledWindowWidth(), context.getScaledWindowHeight());
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }
}
