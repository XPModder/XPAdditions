package com.xpmodder.xpadditions.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPInterfaceContainer;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import com.xpmodder.xpadditions.utility.EnumRSMode;
import com.xpmodder.xpadditions.utility.XPHelper;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;



public class XPInterfaceGui extends ModBaseGui {

    private IInventory playerInv;
    private XPInterfaceTileEntity te;

    public XPInterfaceGui(PlayerInventory playerInv, XPInterfaceTileEntity te){

        super(new XPInterfaceContainer(playerInv, te), playerInv, "XP Interface");

        this.playerInv = playerInv;
        this.te = te;

        if(te.getRS() == EnumRSMode.REDSTONE_IGNORED.getID()){
            this.RS[0] = true;
            this.RS[1] = this.RS[2] = false;
        }
        else if(te.getRS() == EnumRSMode.REDSTONE_OFF.getID()){
            this.RS[1] = true;
            this.RS[0] = this.RS[2] = false;
        }
        else{
            this.RS[2] = true;
            this.RS[0] = this.RS[1] = false;
        }

        this.xSize = 176;
        this.ySize = 166;

    }

    @Override
    protected void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableLighting();

        this.itemRenderer.renderItemAndEffectIntoGUI(new ItemStack(Item.getItemFromBlock(ModBlocks.xpBlock)), sx + 49, sy + 8);

        GlStateManager.disableLighting();

        this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_interface_gui.png"));
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if (te.isConnected()){

            int high = getHeightScaled(58);
            this.blit(sx + 83, sy + 66 - high, 176, 0, 9, high);
            int highF = getHeightScaledFluid(58);
            this.blit(sx + 110, sy + 66 - highF, 186, 0, 16, highF);

        }

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Items";
        String s2 = "Fluids";
        String s3 = String.valueOf(te.xp / XPHelper.getXPforLevelDiff(0, XPHelper.getLevelforBlocks(1)));
        this.font.drawString(s, 8, 40, 4210752);            //#404040
        this.font.drawString(s2, 135, 40, 4210752);
        this.font.drawString(s3, 50, 30, 4210752);

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(mouseButton == 0) {
            if (mouseX >= sx + xSize + 7 && mouseX <= sx + xSize + 28) {
                if (mouseY >= sy + 5 && mouseY <= sy + 25) {
                    this.RS[0] = true;
                    this.te.setRS(EnumRSMode.REDSTONE_IGNORED.getID());
                    this.RS[1] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_IGNORED.getID(), this.te.getPos()));
                }
                else if (mouseY >= sy + 30 && mouseY <= sy + 50) {
                    this.RS[1] = true;
                    this.te.setRS(EnumRSMode.REDSTONE_OFF.getID());
                    this.RS[0] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_OFF.getID(), this.te.getPos()));
                }
                else if (mouseY >= sy + 54 && mouseY <= sy + 74) {
                    this.RS[2] = true;
                    this.te.setRS(EnumRSMode.REDSTONE_ON.getID());
                    this.RS[0] = this.RS[1] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_ON.getID(), this.te.getPos()));
                }
            }
        }
        super.init();

        return true;

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

    @Override
    protected void onButtonPress(Button button) {

    }
}
