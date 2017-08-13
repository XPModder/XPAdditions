package com.xpmodder.xpadditions.client.gui;


import com.xpmodder.xpadditions.XPAdditions;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen) {

        super(guiScreen,
                new ConfigElement(XPAdditions.instance.config.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(XPAdditions.instance.config.configuration.toString()));
    }
}
