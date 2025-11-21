package net.hothlica.writhe.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.hothlica.writhe.entity.access.Rot;
import net.hothlica.writhe.registry.ModAttachmentTypes;
import net.hothlica.writhe.registry.ModEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class RotOverlay implements LayeredDrawer.Layer {

    private static final Identifier VIGNETTE_TEXTURE = Identifier.ofVanilla("textures/misc/vignette.png");
    private int prevDuration = 0;

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player.hasStatusEffect(ModEffects.ROT)) {
            int currDuration = client.player.getStatusEffect(ModEffects.ROT).getDuration();
            int rotTicks = client.player.getAttachedOrCreate(ModAttachmentTypes.ROT_TICKS);
            float opacity = getOpacity(currDuration, rotTicks, 200, 240);
            renderOverlay(context, opacity);
            //client.player.sendMessage(Text.literal(String.valueOf(opacity)));
        }
    }

    private void renderOverlay(DrawContext context, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        if (opacity > 0.0f) {
            opacity = MathHelper.clamp(opacity, 0.0f, 1.0f);
            context.setShaderColor(0.0f, opacity, 0.0f, 1.0f);
        }
        else context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        context.drawTexture(
            VIGNETTE_TEXTURE,
            0, 0, -90, 0.0f, 0.0f,
            context.getScaledWindowWidth(),
            context.getScaledWindowHeight(),
            context.getScaledWindowWidth(),
            context.getScaledWindowHeight()
        );
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    private float getOpacity(int currDuration, int rotTicks, int fadeInTicks, int fadeOut) {
        if (currDuration == -1) currDuration = fadeOut;
        if (rotTicks > 0 && currDuration > prevDuration) currDuration = prevDuration + 1;
        //Results in 0f-1f
        float fadeOutMultiplier = Math.min((float) currDuration / fadeOut, 1f);
        prevDuration = currDuration;
        return Math.min(rotTicks * fadeOutMultiplier / fadeInTicks, 1f);
    }
}
