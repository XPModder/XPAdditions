package com.xpmodder.xpadditions.professions;

import com.xpmodder.xpadditions.capabilities.IPlayerProfessionCapability;
import com.xpmodder.xpadditions.capabilities.PlayerProfessionProvider;
import com.xpmodder.xpadditions.utility.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

public class ProfessionsSystem {

    private List<playerProfessionsElements> playerProfessions = new LinkedList<playerProfessionsElements>();
    private List<playerProfessionNumElements> playerNumProfessions = new LinkedList<playerProfessionNumElements>();
    private List<playerProfessionNumElements> playerCounters = new LinkedList<playerProfessionNumElements>();

    public void createProfession(int id, int level, int xp, PlayerEntity playerIn){

        ModProfessions professions;

        if(id == EnumProfessions.PROFESSION_BLACKSMITH.getID()){
            professions = new Blacksmith(level, xp);
        }
        else if(id == EnumProfessions.PROFESSION_BUILDER.getID()){
            professions = new Builder(level, xp);
        }
        else if(id == EnumProfessions.PROFESSION_FARMER.getID()){
            professions = new Farmer(level, xp);
        }
        else if(id == EnumProfessions.PROFESSION_MINER.getID()){
            professions = new Miner(level, xp);
        }
        else if(id == EnumProfessions.PROFESSION_SOLDIER.getID()){
            professions = new Soldier(level, xp);
        }
        else if(id == EnumProfessions.PROFESSION_TRADER.getID()){
            professions = new Trader(level, xp);
        }
        else{
            professions = new Blacksmith();
        }

        playerProfessions.add(new playerProfessionsElements(professions, playerIn.getName().getString()));
        this.onPlayerSave(playerIn);

    }

    public void createProfession(int id, PlayerEntity playerIn){
        createProfession(id, 0, 0, playerIn);
    }

    public void onPlayerUpdate(World worldIn, PlayerEntity playerIn){

        if (playerProfessions.isEmpty()){
            return;
        }
        for (playerProfessionsElements element : playerProfessions){
            if(element.playerName.equals( playerIn.getName())){
                if (!worldIn.isRemote && playerIn instanceof ServerPlayerEntity) {
                    element.profession.update(worldIn, (ServerPlayerEntity) playerIn);
                }
            }
        }

    }

    public void onPlayerLoad(PlayerEntity playerIn){

        LazyOptional<IPlayerProfessionCapability> profCap = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);
        IPlayerProfessionCapability professionCapability = profCap.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
        int profession = professionCapability.getProfession();
        int level = professionCapability.getLevel();
        int xp = professionCapability.getLastNum();

        if (profession != 0){
            this.createProfession(profession, level, xp, playerIn);
        }

    }

    public void onPlayerSave(PlayerEntity playerIn){

        LazyOptional<IPlayerProfessionCapability> profCap = playerIn.getCapability(PlayerProfessionProvider.PROFESSION_CAP, null);
        IPlayerProfessionCapability professionCapability = profCap.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));

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
                professionCapability.setLastNum(element.profession.xp);
            }
        }

    }

    public boolean hasPlayerProfession(int profession, PlayerEntity playerIn){

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

    public boolean hasPlayerAnyProfession(PlayerEntity playerIn){

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

    public int getPlayerProfession(PlayerEntity playerIn){

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

    public String getPlayerProfessionName(PlayerEntity playerIn){

        int id = getPlayerProfession(playerIn);
        for (EnumProfessions profession : EnumProfessions.values()) {
            if (profession.getID() == id){
                return profession.getName();
            }
        }
        return EnumProfessions.PROFESSION_NONE.getName();

    }

    public int getPlayerProfessionNumber(PlayerEntity playerIn){

        int number = 0;
        for (playerProfessionsElements element : playerProfessions){
            if(element.playerName.equals(playerIn.getName())){
                number++;
            }
        }
        return number;

    }

    public int getPlayerAllowedNumber(PlayerEntity playerIn){

        for (playerProfessionNumElements element : playerNumProfessions){
            if(element.playerName.equals(playerIn.getName())){
                return element.num;
            }
        }
        return 0;

    }

    public int getPlayerProfessionXP(PlayerEntity playerIn){

        if (playerProfessions.isEmpty()){
            return 0;
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                return element.profession.xp;
            }
        }

        return 0;

    }

    public String getPlayerProfessionLevel(PlayerEntity playerIn){

        if (playerProfessions.isEmpty()){
            return "";
        }
        for (playerProfessionsElements element : playerProfessions){
            if (element.playerName.equals( playerIn.getName())){
                for (EnumCareerLevel element2 : EnumCareerLevel.values()){
                    if(element.profession.Level == element2.getID()){
                        return element2.getName();
                    }
                }
            }
        }

        return "";

    }

    @SubscribeEvent
    public void onPlayerGetXP(PlayerXpEvent.PickupXp event){

        int level = event.getPlayer().experienceLevel;
        PlayerEntity player = event.getPlayer();
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
                        player.sendMessage(new StringTextComponent("You unlocked your first profession!"));
                        player.sendMessage(new StringTextComponent("Select your profession in the XPAdditions Guide!"));
                    }
                }
            }
        }

        if (!found){
            playerNumProfessions.add(new playerProfessionNumElements(0, player.getName().getString()));
        }

    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){

        onPlayerLoad(event.getPlayer());

    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event){

        onPlayerSave(event.getPlayer());

    }

    @SubscribeEvent
    public void onPlayerUpdateEvent(TickEvent.PlayerTickEvent event){

        PlayerEntity player = event.player;
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
            playerCounters.add(new playerProfessionNumElements(0, player.getName().getString()));
        }

    }

}
