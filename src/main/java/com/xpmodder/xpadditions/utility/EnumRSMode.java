package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumRSMode implements IStringSerializable {

    REDSTONE_IGNORED(0, "redstone_ignored"),
    REDSTONE_OFF(1, "redstone_off"),
    REDSTONE_ON(2, "redstone_on");

    private int ID;
    private String name;

    private EnumRSMode(int ID, String name) {
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
