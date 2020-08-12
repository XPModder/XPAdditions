package com.xpmodder.xpadditions.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerContainer;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import com.xpmodder.xpadditions.utility.EnumRSMode;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;


public class XPControllerGui extends ModBaseGui {

    private IInventory playerInv;
    private XPControllerTileEntity te;

    public XPControllerGui(final PlayerInventory playerInv, XPControllerTileEntity te){

        super(new XPControllerContainer(playerInv, te), playerInv, "XPController");

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
    public void init(){

        super.init();

    }

    @Override
    protected void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/xp_controller_gui.png"));
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = "Total XP:";
        String s2 = String.valueOf(this.te.getTotalXP(this.te.getID()));
        this.font.drawString(s, 8, 40, 4210752);            //#404040
        this.font.drawString(s2, 8, 50, 4210752);

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
                    this.te.setRS(0);
                    this.RS[1] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_IGNORED.getID(), this.te.getPos()));
                }
                else if (mouseY >= sy + 30 && mouseY <= sy + 50) {
                    this.RS[1] = true;
                    this.te.setRS(1);
                    this.RS[0] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_OFF.getID(), this.te.getPos()));
                }
                else if (mouseY >= sy + 54 && mouseY <= sy + 74) {
                    this.RS[2] = true;
                    this.te.setRS(2);
                    this.RS[0] = this.RS[1] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(EnumRSMode.REDSTONE_ON.getID(), this.te.getPos()));
                }

            }
        }
        super.init();

        return true;
    }

    @Override
    protected void onButtonPress(Button button) {

    }
}
