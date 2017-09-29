package com.xpmodder.xpadditions.utility;

import net.minecraft.entity.player.EntityPlayer;

public class XPHelper {

    public void setPlayerXP(EntityPlayer player, int level){

        if (player.experienceLevel == level){

        }
        else if (player.experienceLevel > level){

            int temp = player.experienceLevel - level;
            temp *= -1;
            player.addExperienceLevel(temp);

        }
        else if (player.experienceLevel < level){

            int temp = level - player.experienceLevel;
            player.addExperienceLevel(temp);

        }

    }

    public void risePlayerXP(EntityPlayer player, int maxLevel){

        if (player.experienceLevel < maxLevel){

            int temp = maxLevel - player.experienceLevel;
            player.addExperienceLevel(temp);

        }

    }

    public void lowerPlayerXP(EntityPlayer player, int minLevel){

        if (player.experienceLevel > minLevel){

            int temp = player.experienceLevel - minLevel;
            temp *= -1;
            player.addExperienceLevel(temp);

        }

    }

    public int getXPforLevelDiff(int lowLevel, int highLevel){

        double lowXP = 0;
        double highXP = 0;

        if (lowLevel <= 16){

            lowXP = Math.pow(lowLevel, 2) + 6 * lowLevel;

        }
        else if (16 < lowLevel && lowLevel <= 31){

            lowXP = 2.5 * Math.pow(lowLevel, 2) - 40.5 * lowLevel + 360;

        }
        else if ( 31 < lowLevel){

            lowXP = 4.5 * Math.pow(lowLevel, 2) - 162.5 * lowLevel + 2220;

        }

        if (highLevel <= 16){

            highXP = Math.pow(highLevel, 2) + 6 * highLevel;

        }
        else if (16 < highLevel && highLevel <= 31){

            highXP = 2.5 * Math.pow(highLevel, 2) - 40.5 * highLevel + 360;

        }
        else if (31 < highLevel){

            highXP = 4.5 * Math.pow(highLevel, 2) - 162.5 * highLevel + 2220;

        }

        return (int) Math.round(highXP - lowXP);

    }

    public int getLevelfromXP(int xp){

        return getLevelfromXP(xp, 0);

    }

    public int getLevelfromXP(int xp, int startLevel){

        double temp = 0;
        int level = 0;

        if (xp <= 393){

            temp = Math.sqrt(xp + 9) - 3;

            if (temp < 0){

                temp *= -1;

            }

        }
        else if (393 < xp && xp <= 1627){

            temp = 0.632455532034 * (Math.sqrt(xp - 195.975) - 12.8072245237);

        }
        else if (1627 < xp){

            temp = 0.471404520791 * (Math.sqrt(xp - 752.986111111) - 38.3016173143);

        }

        level = startLevel + (int)Math.floor(temp);

        return level;

    }

    public int getLevelFromMB(int mB){

        return mB/1000;

    }

    public int getMBfromLevel(int level){

        return level*1000;

    }

    public int getMBfromXP(int xp){

        return (getLevelfromXP(xp) * 1000);

    }

    public int getXPfromMB(int mB){

        return getXPforLevelDiff(0, mB/1000);

    }

    public int getLevelforBlocks(int blocks){

        return blocks * 9;

    }

    public int getLevelforOrbItems(int items){

        return items;

    }

    public int getBlocksfromLevels(int levels){

        return levels/9;

    }

}
