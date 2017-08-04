package com.xpmodder.xpadditions.utility;

public class MathHelper {

    public boolean isPosOnSphere(int x, int y, int z, int radius, int Mx, int My, int Mz){

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution == ((int)Math.pow(radius, 2))){

            return true;

        }
        else {

            return false;

        }

    }

    public boolean isPosInSphere(int x, int y, int z, int radius, int Mx, int My, int Mz){

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((y - My), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2))){

            return true;

        }
        else {

            return false;

        }

    }

    public boolean isPosOnHCircle(int x, int y, int z, int radius, int Mx, int My, int Mz){

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution == ((int)Math.pow(radius, 2))){

            return true;

        }
        else {

            return false;

        }

    }

    public boolean isPosInHCircle(int x, int y, int z, int radius, int Mx, int My, int Mz){

        if (y != My){

            return false;

        }

        int solution = ((int)Math.pow((x - Mx), 2) + (int)Math.pow((z - Mz), 2));

        if (solution < ((int)Math.pow(radius, 2))){

            return true;

        }
        else {

            return false;

        }

    }

}
