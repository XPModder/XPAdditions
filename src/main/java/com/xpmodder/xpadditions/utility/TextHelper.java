package com.xpmodder.xpadditions.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

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

        String out[] = new String[this.getLineNum(length)];
        String s = this.Text;
        String words[] = s.split(" \n");
        int line = 0;
        int counter = 0;
        out[0] = " ";

        for(int i = 0; i < words.length; i++){

            if((counter + words[i].length()) < length){
                counter += words[i].length();
                out[line] += " ";
                out[line] += words[i];
            }
            else{
                line++;
                counter = words[i].length();
                out[line] = words[i];
            }

        }

        return out;
    }

    public int getLineNum( int len ) {
        String s = this.Text;
        String words[] = s.split(" \n");
        int lines = 0;
        int currentLineLen = 0;

        for(int i = 0; i < words.length; i++){

            if(currentLineLen < (len + words[i].length())){
                currentLineLen += words[i].length();
            }
            else{
                lines++;
                currentLineLen = words[i].length();
            }

        }

        return lines;
    }


    public int getLines(int length){

        char in[] = this.Text.toCharArray();
        int line = 0;
        int counter = 0;

        for(int i = 0; i < in.length; i++){

            if(in[i] == '\n'){
                line += 2;
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
