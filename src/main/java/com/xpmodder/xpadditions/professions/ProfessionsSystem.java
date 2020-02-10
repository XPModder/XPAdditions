package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.capabilities.IPlayerProfessionCapability;
import com.xpmodder.xpadditions.capabilities.PlayerProfessionProvider;
import com.xpmodder.xpadditions.utility.EnumCareerLevel;
import com.xpmodder.xpadditions.utility.EnumProfessions;
import com.xpmodder.xpadditions.utility.playerProfessionNumElements;
import com.xpmodder.xpadditions.utility.playerProfessionsElemets;
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

    private List<playerProfessionsElemets> playerProfessions = new LinkedList<playerProfessionsElemets>();
    private List<playerProfessionNumElements> playerNumProfessions = new LinkedList<playerProfessionNumElements>();
    private List<playerProfessionNumElements> playerCounters = new LinkedList<playerProfessionNumElements>();

    public void createMiner(EntityPlayer playerIn){
        Miner miner = new Miner();
        playerProfessions.add(new playerProfessionsElemets(miner, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void createFarmer(EntityPlayer playerIn){
        Farmer farmer = new Farmer();
        playerProfessions.add(new playerProfessionsElemets(farmer, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void createBuilder(EntityPlayer playerIn){
        Builder builder = new Builder();
        playerProfessions.add(new playerProfessionsElemets(builder, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void createBlacksmith(EntityPlayer playerIn){
        Blacksmith blacksmith = new Blacksmith();
        playerProfessions.add(new playerProfessionsElemets(blacksmith, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void createSoldier(EntityPlayer playerIn){
        Soldier soldier = new Soldier();
        playerProfessions.add(new playerProfessionsElemets(soldier, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void createTrader(EntityPlayer playerIn){
        Trader trader = new Trader();
        playerProfessions.add(new playerProfessionsElemets(trader, playerIn.getName()));
        this.onPlayerSave(playerIn);
    }

    public void onPlayerUpdate(World worldIn, EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return;
        }
        for (int i = 0; i < playerProfessions.size(); i++){
            playerProfessionsElemets Element = playerProfessions.get(i);
            if(Element.playerName.equals( playerIn.getName())){
                if (!worldIn.isRemote && playerIn instanceof EntityPlayerMP) {
                    Element.profession.update(worldIn, (EntityPlayerMP) playerIn);
                }
            }
        }

    }

    public void onPlayerLoad(EntityPlayer playerIn){

        IPlayerProfessionCapability professionCapability = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);
        int profession = professionCapability.getProfession();
        int level = professionCapability.getLevel();
        int oldNum = professionCapability.getLastNum();

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

    public void onPlayerSave(EntityPlayer playerIn){

        IPlayerProfessionCapability professionCapability = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);

        professionCapability.setProfession(EnumProfessions.PROFESSION_NONE.getID());
        professionCapability.setLevel(EnumCareerLevel.LEVEL_NOVICE.getID());
        professionCapability.setLastNum(0);

        if (playerProfessions.isEmpty()){
            return;
        }
        for (int i = 0; i < playerProfessions.size(); i++){
            if (playerProfessions.get(i).playerName.equals( playerIn.getName())){
                professionCapability.setProfession(playerProfessions.get(i).profession.ID);
                professionCapability.setLevel(playerProfessions.get(i).profession.Level);
                professionCapability.setLastNum(playerProfessions.get(i).profession.OldNum);
            }
        }

    }

    public boolean hasPlayerProfession(int profession, EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return false;
        }
        for (int i = 0; i < playerProfessions.size(); i++){
            if (playerProfessions.get(i).playerName.equals( playerIn.getName())){
                if (playerProfessions.get(i).profession.ID == profession){
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
        for (int i = 0; i < playerProfessions.size(); i++){
            if (playerProfessions.get(i).playerName.equals( playerIn.getName())){
                return true;
            }
        }
        return false;

    }

    public int getPlayerProfession(EntityPlayer playerIn){

        if (playerProfessions.isEmpty()){
            return EnumProfessions.PROFESSION_NONE.getID();
        }
        for (int i = 0; i < playerProfessions.size(); i++){
            if (playerProfessions.get(i).playerName.equals( playerIn.getName())){
                return playerProfessions.get(i).profession.ID;
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
                    for (playerProfessionsElemets elemet2 : playerProfessions){
                        if (elemet2.playerName.equals(player.getName())){
                            playerProfessions.remove(elemet2);
                            this.onPlayerSave(player);
                            break;
                        }
                    }
                }
                if (level >= 30 && level < 100) {
                    if (!hasPlayerAnyProfession(player)) {
                        element.num = 1;
                        //createMiner(event.getEntityPlayer());
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
