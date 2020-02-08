package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class Miner extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_MINER.getID();
    private static String[][] Effects = new String[7][10];
    private static String[] validBlocks = {Blocks.STONE.getUnlocalizedName(), Blocks.COBBLESTONE.getUnlocalizedName(), Blocks.SANDSTONE.getUnlocalizedName()};


    public Miner(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Miner() {
        this(0,0);
    }

    @Override
    public void update(World worldIn, EntityPlayerMP playerIn) {

        int num = 0;

        for (int i = 0; i < StatList.MINE_BLOCK_STATS.size(); i++) {
            StatBase statBase = StatList.MINE_BLOCK_STATS.get(i);
            for (int j = 0; j < validBlocks.length; j++) {
                if (statBase.statId == validBlocks[j]) {
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
