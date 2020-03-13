package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

import java.util.Random;

public class Soldier extends ModProfessions {

    private static int ID = EnumProfessions.PROFESSION_SOLDIER.getID();
    private static String[][] Effects = new String[7][10];
    private static Item[] Tools = {
            Items.BOW,
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
            Items.DIAMOND_BOOTS
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

    }

}
