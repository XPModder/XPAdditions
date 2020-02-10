package com.xpmodder.xpadditions.capabilities;

import java.util.concurrent.Callable;

public class PlayerProfessionCallable implements Callable<IPlayerProfessionCapability> {


    @Override
    public IPlayerProfessionCapability call() throws Exception {

        return new IPlayerProfessionCapability() {

            private int Profession;
            private int Level;
            private int LastNum;

            @Override
            public void setProfession(int Profession) {
                this.Profession = Profession;
            }

            @Override
            public int getProfession() {
                return this.Profession;
            }

            @Override
            public void setLevel(int Level) {
                this.Level = Level;
            }

            @Override
            public int getLevel() {
                return this.Level;
            }

            @Override
            public void setLastNum(int LastNum) {
                this.LastNum = LastNum;
            }

            @Override
            public int getLastNum() {
                return this.LastNum;
            }

        };

    }

}
