package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPInterfaceContainer;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class XPInterfaceGui extends ModBaseGui {

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
    protected void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableLighting();

        this.itemRender.renderItemAndEffectIntoGUI(new ItemStack(Item.getItemFromBlock(ModBlocks.xpBlock)), sx + 49, sy + 8);

        GlStateManager.disableLighting();

        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_interface_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if (te.isConnected()){

            int high = getHeightScaled(58);
            this.drawTexturedModalRect(sx + 83, sy + 66 - high, 176, 0, 9, high);
            int highF = getHeightScaledFluid(58);
            this.drawTexturedModalRect(sx + 110, sy + 66 - highF, 186, 0, 16, highF);

        }

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Items";
        String s2 = "Fluids";
        String s3 = String.valueOf(te.xp / XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)));
        this.fontRenderer.drawString(s, 8, 40, 4210752);            //#404040
        this.fontRenderer.drawString(s2, 135, 40, 4210752);
        this.fontRenderer.drawString(s3, 50, 30, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);      //#404040

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
