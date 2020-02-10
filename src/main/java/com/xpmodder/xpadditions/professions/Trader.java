package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class Trader extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_TRADER.getID();
    private static String[][] Effects = new String[7][10];
    private static String[] Tools = {
            Items.EMERALD.getUnlocalizedName(),
            Items.ENCHANTED_BOOK.getUnlocalizedName(),
            Items.BOOK.getUnlocalizedName()
    };

    public Trader(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Trader() {
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
        if (num > this.OldNum){
            this.Counter = 100;
        }
        else{
            this.Counter--;
        }

    }

}
