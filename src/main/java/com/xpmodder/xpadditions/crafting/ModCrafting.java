package com.xpmodder.xpadditions.crafting;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {

    public static void initCrafting(){

        GameRegistry.addRecipe(new ItemStack(ModItems.mediumStarItem), new Object[] {"###", "###", "###", '#', ModItems.starItem});
        GameRegistry.addRecipe(new ItemStack(ModItems.bigStarItem), new Object[] {"###", "###", "###", '#', ModItems.mediumStarItem});
        GameRegistry.addRecipe(new ItemStack(ModItems.denseStarItem), new Object[] {"###", "###", "###", '#', ModItems.bigStarItem});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.starfieldBlock, 2), new Object[] {"LLL", "LSL", "LLL", 'L', Blocks.LAPIS_BLOCK, 'S', ModItems.starItem});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.starBlock), new Object[] {"SSS", "SLS", "SSS", 'S', ModBlocks.starfieldBlock, 'L', Blocks.LAPIS_BLOCK});

    }

}
