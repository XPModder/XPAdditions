package com.xpmodder.xpadditions.client.gui;

import com.xpmodder.xpadditions.client.gui.parts.ModGuiIconButton;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;



public abstract class ModBaseGui<T extends Container> extends ContainerScreen {

    public boolean[] RS = {false, false, false};
    private ResourceLocation buttonParts = new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png");

    public ModBaseGui(T inventorySlotsIn, PlayerInventory inv, String title) {

        super(inventorySlotsIn, inv, new StringTextComponent(title));

    }

    @Override
    public void init() {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        super.init();


        this.addButton(new ModGuiIconButton(0, sx + xSize + 7, sy + 5, 21, 20, RS[0], buttonParts, 200, 46, this::onButtonPress));
        this.addButton(new ModGuiIconButton(1, sx + xSize + 7, sy + 30, 21, 20, RS[1], buttonParts, 200, 66, this::onButtonPress));
        this.addButton(new ModGuiIconButton(2, sx + xSize + 7, sy + 54, 21, 20, RS[2], buttonParts, 200, 86, this::onButtonPress));

    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int sx = (width - xSize) / 2;
        int sy = (height - ySize) / 2;

        this.minecraft.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/parts/parts.png"));
        this.blit(sx + xSize + 2, sy, 224, 174, 32, 82);

        drawBackgroundLayer(partialTicks, mouseX, mouseY);

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        return super.mouseClicked(mouseX, mouseY, mouseButton);

    }

    protected abstract void drawBackgroundLayer(float partialTicks, int mouseX, int mouseY);

    protected abstract void onButtonPress(Button button);

}
