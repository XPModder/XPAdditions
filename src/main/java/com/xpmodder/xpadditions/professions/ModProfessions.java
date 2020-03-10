package com.xpmodder.xpadditions.professions;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public abstract class ModProfessions {

    protected int Level = 0;
    protected int ID;
    protected String[][] Effects = new String[7][10];
    protected int Counter = 100;
    protected int xp = 0;

    public ModProfessions(int id, int level, String[][] effects, int OldNum){
        this.ID = id;
        this.Level = level;
        this.Effects = effects;
        this.xp = OldNum;
    }

    public ModProfessions(int id, String[][] effects){
        this(id, 0, effects, 0);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String[][] getEffects() {
        return Effects;
    }

    public void setEffects(String[][] effects) {
        Effects = effects;
    }

    public String getEffect(int Level, int Effect){
        return Effects[Level][Effect];
    }

    public abstract void update(World worldIn, EntityPlayerMP playerIn);

}
