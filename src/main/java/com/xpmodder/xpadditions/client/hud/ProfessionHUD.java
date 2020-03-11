package com.xpmodder.xpadditions.client.hud;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.xpmodder.xpadditions.handler.GeneralEventHandler.professionsSystem;

public class ProfessionHUD extends Gui {

    private static ResourceLocation parts = new ResourceLocation(Reference.MOD_ID, "textures/gui/hud_parts.png");
    private int width;
    private int height;
    private int xSize = 182;
    private int ySize = 5;
    private final int White = 16777215;     //255,255,255

    public ProfessionHUD() {}

    public void Render(RenderGameOverlayEvent.Pre event){

        GlStateManager.pushMatrix();
        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        profiler.startSection("xpadditions-hud");
        ScaledResolution scaledRes = new ScaledResolution(mc);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableAlpha();
        mc.getTextureManager().bindTexture(parts);
        this.width = mc.displayWidth;
        this.height = mc.displayHeight;
        int x = (scaledRes.getScaledWidth() / 2) - (xSize / 2);
        int y = (scaledRes.getScaledHeight() - 55);
        String Text = professionsSystem.getPlayerProfessionName(mc.player);
        String Text2 = String.valueOf(professionsSystem.getPlayerProfessionXP(mc.player));
        String Text3 = professionsSystem.getPlayerProfessionLevel(mc.player);
        int Text3Width = mc.fontRenderer.getStringWidth(Text3);
        int Text2Width = mc.fontRenderer.getStringWidth(Text2);
        int TextX1 = (scaledRes.getScaledWidth() - this.xSize);
        int TextX2 = (scaledRes.getScaledWidth() - Text2Width);
        int TextX3 = (scaledRes.getScaledWidth() + xSize - Text3Width);
        int TextY = (scaledRes.getScaledHeight() * 2) - 120;
        int amountFilled = (professionsSystem.getPlayerProfessionXP(mc.player) / 100) * this.xSize;

        if(professionsSystem != null) {

            this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
            this.drawTexturedModalRect(x, y, 0, 5, amountFilled, this.ySize);

            if(!professionsSystem.getPlayerProfessionName(mc.player).equals("None")) {

                GlStateManager.scale(0.5, 0.5, 0.5);
                this.drawString(mc.fontRenderer, Text, TextX1, TextY, this.White);
                this.drawString(mc.fontRenderer, Text2, TextX2, TextY, this.White);
                this.drawString(mc.fontRenderer, Text3, TextX3, TextY, this.White);

            }
        }
        mc.getTextureManager().deleteTexture(parts);
        mc.getTextureManager().bindTexture(ICONS);
        GlStateManager.popMatrix();
        profiler.endSection();

    }


}
