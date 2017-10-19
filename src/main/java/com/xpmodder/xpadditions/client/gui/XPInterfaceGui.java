package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.client.gui.parts.ModGuiIconButton;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPInterfaceContainer;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.io.IOException;

public class XPInterfaceGui extends GuiContainer {

    private IInventory playerInv;
    private XPInterfaceTileEntity te;
    private ResourceLocation buttonParts = new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png");

    public XPInterfaceGui(IInventory playerInv, XPInterfaceTileEntity te){

        super(new XPInterfaceContainer(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 166;

    }

    @Override
    public void initGui(){

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        super.initGui();

        this.addButton(new ModGuiIconButton(0, sx + xSize + 5, sy + 5, 21, 20, "", buttonParts, 200, 46));
        this.addButton(new ModGuiIconButton(1, sx + xSize + 5, sy + 30, 21, 20, "", buttonParts, 200, 66));
        this.addButton(new ModGuiIconButton(2, sx + xSize + 5, sy + 54, 21, 20, "", buttonParts, 200, 86));

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_interface_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(sx + xSize, sy, 203, 0, 30, 80);

        if (te.isConnected()){

            int high = getHeightScaled(58);
            this.drawTexturedModalRect(sx + 83, sy + 65 - high, 176, 0, 9, high);
            int highF = getHeightScaledFluid(58);
            this.drawTexturedModalRect(sx + 110, sy + 65 - highF, 186, 0, 16, highF);

        }

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Items";
        String s2 = "Fluids";
        String s3 = String.valueOf(te.xp / XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)));
        this.fontRendererObj.drawString(s, 8, 40, 4210752);            //#404040
        this.fontRendererObj.drawString(s2, 135, 40, 4210752);
        this.fontRendererObj.drawString(s3, 50, 30, 4210752);
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton){

        try {

            super.mouseClicked(mouseX, mouseY, mouseButton);

        }
        catch (IOException e){

            LogHelper.warn("IOException in mouseClicked() of XPInterfaceGui: " + e);

        }



    }

    private int getHeightScaled(int pixels) {

        float ratio;

        ratio = (float) te.xp / te.maxXP;

        if(ratio > 1)
            ratio = 1;

        return  Math.round((pixels * ratio));

    }

    private int getHeightScaledFluid(int pixels){

        float ratio;

        ratio = (float) te.xp / te.maxXPmB;

        if(ratio > 1)
            ratio = 1;

        return Math.round((pixels * ratio));

    }

}
