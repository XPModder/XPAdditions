package com.xpmodder.xpadditions.capabilities;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class ModCapabilities {

    private static final ResourceLocation PROFESSION_CAP = new ResourceLocation("profession", Reference.MOD_ID);

    public static void register(){
        CapabilityManager.INSTANCE.register(IPlayerProfessionCapability.class, new PlayerProfessionStorage(), new PlayerProfessionCallable());

    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event){

        if (event.getObject() instanceof EntityPlayer){
            event.addCapability(PROFESSION_CAP, new PlayerProfessionProvider());
        }

    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event){
        EntityPlayer player = event.getEntityPlayer();
        IPlayerProfessionCapability professionCapability = player.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);
        IPlayerProfessionCapability oldProfessionCapability = event.getOriginal().getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);

        professionCapability.setProfession(oldProfessionCapability.getProfession());
        professionCapability.setLastNum(oldProfessionCapability.getLastNum());
        professionCapability.setLevel(oldProfessionCapability.getLevel());

    }

}
