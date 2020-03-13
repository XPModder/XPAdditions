package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import java.util.Random;

public class Miner extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_MINER.getID();
    private static String[][] Effects = new String[7][10];
    private static Item[] Tools = {
            Item.getItemFromBlock(Blocks.STONE),
            Item.getItemFromBlock(Blocks.COBBLESTONE),
            Item.getItemFromBlock(Blocks.SANDSTONE)
    };


    public Miner(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Miner() {
        this(0,0);
    }

    @Override
    public void update(World worldIn, EntityPlayerMP playerIn) {

        int num = 0;
        Random rand = new Random();

        if(rand.nextInt(10) == 1){      //There is a 10% chance to loose xp each update cycle
            this.xp --;
        }

        for(StatBase statBase: StatList.USE_ITEM_STATS) {

            for (Item item : Tools) {           //Loop through the Items

                if (statBase.getStatName().getFormattedText().contains(item.getItemStackDisplayName(new ItemStack(item)))) {         //get the uses of the current item
                    num += playerIn.getStatFile().readStat(statBase);       //and increase the number by that value
                }

            }

        }

        if (num > this.Counter) {       //When the number is bigger than the pervious one
            this.xp++;                  //Increase the xp
            this.Counter = num;         //And set the Counter to our new value
        }

        if(this.xp < 0){
            this.xp = 0;        //When we End up with a negative xp value, correct that.
        }
        else if(this.xp >= ((this.Level + 1) ^ 2) * 100){     //When we reach the threshhold for the next Level, we will level up!
            this.Level++;
        }
        else if(this.xp < (this.Level ^ 2) * 100){            //when we fall below the threshhold for the current level, we will level down!
            this.Level--;
        }

    }
}
