package com.xpmodder.xpadditions;

import com.xpmodder.xpadditions.proxy.IProxy;
import com.xpmodder.xpadditions.reference.Reference;
import com.xpmodder.xpadditions.utility.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class XPAdditions {


    @SidedProxy(clientSide = Reference.ClientProxyClass, serverSide = Reference.ServerProxyClass)
    public static IProxy proxy;



    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent event){


        LogHelper.info("PreInitialization complete!");
    }

    @Mod.EventHandler
    public void Init (FMLInitializationEvent event){


        LogHelper.info("Initialization complete!");
    }

    @Mod.EventHandler
    public void postInit (FMLPostInitializationEvent event){


        LogHelper.info("PostInitialization complete!");
    }

}
