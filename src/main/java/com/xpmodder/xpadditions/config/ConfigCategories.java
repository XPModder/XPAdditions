package com.xpmodder.xpadditions.config;

import java.util.Locale;

public enum ConfigCategories {

    UTILITY("Utility", "Settings for Utility Items/Blocks"),
    WORLD("World", "Setting for World Gen"),
    OTHER("Other", "Other Settings");

    public final String name;
    public final String comment;

    ConfigCategories(String name, String comment){
        this.name = name.toLowerCase(Locale.ROOT);
        this.comment = comment;
    }

}
