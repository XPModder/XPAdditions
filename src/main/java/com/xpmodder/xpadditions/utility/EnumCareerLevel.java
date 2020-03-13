package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumCareerLevel implements IStringSerializable {

    LEVEL_NOVICE(0, "Novice"),
    LEVEL_ADVANCED_BEGINNER(1, "Advanced Beginner"),
    LEVEL_AMATEUR(2, "Amateur"),
    LEVEL_COMPETENT(3, "Competent"),
    LEVEL_PROFICIENT(4, "Proficient"),
    LEVEL_MASTER(5, "Master"),
    LEVEL_EXPERT(6, "Expert");

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
