package com.xpmodder.xpadditions.init;

import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import com.xpmodder.xpadditions.tileentity.XPPlayerConnectorTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init(){

        GameRegistry.registerTileEntity(XPInterfaceTileEntity.class, new ResourceLocation(Reference.MOD_ID, "xp_interface_tile_entity"));
        GameRegistry.registerTileEntity(XPControllerTileEntity.class, new ResourceLocation(Reference.MOD_ID, "_xp_controller_tile_entity"));
        GameRegistry.registerTileEntity(XPPlayerConnectorTileEntity.class, new ResourceLocation(Reference.MOD_ID, "_xp_player_connector_tile_entity"));

    }

}
