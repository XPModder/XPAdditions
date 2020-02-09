package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.utility.EnumProfessions;
import com.xpmodder.xpadditions.utility.playerProfessionsElemets;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class ProfessionsSystem {

    private List<playerProfessionsElemets> playerProfessions;

    public void createMiner(EntityPlayer playerIn){
        Miner miner = new Miner();
        miner.onSave(playerIn);
        playerProfessions.add(new playerProfessionsElemets(miner, playerIn.getName()));
    }

    public void onPlayerUpdate(World worldIn, EntityPlayer playerIn){

        for (int i = 0; i < playerProfessions.size(); i++){
            playerProfessionsElemets Element = playerProfessions.get(i);
            if(Element.playerName == playerIn.getName()){
                Element.profession.update(worldIn, playerIn);
            }
        }

    }

    public void onPlayerLoad(World worldIn, EntityPlayer playerIn){

        int profession = playerIn.getEntityData().getInteger("xpadditions-Profession");
        int level = playerIn.getEntityData().getInteger("xpadditions-Level");
        int oldNum = playerIn.getEntityData().getInteger("xpadditions-LastNum");
        boolean addToList = false;
        ModProfessions professions;

        if(profession == EnumProfessions.PROFESSION_MINER.getID()){
            professions = new Miner(level, oldNum);
            addToList = true;
        }
        else if(profession == EnumProfessions.PROFESSION_FARMER.getID()){
            professions = new Farmer(level, oldNum);
            addToList = true;
        }
        else{
            professions = new Miner();
            addToList = false;
        }

        if(addToList) {
            playerProfessions.add(new playerProfessionsElemets(professions, playerIn.getName()));
        }

    }

    public void onPlayerSave(World worldIn, EntityPlayer playerIn){

        for (int i = 0; i < playerProfessions.size(); i++){
            if (playerProfessions.get(i).playerName == playerIn.getName()){
                playerProfessions.get(i).profession.onSave(playerIn);
            }
        }

    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){

        onPlayerLoad(event.player.world, event.player);

    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event){

        onPlayerSave(event.player.world, event.player);

    }

    @SubscribeEvent
    public void onPlayerUpdateEvent(TickEvent.PlayerTickEvent event){

        onPlayerUpdate(event.player.world, event.player);

    }

}
