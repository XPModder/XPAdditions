package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.client.gui.parts.ModGuiIconButton;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public abstract class ModBaseGui extends GuiContainer {

    public boolean[] RS = {false, false, false};
    private ResourceLocation buttonParts = new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png");

    public ModBaseGui(Container inventorySlotsIn) {

        super(inventorySlotsIn);

    }

    @Override
    public void initGui() {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        super.initGui();


        this.addButton(new ModGuiIconButton(0, sx + xSize + 7, sy + 5, 21, 20, RS[0], buttonParts, 200, 46));
        this.addButton(new ModGuiIconButton(1, sx + xSize + 7, sy + 30, 21, 20, RS[1], buttonParts, 200, 66));
        this.addButton(new ModGuiIconButton(2, sx + xSize + 7, sy + 54, 21, 20, RS[2], buttonParts, 200, 86));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png"));
        this.drawTexturedModalRect(sx + xSize + 2, sy, 224, 174, 32, 82);

        drawBackgroundLayer(partialTicks, mouseX, mouseY);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

        super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    protected abstract void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY);

}
