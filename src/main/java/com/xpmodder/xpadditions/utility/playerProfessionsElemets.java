package com.xpmodder.xpadditions.utility;

import com.xpmodder.xpadditions.professions.ModProfessions;

public class playerProfessionsElemets {

    public ModProfessions profession;
    public String playerName;

    public playerProfessionsElemets(ModProfessions profession, String playerName){
        this.profession = profession;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ModProfessions getProfession() {
        return profession;
    }

}
