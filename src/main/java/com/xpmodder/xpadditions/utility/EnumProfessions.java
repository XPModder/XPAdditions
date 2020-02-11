package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumProfessions implements IStringSerializable {

    PROFESSION_NONE(0, "None"),
    PROFESSION_BLACKSMITH(1, "Blacksmith"),
    PROFESSION_BUILDER(2, "Builder"),
    PROFESSION_FARMER(3, "Farmer"),
    PROFESSION_MINER(4, "Miner"),
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
