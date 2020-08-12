package com.xpmodder.xpadditions.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigValues {

    public static void defineConfigValues(ModConfig config){

        for(ConfigIntValues currConf : ConfigIntValues.values()){
            //currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc, currConf.min, currConf.max).getInt();
        }

        for(ConfigBoolValues currConf : ConfigBoolValues.values()){
            //currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getBoolean();
        }

        for(ConfigIntListValues currConf : ConfigIntListValues.values()){
            //currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getIntList();
        }

        for(ConfigStringListValues currConf : ConfigStringListValues.values()){
            //currConf.currentValue = config.get(currConf.category, currConf.name, currConf.defaultValue, currConf.desc).getStringList();
        }

    }

}
