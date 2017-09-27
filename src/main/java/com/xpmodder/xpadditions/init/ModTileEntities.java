package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init(){

        GameRegistry.registerTileEntity(XPInterfaceTileEntity.class, Reference.MOD_ID + "_xp_interface_tile_entity");

    }

}
