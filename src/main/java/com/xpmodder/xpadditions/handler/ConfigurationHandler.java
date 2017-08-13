package com.xpmodder.xpadditions.handler;


import com.xpmodder.xpadditions.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {

    public Configuration configuration;

    public Property starBlockRadius;

    public ConfigurationHandler(File configFile){

        configuration = new Configuration(configFile);
        reload();

    }

    void reload(){

        configuration.load();

        starBlockRadius = configuration.get(Configuration.CATEGORY_GENERAL, "starBlockRadius", 20, "This is the radius in which a Star Block spawns lightsources!");

        update();

    }

    @SubscribeEvent
    public void OnConfigChanged(ConfigChangedEvent event) {

        if (event.getModID().equals(Reference.MOD_ID)) update();

    }

    void update(){

        starBlockRadius.set(Math.min(Math.max(starBlockRadius.getInt(20), 40), 4));

        configuration.save();

    }

}


