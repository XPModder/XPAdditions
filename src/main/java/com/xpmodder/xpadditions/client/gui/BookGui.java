package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.TextHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Arrays;


public class BookGui extends GuiScreen {

    int xSize = 250;
    int ySize = 202;
    int guiLeft = 0;
    int guiTop = 0;
    int page = 0;
    ResourceLocation bookText = new ResourceLocation(Reference.MOD_ID, "other/book.txt");
    TextHelper textHelper;

    public BookGui(IInventory playerInv){

        super();

        this.xSize = 250;
        this.ySize = 202;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.page = 0;
        this.textHelper = new TextHelper();
        this.textHelper.setFileText(bookText);
        LogHelper.info(Arrays.toString(this.textHelper.getTextLines(20)));

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
        this.drawTexturedModalRect((this.width / 2) - (this.xSize / 2), (this.height / 2) - (this.ySize / 2), 0, 0, xSize, ySize);

    }

    public void drawForegroundLayer(int mouseX, int mouseY) {

        int startX = (this.width / 2) - (this.xSize / 2);
        int startX2 = (this.width / 2);
        int startY = (this.height / 2) - (this.ySize / 2);
        int lineLength = 20;
        String s = "XPAdditions Guide";
        this.fontRenderer.drawString(s, (this.width / 2), 0, 4210752);            //#404040

        String Text[] = this.textHelper.getTextLines(lineLength);

        int j = 0;
        for(int i = 0; i < 19; i++){

            this.fontRenderer.drawString(Text[i], startX + 10, startY + (10 * j) + 8, 4210752);
            j++;

        }
        j = 0;
        for(int i = 19; i < 38; i++){

            this.fontRenderer.drawString(Text[i], startX2 + 10, startY + (10 * j) + 8, 4210752);
            j++;

        }

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);

        super.initGui();
    }

}
