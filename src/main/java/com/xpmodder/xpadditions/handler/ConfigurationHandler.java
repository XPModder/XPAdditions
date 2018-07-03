package com.xpmodder.xpadditions.handler;


import com.xpmodder.xpadditions.config.ConfigValues;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    public ConfigurationHandler(File configFile){

        MinecraftForge.EVENT_BUS.register(this);
        configuration = new Configuration(configFile);
        configuration.load();
        redefineConfigs();

    }

    @SubscribeEvent
    public void OnConfigChanged(ConfigChangedEvent event) {

        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) redefineConfigs();

    }


    public static void redefineConfigs(){

        ConfigValues.defineConfigValues(configuration);

        if(configuration.hasChanged()){
            configuration.save();
        }

    }

}


