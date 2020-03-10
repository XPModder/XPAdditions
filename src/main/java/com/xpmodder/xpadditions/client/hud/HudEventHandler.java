package com.xpmodder.xpadditions.client.hud;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class HudEventHandler {

    private static ProfessionHUD professionHUD = new ProfessionHUD();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onDrawScreenPre(RenderGameOverlayEvent.Pre event){
        professionHUD.Render(event);
    }

    @SubscribeEvent
    public static void onDrawScreenPost(RenderGameOverlayEvent.Post event){

    }

}
