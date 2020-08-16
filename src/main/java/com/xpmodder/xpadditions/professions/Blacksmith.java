package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;

import java.util.Random;

public class Blacksmith extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_BLACKSMITH.getID();
    private static String[][] Effects = new String[7][10];
    private static Item[] Tools = {
            Items.IRON_HOE,
            Items.GOLDEN_HOE,
            Items.DIAMOND_HOE,
            Items.IRON_AXE,
            Items.GOLDEN_AXE,
            Items.DIAMOND_AXE,
            Items.IRON_SHOVEL,
            Items.GOLDEN_SHOVEL,
            Items.DIAMOND_SHOVEL,
            Items.IRON_PICKAXE,
            Items.GOLDEN_PICKAXE,
            Items.DIAMOND_PICKAXE,
            Items.IRON_SWORD,
            Items.GOLDEN_SWORD,
            Items.DIAMOND_SWORD,
            Items.IRON_HELMET,
            Items.GOLDEN_HELMET,
            Items.DIAMOND_HELMET,
            Items.IRON_CHESTPLATE,
            Items.GOLDEN_CHESTPLATE,
            Items.DIAMOND_CHESTPLATE,
            Items.IRON_LEGGINGS,
            Items.GOLDEN_LEGGINGS,
            Items.DIAMOND_LEGGINGS,
            Items.IRON_BOOTS,
            Items.GOLDEN_BOOTS,
            Items.DIAMOND_BOOTS,
            Items.IRON_HORSE_ARMOR,
            Items.GOLDEN_HORSE_ARMOR,
            Items.DIAMOND_HORSE_ARMOR
    };

    public Blacksmith(int level, int oldNum){
        super(ID, level, Effects, oldNum);
    }

    public Blacksmith() {
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
            this.xp += 10;              //Increase the xp
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
