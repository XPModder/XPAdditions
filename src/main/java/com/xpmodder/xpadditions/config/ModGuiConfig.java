package com.xpmodder.xpadditions.config;



import net.minecraft.client.gui.screen.Screen;


public class ModGuiConfig {

    public ModGuiConfig(Screen guiScreen) {

        /*
        super(guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));

         */
    }

    /*
    private static List<IConfigElement> getConfigElements(){
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        for(int i = 0; i < ConfigCategories.values().length; i++){
            ConfigCategories cat = ConfigCategories.values()[i];
            ConfigurationHandler.configuration.setCategoryComment(cat.name, cat.comment);
            list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(cat.name)));
        }
        return list;
    }

     */

}
