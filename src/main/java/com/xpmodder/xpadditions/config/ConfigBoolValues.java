package com.xpmodder.xpadditions.config;

public enum ConfigBoolValues {

    GENERATE_METEORS("Meteors", ConfigCategories.WORLD, true, "Should Meteors be generated in the World?"),
    GENERATE_OVERWORLD_ORE("Overworld XP Ore", ConfigCategories.WORLD, true, "Should XP Ore be generated in the Overworld?"),
    GENERATE_NETHER_ORE("Nether XP Ore", ConfigCategories.WORLD, true, "Should XP Ore be generated in the Nether?"),
    GENERATE_END_ORE("End XP Ore", ConfigCategories.WORLD, true, "Should XP Ore be generated in the End?");

    public final String name;
    public final String category;
    public final boolean defaultValue;
    public final String desc;

    public boolean currentValue;

    ConfigBoolValues(String name, ConfigCategories category, boolean defaultValue, String desc){
        this.name = name;
        this.category = category.name;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public boolean isEnabled(){
        return this.currentValue;
    }

}
