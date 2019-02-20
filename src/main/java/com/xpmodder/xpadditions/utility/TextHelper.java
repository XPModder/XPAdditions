package com.xpmodder.xpadditions.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextHelper {

    private String Text = "This is a sample Text from the Book! This should print in multiple lines! And it should be longer than 100 characters now!       If you see this something went wrong! Please notify XPModde (the mod author), about this!";

    public String[] getTextInLines(int len) {
        String text = this.Text.substring(1);
        String temp[] = new String[this.getLineNum(len)];
        int currentLineLen = 0;
        int LineAt = 0;
        temp[0] = " ";
        String[] words = text.split(" ");


        for (int i = 0; i < words.length; i++) {

            int wordLen = words[i].length();

            if (words[i].contains("\n")) {
                temp[LineAt] += " ";
                for (int j = 0; j < words[i].length(); j++) {
                    if (words[i].charAt(j) == '\n') {
                        LineAt++;
                        currentLineLen = 0;
                        temp[LineAt] = " ";
                    } else {
                        temp[LineAt] += words[i].charAt(j);
                        currentLineLen++;
                    }

                }
                words[i].replaceAll("\n", " ");
            } else {
                if (currentLineLen + wordLen + 1 <= len) {
                    wordLen++;
                    temp[LineAt] += (" " + words[i]);
                    currentLineLen += wordLen;
                } else {
                    boolean firstIsSpace = words[i].charAt(0) == ' ';
                    LineAt++;
                    temp[LineAt] = ((firstIsSpace ? "" : words[i]));
                    currentLineLen = firstIsSpace ? 0 : wordLen;
                }
            }

        }

        return temp;
    }

    public int getLineNum(int len) {
        String s = this.Text;
        StringTokenizer st = new StringTokenizer(s, " ", true);
        String word;
        int currentLineLen = 0;
        int LineAt = 0;

        while (st.hasMoreTokens()) {
            int wordLen = (word = st.nextToken()).length();
            wordLen += 1;

            if (currentLineLen + wordLen <= len) {
                currentLineLen += wordLen;
            } else {
                boolean firstIsSpace = word.charAt(0) == ' ';
                LineAt++;
                currentLineLen = firstIsSpace ? 0 : wordLen;
            }
        }

        return (LineAt + 1);
    }

    public int setFileText(ResourceLocation resLoc) {

        try {

            InputStream iStream = Minecraft.getMinecraft().getResourceManager().getResource(resLoc).getInputStream();

            String text = new Scanner(iStream, "UTF-8").useDelimiter("\\A").next();

            this.Text = text;

            return 1;

        } catch (Exception ex) {
            return -1;
        }

    }

}

