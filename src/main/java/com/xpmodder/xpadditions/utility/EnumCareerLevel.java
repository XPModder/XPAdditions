package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumCareerLevel implements IStringSerializable {

    LEVEL_NOVICE(0, "level_novice"),
    LEVEL_ADVANCED_BEGINNER(1, "level_advanced_beginner"),
    LEVEL_AMATEUR(2, "level_amateur"),
    LEVEL_COMPETENT(3, "level_competent"),
    LEVEL_PROFICIENT(4, "level_proficient"),
    LEVEL_MASTER(5, "level_master"),
    LEVEL_EXPERT(6, "level_expert");

    private int ID;
    private String name;

    private EnumCareerLevel(int ID, String name){
        this.ID = ID;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return getName();
    }

}
