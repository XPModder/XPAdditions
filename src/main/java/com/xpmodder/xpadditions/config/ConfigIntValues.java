package com.xpmodder.xpadditions.config;

public enum ConfigIntValues {

    STAR_BLOCK_RADIUS("Star Block: Radius", ConfigCategories.UTILITY, 10, 1, 100, "The radius in which the Star Block generates Lightsources"),
    METEOR_DENSITY("Meteors: Density", ConfigCategories.WORLD, 10000, 1000, 10000000, "How rare or frequent Meteors are - Lower Number = more frequent"),
    ORE_DENSITY("XP Ore: Density", ConfigCategories.WORLD, 2, 1, 100, "How much XP Ore is generated");

    public final String name;
    public final String category;
    public final int defaultValue;
    public final int min;
    public final int max;
    public final String desc;

    public int currentValue;

    ConfigIntValues(String name, ConfigCategories category, int defaultValue, int min, int max, String desc){
        this.name = name;
        this.category = category.name;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
        this.desc = desc;
    }

    public int getValue(){
        return this.currentValue;
    }

}
