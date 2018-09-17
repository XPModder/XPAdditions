package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;


public class BookGui extends GuiScreen {

    int xSize = 176;
    int ySize = 166;
    int guiLeft = 0;
    int guiTop = 0;

    public BookGui(IInventory playerInv){

        super();

        this.xSize = 176;
        this.ySize = 166;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawBackgroundLayer(partialTicks, mouseX, mouseY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawForegroundLayer(mouseX, mouseY);

    }

    public void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/book_gui.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    }

    public void drawForegroundLayer(int mouseX, int mouseY) {

        String s = "XPAdditions Guide";
        this.fontRenderer.drawString(s, (this.width / 2), 0, 4210752);            //#404040

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);

        super.initGui();
    }

}
