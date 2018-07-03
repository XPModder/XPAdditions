package com.xpmodder.xpadditions.config;


import com.xpmodder.xpadditions.handler.ConfigurationHandler;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen) {

        super(guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

    private static List<IConfigElement> getConfigElements(){
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        for(int i = 0; i < ConfigCategories.values().length; i++){
            ConfigCategories cat = ConfigCategories.values()[i];
            ConfigurationHandler.configuration.setCategoryComment(cat.name, cat.comment);
            list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(cat.name)));
        }
        return list;
    }

}
