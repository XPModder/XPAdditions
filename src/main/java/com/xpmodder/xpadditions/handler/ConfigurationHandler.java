package com.xpmodder.xpadditions.handler;


import com.xpmodder.xpadditions.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;
    public static boolean configValue = false;

    public static void init(File configFile){

        if (configuration == null) {

            configuration = new Configuration(configFile);
            loadConfiguration();

        }

    }

    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event){

        if (event.getModID() == Reference.MOD_ID){

            loadConfiguration();

        }

    }

    private static void loadConfiguration(){

        configValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This is an Example!");

        if (configuration.hasChanged()){

            configuration.save();
        }

    }

}


