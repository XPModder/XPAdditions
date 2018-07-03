package com.xpmodder.xpadditions.config;

public enum ConfigStringListValues {

    SACK_BLACKLIST("Sack Blacklist", ConfigCategories.OTHER, new String[0], "The items that aren't allowed to be put in the Traveller's Sack. Use REGISTRY NAMES, and if metadata is needed, add it like so: somemod:some_block@3");

    public final String name;
    public final String category;
    public final String[] defaultValue;
    public final String desc;

    public String[] currentValue;

    ConfigStringListValues(String name, ConfigCategories category, String[] defaultValue, String desc){
        this.name = name;
        this.category = category.name;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public String[] getValue(){
        return this.currentValue;
    }

}
