package com.xpmodder.xpadditions.client.hud;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

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
        mc.getTextureManager().bindTexture(parts);          //Bind the texture for our hud elemets
        this.width = mc.displayWidth;
        this.height = mc.displayHeight;
        int x = (scaledRes.getScaledWidth() / 2) - (xSize / 2);     //Calculate the x and y coordinates for the profession bar
        int y = (scaledRes.getScaledHeight() - 55);
        double scale = 0.5;                                         //Scale for the GlStateManager
        String Text = professionsSystem.getPlayerProfessionName(mc.player);
        String Text2 = String.valueOf(professionsSystem.getPlayerProfessionXP(mc.player));
        String Text3 = professionsSystem.getPlayerProfessionLevel(mc.player);
        int Text3Width = mc.fontRenderer.getStringWidth(Text3);
        int Text2Width = mc.fontRenderer.getStringWidth(Text2);
        int TextX1 = (int)Math.round(x / scale);
        int TextX2 = (int)Math.round((x + (this.xSize / 2.0)) / scale - (Text2Width / 2.0));
        int TextX3 = (int)Math.round((x + this.xSize) / scale - Text3Width);

        if(mc.player.getAbsorptionAmount() > 0){            //The player has absorption, so we want to shift the bar and text up
            y -= 10;
        }

        if(mc.player.isCreative()){                         //Move the hud to directly over the hotbar as we dont need to leave space for xp, health and armor
            y += 25;
        }

        int TextY = (int)Math.round((y / scale) - 10);
        int amountFilled = (professionsSystem.getPlayerProfessionXP(mc.player) / 100) * this.xSize;


        if(!mc.player.isSpectator()) {                      //Only Render Hud when not in spectator mode

            if (professionsSystem != null) {

                this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
                this.drawTexturedModalRect(x, y, 0, 5, amountFilled, this.ySize);

                if (!professionsSystem.getPlayerProfessionName(mc.player).equals("None")) {

                    GlStateManager.scale(scale, scale, scale);
                    this.drawString(mc.fontRenderer, Text, TextX1, TextY, this.White);
                    this.drawString(mc.fontRenderer, Text2, TextX2, TextY, this.White);
                    this.drawString(mc.fontRenderer, Text3, TextX3, TextY, this.White);

                }
            }
        }
        mc.getTextureManager().deleteTexture(parts);
        mc.getTextureManager().bindTexture(ICONS);
        GlStateManager.popMatrix();
        profiler.endSection();


    }


}
