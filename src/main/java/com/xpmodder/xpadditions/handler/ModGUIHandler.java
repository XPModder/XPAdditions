package com.xpmodder.xpadditions.handler;

import com.xpmodder.xpadditions.client.gui.XPControllerGui;
import com.xpmodder.xpadditions.client.gui.XPInterfaceGui;
import com.xpmodder.xpadditions.tileentity.XPControllerContainer;
import com.xpmodder.xpadditions.tileentity.XPControllerTileEntity;
import com.xpmodder.xpadditions.tileentity.XPInterfaceContainer;
import com.xpmodder.xpadditions.tileentity.XPInterfaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGUIHandler implements IGuiHandler {

    public static final int XP_Interface_GUI = 0;
    public static final int XP_Controller_GUI = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID){

            case XP_Interface_GUI:
                return new XPInterfaceContainer(player.inventory, (XPInterfaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

            case XP_Controller_GUI:
                return new XPControllerContainer(player.inventory, (XPControllerTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

            default:
                return null;

        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        switch(ID){

            case XP_Interface_GUI:
                return new XPInterfaceGui(player.inventory, (XPInterfaceTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

            case XP_Controller_GUI:
                return new XPControllerGui(player.inventory, (XPControllerTileEntity) world.getTileEntity(new BlockPos(x, y, z)));

            default:
                return null;

        }

    }

}
