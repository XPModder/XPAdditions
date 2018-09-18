package com.xpmodder.xpadditions.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;
import java.util.Scanner;

public class TextHelper {

    private String Text = "This is a sample Text from the Book! This should print in multiple lines! And it should be longer than 100 characters now!       If you see this something went wrong!";

    public String getTextByLine(int line, int length)
    {

        int start = line * length;
        int end = start + length;

        if(end > this.Text.length()){
            end = this.Text.length();
        }

        return this.Text.substring(start, end);

    }

    public String[] getTextLines(int length){

        String out[] = new String[this.getLines(length)];
        char in[] = this.Text.toCharArray();
        int line = 0;
        int counter = 0;

        for(int i = 0; i < in.length; i++){

            if(in[i] == '\n'){
                out[line] = " ";
                line++;
                counter = 0;
            }
            else if(in[i] == '\r'){

            }
            else {

                if(counter == 0){
                    out[line] = "" + in[i];
                }
                else{
                    out[line] += in[i];
                }
                counter++;

                if (counter == length) {
                    line++;
                    counter = 0;
                }

            }

        }

        return out;
    }

    public int getLines(int length){

        char in[] = this.Text.toCharArray();
        int line = 0;
        int counter = 0;

        for(int i = 0; i < in.length; i++){

            if(in[i] == '\n'){
                line++;
                counter = 0;
            }
            else if(in[i] == '\r'){

            }
            else {

                counter++;
                if (counter == length) {
                    line++;
                    counter = 0;
                }

            }

        }

        return line;
    }

    public int setFileText(ResourceLocation resourceLocation){

        try{

            InputStream iStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();

            String text = new Scanner(iStream, "UTF-8").useDelimiter("\\A").next();

            this.Text = text;

            return 1;

        }
        catch (Exception ex){
            return -1;
        }

    }

}
