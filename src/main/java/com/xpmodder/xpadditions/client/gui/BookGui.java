package com.xpmodder.xpadditions.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.EnumProfessions;
import com.xpmodder.xpadditions.utility.LogHelper;
import com.xpmodder.xpadditions.utility.TextHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.Arrays;

import static com.xpmodder.xpadditions.handler.GeneralEventHandler.professionsSystem;


public class BookGui extends Screen {

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

        super(new StringTextComponent("XPAdditions Guide"));

        this.xSize = 250;
        this.ySize = 202;
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        this.page = 0;
        this.textHelper = new TextHelper();
        this.textHelper.setFileText(bookText);
        LogHelper.info(Arrays.toString(this.textHelper.getTextInLines(20)));

    }

    //Note: drawTextureModelRect -> this.blit(x,y,u,v,width,height)

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        this.drawBackgroundLayer(partialTicks, mouseX, mouseY);
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.disableDepthTest();
        super.render(mouseX, mouseY, partialTicks);
        this.drawForegroundLayer(mouseX, mouseY);

    }

    public void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/book_gui.png"));
        this.blit((this.width / 2) - (this.xSize / 2), (this.height / 2) - (this.ySize / 2), 0, 0, this.xSize, this.ySize);

    }

    public void drawForegroundLayer(int mouseX, int mouseY) {

        PlayerEntity player = this.minecraft.player;

        int startX = (this.width / 2) - (this.xSize / 2);
        int startX2 = (this.width / 2);
        int startY = (this.height / 2) - (this.ySize / 2);
        int endX = (this.width / 2) + (this.xSize / 2);
        int endY = (this.height / 2) + (this.ySize / 2);
        int lineLength = 20;

        this.maxPage = (int) Math.floor(this.textHelper.getLineNum(lineLength) / 38.0);

        if (professionsSystem.getPlayerProfessionNumber(player) < professionsSystem.getPlayerAllowedNumber(player)){

            this.regularText = false;
            this.font.drawString("You have unlocked", startX + 10, startY + 10, this.textColor);
            this.font.drawString("a profession!", startX + 10, startY + 20, this.textColor);
            this.font.drawString("Select your preferred", startX + 10, startY + 40, this.textColor);
            this.font.drawString("new profession on", startX + 10, startY + 50, this.textColor);
            this.font.drawString("the right page!", startX + 10, startY + 60, this.textColor);

            this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png"));
            this.addButton(new Button(startX2 + 4, startY + 10, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_BLACKSMITH.getName(), this::onButtonPressed));
            this.addButton(new Button(startX2 + ((this.xSize / 4) + 2), startY + 10, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_BUILDER.getName(), this::onButtonPressed));
            this.addButton(new Button(startX2 + 4, startY + 35, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_FARMER.getName(), this::onButtonPressed));
            this.addButton(new Button(startX2 + ((this.xSize / 4) + 2), startY + 35, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_MINER.getName(), this::onButtonPressed));
            this.addButton(new Button(startX2 + 4, startY + 60, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_SOLDIER.getName(), this::onButtonPressed));
            this.addButton(new Button(startX2 + ((this.xSize / 4) + 2), startY + 60, (this.xSize / 4) - 6, 20, EnumProfessions.PROFESSION_TRADER.getName(), this::onButtonPressed));



        }
        else {

            this.regularText = true;
            String Text[] = this.textHelper.getTextInLines(lineLength);

            int j = 0;
            for (int i = (0 + (this.page * 38)); i < (19 + (this.page * 38)); i++) {

                if (i < Text.length) {
                    this.font.drawString(Text[i], startX + 10, startY + (10 * j) + 8, this.textColor);
                    j++;
                }

            }
            j = 0;
            for (int i = (19 + (this.page * 38)); i < (38 + (this.page * 38)); i++) {

                if (i < Text.length) {
                    this.font.drawString(Text[i], startX2 + 10, startY + (10 * j) + 8, this.textColor);
                    j++;
                }

            }

            //Arrows: 1: 2, 228 | 2: 25, 228 | 3: 2, 241 | 4: 25, 241 | Size: 19, 11

            GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
            this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/book_gui.png"));

            if (mouseY < endY + 11 && mouseY > endY) {
                if (mouseX < startX + 14 && mouseX > startX - 5) {
                    this.blit(startX - 5, endY, 25, 241, 19, 11);
                } else {
                    this.blit(startX - 5, endY, 2, 241, 19, 11);
                }
                if (mouseX < endX + 4 && mouseX > endX - 15) {
                    this.blit(endX - 15, endY, 25, 228, 19, 11);
                } else {
                    this.blit(endX - 15, endY, 2, 228, 19, 11);
                }
            } else {
                this.blit(startX - 5, endY, 2, 241, 19, 11);
                this.blit(endX - 15, endY, 2, 228, 19, 11);
            }

        }

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton){

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

        super.init();

        return true;

    }

    public void onButtonPressed(Button button){

        if (!this.regularText) {

            professionsSystem.createProfession(EnumProfessions.valueOf(button.getMessage()).getID(), this.minecraft.player);

            buttons.clear();
            this.regularText = true;

        }

    }

}
