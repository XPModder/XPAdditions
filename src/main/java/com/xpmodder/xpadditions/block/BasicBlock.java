package com.xpmodder.xpadditions.block;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block{

    public BasicBlock(String unlocalizedName, Material material, float hardness, float resistance) {
        super(
                Block.Properties.create(material)
                        .hardnessAndResistance(hardness, resistance)
        );
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
    }

    public BasicBlock(String unlocalizedName, float hardness, float resistance) {
        this(unlocalizedName, Material.ROCK, hardness, resistance);
    }

    public BasicBlock(String unlocalizedName) {
        this(unlocalizedName, 2.0f, 10.0f);
    }

}
