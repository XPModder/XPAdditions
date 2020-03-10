package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class Soldier extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_SOLDIER.getID();
    private static String[][] Effects = new String[7][10];
    private static String[] Tools = {
            Items.BOW.getUnlocalizedName(),
            Items.IRON_SWORD.getUnlocalizedName(),
            Items.GOLDEN_SWORD.getUnlocalizedName(),
            Items.DIAMOND_SWORD.getUnlocalizedName(),
            Items.IRON_HELMET.getUnlocalizedName(),
            Items.GOLDEN_HELMET.getUnlocalizedName(),
            Items.DIAMOND_HELMET.getUnlocalizedName(),
            Items.IRON_CHESTPLATE.getUnlocalizedName(),
            Items.GOLDEN_CHESTPLATE.getUnlocalizedName(),
            Items.DIAMOND_CHESTPLATE.getUnlocalizedName(),
            Items.IRON_LEGGINGS.getUnlocalizedName(),
            Items.GOLDEN_LEGGINGS.getUnlocalizedName(),
            Items.DIAMOND_LEGGINGS.getUnlocalizedName(),
            Items.IRON_BOOTS.getUnlocalizedName(),
            Items.GOLDEN_BOOTS.getUnlocalizedName(),
            Items.DIAMOND_BOOTS.getUnlocalizedName()
    };

    public Soldier(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Soldier() {
        this(0,0);
    }

    @Override
    public void update(World worldIn, EntityPlayerMP playerIn) {

        int num = 0;
        for (int i = 0; i < StatList.USE_ITEM_STATS.size(); i++){
            StatBase statBase = StatList.USE_ITEM_STATS.get(i);
            for (int j = 0; j < Tools.length; j++){
                if (statBase.statId == Tools[j]){
                    num += playerIn.getStatFile().readStat(statBase);
                }
            }
        }
        if (num > this.xp){
            this.Counter = 100;
        }
        else{
            this.Counter--;
        }

    }

}
