package com.xpmodder.xpadditions.client.hud;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID)
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
