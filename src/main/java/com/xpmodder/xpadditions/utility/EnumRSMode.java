package com.xpmodder.xpadditions.utility;

import net.minecraft.util.IStringSerializable;

public enum EnumRSMode implements IStringSerializable {

    REDSTONE_OFF(0, "redstone_off"),
    REDSTONE_ON(1, "redstone_on"),
    REDSTONE_IGNORED(2, "redstone_ignored");

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
