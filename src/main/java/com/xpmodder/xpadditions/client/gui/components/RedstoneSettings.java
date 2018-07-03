package com.xpmodder.xpadditions.client.gui.components;

import com.xpmodder.xpadditions.client.gui.XPInterfaceGui;
import com.xpmodder.xpadditions.client.gui.parts.ModGuiIconButton;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.EnumRSMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class RedstoneSettings {

    EnumRSMode RS;
    int sx;
    int sy;
    int xSize;
    int ySize;
    GuiContainer gui;
    ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png");

    public RedstoneSettings(int sx, int sy, int xSize, int ySize, EnumRSMode RS, GuiContainer gui) {

        this.sx = sx;
        this.sy = sy;
        this.xSize = xSize;
        this.ySize = ySize;
        this.RS = RS;
        this.gui = gui;

    }

    public void drawButtons(Minecraft mc, int mouseX, int mouseY, float partialTicks) {

        //gui.addButton(new ModGuiIconButton(0, sx + xSize + 5, sy + 5, 21, 20, false, texture, 200, 46));
        //gui.addButton(new ModGuiIconButton(1, sx + xSize + 5, sy + 30, 21, 20, false, texture, 200, 66));
        //gui.addButton(new ModGuiIconButton(2, sx + xSize + 5, sy + 54, 21, 20, false, texture, 200, 86));

    }

}
