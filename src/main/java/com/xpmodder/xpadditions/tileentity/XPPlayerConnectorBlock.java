package com.xpmodder.xpadditions.tileentity;

import com.xpmodder.xpadditions.creativetab.CreativeTabXPA;
import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class XPPlayerConnectorBlock extends Block {

    public XPPlayerConnectorBlock() {

        super(Material.IRON);
    }

    public XPPlayerConnectorBlock(String unlocalizedName) {

        super(Material.IRON);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(Reference.MOD_ID, unlocalizedName);
        this.setCreativeTab(CreativeTabXPA.XPA_TAB);

    }

}
