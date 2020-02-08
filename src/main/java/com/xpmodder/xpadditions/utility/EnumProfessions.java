package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumProfessions implements IStringSerializable {

    PROFESSION_MINER(0, "profession_miner"),
    PROFESSION_FARMER(1, "profession_farmer"),
    PROFESSION_BLACKSMITH(2, "profession_blacksmith"),
    PROFESSION_SOLDIER(3, "profession_soldier");

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
