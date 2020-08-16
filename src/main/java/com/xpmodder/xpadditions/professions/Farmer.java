package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

import java.util.Random;

public class Farmer extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_FARMER.getID();
    private static String[][] Effects = new String[7][10];
    private static Item[] Tools = {
            Items.WOODEN_HOE,
            Items.STONE_HOE,
            Items.IRON_HOE,
            Items.GOLDEN_HOE,
            Items.DIAMOND_HOE,
            Items.BONE
    };

    public Farmer(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Farmer() {
        this(0,0);
    }

    @Override
    public void update(World worldIn, ServerPlayerEntity playerIn) {

        int num = 0;
        Random rand = new Random();

        if(rand.nextInt(10) == 1){      //There is a 10% chance to loose xp each update cycle
            this.xp --;
        }

        for(Stat statBase: Stats.ITEM_USED) {

            for (Item item : Tools) {           //Loop through the Items

                if (statBase.getName().contains(item.getName().getString())) {         //get the uses of the current item
                    num += playerIn.getStats().getValue(statBase);       //and increase the number by that value
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
