package com.xpmodder.xpadditions.config;

public enum ConfigIntListValues {

    OIL_POWER("Oil Gen: Power Values", ConfigCategories.OTHER, new int[]{40, 80, 100, 120}, "The amount of power that the 4 tiers of oils generate in CF/t.  Ordered.");

    public final String name;
    public final String category;
    public final int[] defaultValue;
    public final String desc;

    public int[] currentValue;

    ConfigIntListValues(String name, ConfigCategories category, int[] defaultValue, String desc){
        this.name = name;
        this.category = category.name;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public int[] getValue(){
        return this.currentValue;
    }

}
