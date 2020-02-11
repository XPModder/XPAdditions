package com.xpmodder.xpadditions.client.hud;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.xpmodder.xpadditions.handler.GeneralEventHandler.professionsSystem;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public final class ProfessionHUD {

    private ProfessionHUD() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onDrawScreenPre(RenderGameOverlayEvent.Pre event){

        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        if(professionsSystem != null) {
            String Text = "Your current profession is: ";
            Text += professionsSystem.getPlayerProfessionName(mc.player);

            mc.fontRenderer.drawString(Text, 10, 10, 4210752);
        }

    }

    @SubscribeEvent
    public static void onDrawScreenPost(RenderGameOverlayEvent.Post event){

    }


}
