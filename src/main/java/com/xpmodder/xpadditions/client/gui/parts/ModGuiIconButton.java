package com.xpmodder.xpadditions.client.gui.parts;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;


public class ModGuiIconButton extends Button {

    ResourceLocation texture;
    int textureX;
    int textureY;
    boolean on;

    public void onPressed(){

    }

    public ModGuiIconButton(int buttonId, int x, int y, int widthIn, int heightIn, boolean on, ResourceLocation buttonIcon, int textureX, int textureY, IPressable onButtonPress) {

        super(x, y, widthIn, heightIn, "", onButtonPress);
        this.texture = buttonIcon;
        this.textureX = textureX;
        this.textureY = textureY;
        this.on = on;


    }

    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks) {

        if (this.visible)
        {
            Minecraft mc = Minecraft.getInstance();
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(this.texture);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = 1;
            if(!this.on){
                i = 0;
            }
            else{
                if(this.on){
                    i = 2;
                }
                else{
                    i = 1;
                }
            }
            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.blit(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            this.blit(this.x, this.y, this.textureX, this.textureY, this.width, this.height);
            this.mouseDragged(mouseX, mouseY, 0, mouseX, mouseY);
            int j = 14737632;


            if (this.packedFGColor != 0)
            {
                j = this.packedFGColor;
            }
            else
            if (!this.on)
            {
                j = 10526880;
            }
            else if (this.isHovered)
            {
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
        }

    }

}
