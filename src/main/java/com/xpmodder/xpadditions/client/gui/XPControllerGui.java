package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerContainer;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class XPControllerGui extends GuiContainer {

    private IInventory playerInv;
    private XPControllerTileEntity te;

    public XPControllerGui(IInventory playerInv, XPControllerTileEntity te){

        super(new XPControllerContainer(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 176;
        this.ySize = 166;

    }

    @Override
    public void initGui(){

        super.initGui();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_controller_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Total XP:";
        String s2 = String.valueOf(this.te.getTotalXP(this.te.getID()));
        this.fontRenderer.drawString(s, 8, 40, 4210752);            //#404040
        this.fontRenderer.drawString(s2, 8, 50, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040

    }

}
