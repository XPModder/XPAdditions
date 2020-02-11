package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.EnumProfessions;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.TextHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Arrays;

import static com.xpmodder.xpadditions.handler.GeneralEventHandler.professionsSystem;


public class BookGui extends GuiScreen {

    int xSize = 250;
    int ySize = 202;
    int guiLeft = 0;
    int guiTop = 0;
    int page = 0;
    int maxPage = 10;
    boolean regularText = true;
    int textColor = 4210752;
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
        LogHelper.info(Arrays.toString(this.textHelper.getTextInLines(20)));

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
        this.drawTexturedModalRect((this.width / 2) - (this.xSize / 2), (this.height / 2) - (this.ySize / 2), 0, 0, this.xSize, this.ySize);

    }

    public void drawForegroundLayer(int mouseX, int mouseY) {

        EntityPlayer player = this.mc.player;

        int startX = (this.width / 2) - (this.xSize / 2);
        int startX2 = (this.width / 2);
        int startY = (this.height / 2) - (this.ySize / 2);
        int endX = (this.width / 2) + (this.xSize / 2);
        int endY = (this.height / 2) + (this.ySize / 2);
        int lineLength = 20;
        String s = "XPAdditions Guide";
        this.fontRenderer.drawString(s, (this.width / 2), 0, this.textColor);            //#404040
        this.maxPage = (int) Math.floor(this.textHelper.getLineNum(lineLength) / 38.0);

        if (professionsSystem.getPlayerProfessionNumber(player) < professionsSystem.getPlayerAllowedNumber(player)){

            this.regularText = false;
            this.fontRenderer.drawString("You have unlocked", startX + 10, startY + 10, this.textColor);
            this.fontRenderer.drawString("a profession!", startX + 10, startY + 20, this.textColor);
            this.fontRenderer.drawString("Select your preferred", startX + 10, startY + 40, this.textColor);
            this.fontRenderer.drawString("new profession on", startX + 10, startY + 50, this.textColor);
            this.fontRenderer.drawString("the right page!", startX + 10, startY + 60, this.textColor);

            this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png"));
            this.addButton(new GuiButton(0, startX2 + 4, startY + 10, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_BLACKSMITH.getName()));
            this.addButton(new GuiButton(1, startX2 + ((this.xSize / 4) + 2), startY + 10, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_BUILDER.getName()));
            this.addButton(new GuiButton(2, startX2 + 4, startY + 35, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_FARMER.getName()));
            this.addButton(new GuiButton(3, startX2 + ((this.xSize / 4) + 2), startY + 35, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_MINER.getName()));
            this.addButton(new GuiButton(4, startX2 + 4, startY + 60, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_SOLDIER.getName()));
            this.addButton(new GuiButton(5, startX2 + ((this.xSize / 4) + 2), startY + 60, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_TRADER.getName()));

        }
        else {

            this.regularText = true;
            String Text[] = this.textHelper.getTextInLines(lineLength);

            int j = 0;
            for (int i = (0 + (this.page * 38)); i < (19 + (this.page * 38)); i++) {

                if (i < Text.length) {
                    this.fontRenderer.drawString(Text[i], startX + 10, startY + (10 * j) + 8, this.textColor);
                    j++;
                }

            }
            j = 0;
            for (int i = (19 + (this.page * 38)); i < (38 + (this.page * 38)); i++) {

                if (i < Text.length) {
                    this.fontRenderer.drawString(Text[i], startX2 + 10, startY + (10 * j) + 8, this.textColor);
                    j++;
                }

            }

            //Arrows: 1: 2, 228 | 2: 25, 228 | 3: 2, 241 | 4: 25, 241 | Size: 19, 11

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/book_gui.png"));

            if (mouseY < endY + 11 && mouseY > endY) {
                if (mouseX < startX + 14 && mouseX > startX - 5) {
                    this.drawTexturedModalRect(startX - 5, endY, 25, 241, 19, 11);
                } else {
                    this.drawTexturedModalRect(startX - 5, endY, 2, 241, 19, 11);
                }
                if (mouseX < endX + 4 && mouseX > endX - 15) {
                    this.drawTexturedModalRect(endX - 15, endY, 25, 228, 19, 11);
                } else {
                    this.drawTexturedModalRect(endX - 15, endY, 2, 228, 19, 11);
                }
            } else {
                this.drawTexturedModalRect(startX - 5, endY, 2, 241, 19, 11);
                this.drawTexturedModalRect(endX - 15, endY, 2, 228, 19, 11);
            }

        }

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        int startX = (this.width / 2) - (this.xSize / 2);
        int endX = (this.width / 2) + (this.xSize / 2);
        int endY = (this.height / 2) + (this.ySize / 2);

        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (this.regularText) {
            if (mouseY < endY + 11 && mouseY > endY) {
                if (mouseX < startX + 14 && mouseX > startX - 5) {
                    if (this.page > 0)
                        this.page--;
                }
                if (mouseX < endX + 4 && mouseX > endX - 15) {
                    if (this.page < maxPage)
                        this.page++;
                }
            }
        }

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {

        if (!this.regularText) {

            professionsSystem.createProfession(button.id + 1, this.mc.player);

            buttonList.clear();
            this.regularText = true;

        }

    }

}
