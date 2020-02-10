package com.xpmodder.xpadditions.utility;

public class playerProfessionNumElements {

    public int num;
    public String playerName;

    public playerProfessionNumElements(int num, String playerName){
        this.num = num;
        this.playerName = playerName;
    }

    public int getNumProfessions(){
        return this.num;
    }

    public String getPlayerName(){
        return this.playerName;
    }

}
