package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumCareerLevel;
import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ModProfessions {

    protected int Level = 0;
    protected int ID;
    protected String[][] Effects = new String[7][10];
    protected int Counter = 100;
    protected int OldNum = 0;

    public ModProfessions(int id, int level, String[][] effects, int OldNum){
        this.ID = id;
        this.Level = level;
        this.Effects = effects;
        this.OldNum = OldNum;
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

    public void onSave(EntityPlayerMP playerIn){
        NBTTagCompound compound = playerIn.getEntityData();
        compound.setInteger("xpadditions-LastNum", this.OldNum);
        compound.setInteger("xpaddition-Level", this.Level);
        compound.setInteger("xpadditions-Profession", this.ID);
        playerIn.writeToNBT(compound);
    }

    public void onLoad(EntityPlayerMP playerIn){
        NBTTagCompound compound = playerIn.getEntityData();
        this.OldNum = compound.getInteger("xpadditions-LastNum");
        this.Level = compound.getInteger("xpadditions-Level");
        this.ID = compound.getInteger("xpadditions-Profession");
    }

    public abstract void update(World worldIn, EntityPlayerMP playerIn);

}
