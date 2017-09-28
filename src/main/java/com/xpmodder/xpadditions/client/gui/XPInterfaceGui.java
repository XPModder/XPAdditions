package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPInterfaceContainer;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class XPInterfaceGui extends GuiContainer {

    private IInventory playerInv;
    private XPInterfaceTileEntity te;

    public XPInterfaceGui(IInventory playerInv, XPInterfaceTileEntity te){

        super(new XPInterfaceContainer(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 166;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_interface_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Items";
        String s2 = "Fluid";
        this.fontRendererObj.drawString(s, 8, 6, 4210752);            //#404040
        this.fontRendererObj.drawString(s2, 145, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040

        if (100 < mouseX & mouseX < 120){

            if (10 < mouseY & mouseY < 100){



            }

        }

    }

}
