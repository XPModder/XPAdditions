package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.handler.ModBucketHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.StringUtils;

public class ItemBucketXPA extends ItemBucket {

    private String itemName;

    public ItemBucketXPA(Block containedBlockIn, String fluidName) {

        super(containedBlockIn);
        setCreativeTab(CreativeTabXPA.XPA_TAB);
        setContainerItem(Items.BUCKET);
        itemName = "bucket" + StringUtils.capitalize(fluidName);
        setUnlocalizedName(itemName);
        setRegistryName(itemName);

    }

    public void init() {
        GameRegistry.register(this);
    }

    public String getItemName() {
        return itemName;
    }

    public static ItemBucketXPA create(BlockFluidClassic block, Fluid fluid){

        ItemBucketXPA b = new ItemBucketXPA(block != null ? block : Blocks.AIR, fluid.getName());
        b.init();
        FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(b), new ItemStack(Items.BUCKET));
        if (block != null) {
            ModBucketHandler.instance.registerFluid(block, b);
        }

        return b;

    }

}
