package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.network.MessageRedstoneSetting;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.ModBaseTileEntity;
import com.xpmodder.xpadditions.tileentity.XPControllerContainer;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class XPControllerGui extends ModBaseGui {

    private IInventory playerInv;
    private XPControllerTileEntity te;

    public XPControllerGui(final InventoryPlayer playerInv, XPControllerTileEntity te){

        super(new XPControllerContainer(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        if(te.getRS() == 0){
            this.RS[0] = true;
            this.RS[1] = this.RS[2] = false;
        }
        else if(te.getRS() == 1){
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
    public void initGui(){

        super.initGui();

    }

    @Override
    protected void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

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

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(mouseButton == 0) {
            if (mouseX >= sx + xSize + 7 && mouseX <= sx + xSize + 28) {
                if (mouseY >= sy + 5 && mouseY <= sy + 25) {
                    this.RS[0] = true;
                    this.te.setRS(0);
                    this.RS[1] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(0, this.te.getPos()));
                }
                else if (mouseY >= sy + 30 && mouseY <= sy + 50) {
                    this.RS[1] = true;
                    this.te.setRS(1);
                    this.RS[0] = this.RS[2] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(1, this.te.getPos()));
                }
                else if (mouseY >= sy + 54 && mouseY <= sy + 74) {
                    this.RS[2] = true;
                    this.te.setRS(2);
                    this.RS[0] = this.RS[1] = false;
                    XPAdditions.networkWrapper.sendToServer(new MessageRedstoneSetting(2, this.te.getPos()));
                }

            }
        }
        super.initGui();
    }

}
