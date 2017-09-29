package com.xpmodder.xpadditions.xpnetwork;

public class xpStorage {

    public int freeID = 0;
    private int totalXP[];
    private int ID;

    public void init(int id){

        totalXP[id] = 0;
        ID = id;

    }

    public int getID() {

        return ID;

    }

    public int getTotalXP(int id) {

        return totalXP[id];

    }

    public void setTotalXP(int totalXP, int id) {

        this.totalXP[id] = totalXP;

    }

    public void addXP(int amount, int id){

        totalXP[id] += amount;

    }

    public void removeXP(int amount, int id){

        totalXP[id] -= amount;

    }
}
