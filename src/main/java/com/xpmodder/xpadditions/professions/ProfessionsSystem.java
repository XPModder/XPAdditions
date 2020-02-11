package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.capabilities.IPlayerProfessionCapability;
import com.xpmodder.xpadditions.capabilities.PlayerProfessionProvider;
import com.xpmodder.xpadditions.utility.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.LinkedList;
import java.util.List;

public class ProfessionsSystem {

    private List<playerProfessionsElements> playerProfessions = new LinkedList<playerProfessionsElements>();
    private List<playerProfessionNumElements> playerNumProfessions = new LinkedList<playerProfessionNumElements>();
    private List<playerProfessionNumElements> playerCounters = new LinkedList<playerProfessionNumElements>();

    public void createProfession(int id, int level, int oldNum, EntityPlayer playerIn){

        ModProfessions professions;

        if(id == EnumProfessions.PROFESSION_BLACKSMITH.getID()){
            professions = new Blacksmith(level, oldNum);
        }
        else if(id == EnumProfessions.PROFESSION_BUILDER.getID()){
            professions = new Builder(level, oldNum);
        }
        else if(id == EnumProfessions.PROFESSION_FARMER.getID()){
            professions = new Farmer(level, oldNum);
        }
        else if(id == EnumProfessions.PROFESSION_MINER.getID()){
            professions = new Miner(level, oldNum);
        }
        else if(id == EnumProfessions.PROFESSION_SOLDIER.getID()){
            professions = new Soldier(level, oldNum);
        }
        else if(id == EnumProfessions.PROFESSION_TRADER.getID()){
            professions = new Trader(level, oldNum);
        }
        else{
            professions = new Blacksmith();
        }

        playerProfessions.add(new playerProfessionsElements(professions, playerIn.getName()));
        this.onPlayerSave(playerIn);

    }

    public void createProfession(int id, EntityPlayer playerIn){
        createProfession(id, 0, 0, playerIn);
    }

    public void onPlayerUpdate(World worldIn, EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return;
        }
        for (playerProfessionsElements element : playerProfessions){
            if(element.playerName.equals( playerIn.getName())){
                if (!worldIn.isRemote && playerIn instanceof EntityPlayerMP) {
                    element.profession.update(worldIn, (EntityPlayerMP) playerIn);
                }
            }
        }

    }

    public void onPlayerLoad(EntityPlayer playerIn){

        IPlayerProfessionCapability professionCapability = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);
        int profession = professionCapability.getProfession();
        int level = professionCapability.getLevel();
        int oldNum = professionCapability.getLastNum();

        if (profession != 0){
            this.createProfession(profession, level, oldNum, playerIn);
        }

    }

    public void onPlayerSave(EntityPlayer playerIn){

        IPlayerProfessionCapability professionCapability = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);

        professionCapability.setProfession(EnumProfessions.PROFESSION_NONE.getID());
        professionCapability.setLevel(EnumCareerLevel.LEVEL_NOVICE.getID());
        professionCapability.setLastNum(0);

        if (playerProfessions.isEmpty()){
            return;
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                professionCapability.setProfession(element.profession.ID);
                professionCapability.setLevel(element.profession.Level);
                professionCapability.setLastNum(element.profession.OldNum);
            }
        }

    }

    public boolean hasPlayerProfession(int profession, EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return false;
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                if (element.profession.ID == profession){
                    return true;
                }
            }
        }
        return false;

    }

    public boolean hasPlayerAnyProfession(EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return false;
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                return true;
            }
        }
        return false;

    }

    public int getPlayerProfession(EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return EnumProfessions.PROFESSION_NONE.getID();
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                return element.profession.ID;
            }
        }
        return EnumProfessions.PROFESSION_NONE.getID();

    }

    public String getPlayerProfessionName(EntityPlayer playerIn){

        int id = getPlayerProfession(playerIn);
        for (EnumProfessions profession : EnumProfessions.values()) {
            if (profession.getID() == id){
                return profession.getName();
            }
        }
        return EnumProfessions.PROFESSION_NONE.getName();

    }

    public int getPlayerProfessionNumber(EntityPlayer playerIn){

        int number = 0;
        for (playerProfessionsElements element : playerProfessions){
            if(element.playerName.equals(playerIn.getName())){
                number++;
            }
        }
        return number;

    }

    public int getPlayerAllowedNumber(EntityPlayer playerIn){

        for (playerProfessionNumElements element : playerNumProfessions){
            if(element.playerName.equals(playerIn.getName())){
                return element.num;
            }
        }
        return 0;

    }

    @SubscribeEvent
    public void onPlayerGetXP(PlayerPickupXpEvent event){
        int level = event.getEntityPlayer().experienceLevel;
        EntityPlayer player = event.getEntityPlayer();
        boolean found = false;

        for (playerProfessionNumElements element : playerNumProfessions) {
            if (element.playerName.equals(player.getName())) {
                found = true;
                if (level < 30) {
                    element.num = 0;
                    for (playerProfessionsElements elemet2 : playerProfessions){
                        if (elemet2.playerName.equals(player.getName())){
                            playerProfessions.remove(elemet2);
                            this.onPlayerSave(player);
                            break;
                        }
                    }
                }
                if (level >= 30 && level < 100) {
                    if (element.num == 0) {
                        element.num = 1;
                        player.sendMessage(new TextComponentString("You unlocked your first profession!"));
                        player.sendMessage(new TextComponentString("Select your profession in the XPAdditions Guide!"));
                    }
                }
            }
        }

        if (!found){
            playerNumProfessions.add(new playerProfessionNumElements(0, player.getName()));
        }

    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){

        onPlayerLoad(event.player);

    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event){

        onPlayerSave(event.player);

    }

    @SubscribeEvent
    public void onPlayerUpdateEvent(TickEvent.PlayerTickEvent event){

        EntityPlayer player = event.player;
        boolean found = false;

        if (player.experienceLevel < 30){
            for (playerProfessionNumElements element : playerNumProfessions) {
                if (element.playerName.equals(player.getName())) {
                    element.num = 0;
                    for (playerProfessionsElements element2 : playerProfessions){
                        if (element2.playerName.equals(player.getName())){
                            playerProfessions.remove(element2);
                            this.onPlayerSave(player);
                            break;
                        }
                    }

                }
            }
        }

        for (playerProfessionNumElements element : playerCounters){
            if (element.playerName.equals(player.getName())){
                found = true;
                element.num++;
                if (element.num == 1000){
                    onPlayerUpdate(player.world, player);
                    element.num = 0;
                }
            }
        }

        if (!found){
            playerCounters.add(new playerProfessionNumElements(0, player.getName()));
        }

    }

}
