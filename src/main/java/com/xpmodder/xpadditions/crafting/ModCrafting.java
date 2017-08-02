package com.xpmodder.xpadditions.crafting;

import com.xpmodder.xpadditions.init.ModBlocks;
import com.xpmodder.xpadditions.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {

    public static void initCrafting(){

        GameRegistry.addRecipe(new ItemStack(ModItems.mediumStarItem), new Object[] {"###", "###", "###", '#', ModItems.starItem});
        GameRegistry.addRecipe(new ItemStack(ModItems.bigStarItem), new Object[] {"###", "###", "###", '#', ModItems.mediumStarItem});
        GameRegistry.addRecipe(new ItemStack(ModItems.denseStarItem), new Object[] {"###", "###", "###", '#', ModItems.bigStarItem});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.starfieldBlock, 2), new Object[] {"LLL", "LSL", "LLL", 'L', Blocks.LAPIS_BLOCK, 'S', ModItems.starItem});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.starBlock), new Object[] {"BBB", "BIB", "BBB", 'B', ModBlocks.starfieldBlock, 'I', ModItems.mediumStarItem});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.xpBlock), new Object[] {"###", "###", "###", '#', ModItems.xpOrbItem});


        GameRegistry.addShapelessRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE), new Object[] {Items.GLASS_BOTTLE, ModItems.xpOrbItem, ModItems.xpOrbItem, ModItems.xpOrbItem});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.xpOrbItem, 3), new Object[] {Items.EXPERIENCE_BOTTLE});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.starItem, 9), new Object[] {ModItems.mediumStarItem});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.mediumStarItem, 9), new Object[] {ModItems.bigStarItem});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bigStarItem, 9), new Object[] {ModItems.denseStarItem});
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.xpOrbItem, 9), new Object[] {ModBlocks.xpBlock});

    }

}
