package com.xpmodder.xpadditions.proxy;

import com.xpmodder.xpadditions.client.render.blocks.blockRenderRegister;
import com.xpmodder.xpadditions.client.render.items.itemRenderRegister;
import com.xpmodder.xpadditions.fluid.ModFluids;
import com.xpmodder.xpadditions.handler.ParticleTextureHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {

        super.preInit(e);

        MinecraftForge.EVENT_BUS.register(new ParticleTextureHandler());
        blockRenderRegister.preInit();

        ModFluids.registerExp();

    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        itemRenderRegister.registerItemRenderer();
        blockRenderRegister.registerBlockRenderer();

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}
