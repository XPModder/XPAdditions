package com.xpmodder.xpadditions.fluid;


import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class XPTankBlock extends Block{

    public XPTankBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);
        this.setHardness(hardness);
        this.setResistance(resistance);
    }

    public XPTankBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.IRON, hardness, resistance);
    }

    public XPTankBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }



}
