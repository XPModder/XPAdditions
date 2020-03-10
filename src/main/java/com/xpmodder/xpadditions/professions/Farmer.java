package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class Farmer extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_FARMER.getID();
    private static String[][] Effects = new String[7][10];
    private static String[] Tools = {
            Items.WOODEN_HOE.getUnlocalizedName(),
            Items.STONE_HOE.getUnlocalizedName(),
            Items.IRON_HOE.getUnlocalizedName(),
            Items.GOLDEN_HOE.getUnlocalizedName(),
            Items.DIAMOND_HOE.getUnlocalizedName(),
            Items.BONE.getUnlocalizedName()
    };

    public Farmer(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Farmer() {
        this(0,0);
    }

    @Override
    public void update(World worldIn, EntityPlayerMP playerIn) {

        int num = 0;
        StatBase statBase = StatList.ANIMALS_BRED;
        num += playerIn.getStatFile().readStat(statBase);
        for (int i = 0; i < StatList.USE_ITEM_STATS.size(); i++){
            statBase = StatList.USE_ITEM_STATS.get(i);
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
