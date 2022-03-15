package com.example.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String giveRandomWord(int count) {
        String generatedString = RandomStringUtils.randomAlphabetic(count);
        return generatedString;
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static double rndSizeLetter(double min, double max)
    {
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }
}
