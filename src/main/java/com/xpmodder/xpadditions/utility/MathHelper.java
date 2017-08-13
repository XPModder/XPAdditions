package com.xpmodder.xpadditions.utility;

import net.minecraft.util.math.BlockPos;

public class MathHelper {

    public static boolean isPosOnSphere(int x, int y, int z, int radius, int Mx, int My, int Mz){

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution >= ((int)Math.pow(radius, 2) - (2 * radius)) && solution <= (int)Math.pow(radius, 2)){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosOnSphere(BlockPos pos, int radius, BlockPos center){

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int Mx = center.getX();
        int My = center.getY();
        int Mz = center.getZ();

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution >= ((int)Math.pow(radius, 2) - (2 * radius)) && solution <= (int)Math.pow(radius, 2)){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosInSphere(int x, int y, int z, int radius, int Mx, int My, int Mz){

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2) - (2 * radius))){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosInSphere(BlockPos pos, int radius, BlockPos center){

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int Mx = center.getX();
        int My = center.getY();
        int Mz = center.getZ();

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2) - (2 * radius))){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosOnHCircle(int x, int y, int z, int radius, int Mx, int My, int Mz){

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution >= ((int)Math.pow(radius, 2) - (2 * radius)) && solution <= (int)Math.pow(radius, 2)){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosOnHCircle(BlockPos pos, int radius, BlockPos center){

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int Mx = center.getX();
        int My = center.getY();
        int Mz = center.getZ();

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution >= ((int)Math.pow(radius, 2) - (2 * radius)) && solution <= (int)Math.pow(radius, 2)){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosInHCircle(int x, int y, int z, int radius, int Mx, int My, int Mz){

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2) - (2 * radius))){

            return true;

        }
        else {

            return false;

        }

    }

    public static boolean isPosInHCircle(BlockPos pos, int radius, BlockPos center){

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int Mx = center.getX();
        int My = center.getY();
        int Mz = center.getZ();

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2) - (2 * radius))){

            return true;

        }
        else {

            return false;

        }

    }

}
