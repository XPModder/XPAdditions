package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.tileentity.XPPlayerConnectorContainer;
import com.xpmodder.xpadditions.tileentity.XPPlayerConnectorTileEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;

public class XPPlayerConnectorGui extends ModBaseGui {

    private IInventory playerInv;
    private XPPlayerConnectorTileEntity te;

    public XPPlayerConnectorGui(PlayerInventory playerInv, XPPlayerConnectorTileEntity te) {

        super(new XPPlayerConnectorContainer(playerInv, te), playerInv, "XP Player Connector");

        this.playerInv = playerInv;
        this.te = te;

        this.xSize = 170;
        this.ySize = 120;

    }

    @Override
    protected void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){

    }

    @Override
    protected void onButtonPress(Button button) {

    }
}
