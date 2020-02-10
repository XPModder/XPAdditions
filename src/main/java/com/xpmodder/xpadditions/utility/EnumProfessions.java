package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumProfessions implements IStringSerializable {

    PROFESSION_NONE(0, "None"),
    PROFESSION_MINER(1, "Miner"),
    PROFESSION_FARMER(2, "Farmer"),
    PROFESSION_BLACKSMITH(3, "Blacksmith"),
    PROFESSION_BUILDER(4, "Builder"),
    PROFESSION_SOLDIER(5, "Soldier"),
    PROFESSION_TRADER(6, "Trader");

    private int ID;
    private String name;

    private EnumProfessions(int ID, String name){
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
