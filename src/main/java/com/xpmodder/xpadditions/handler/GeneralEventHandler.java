package com.xpmodder.xpadditions.handler;

import com.xpmodder.xpadditions.professions.ProfessionsSystem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GeneralEventHandler {

    public static ProfessionsSystem professionsSystem;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event){

        professionsSystem = new ProfessionsSystem();
        MinecraftForge.EVENT_BUS.register(professionsSystem);

    }

}
