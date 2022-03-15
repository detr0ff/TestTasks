package Utilities;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String giveRandomWord(int count) {
        String generatedString = RandomStringUtils.randomAlphabetic(count);
        return generatedString;
    }

    public static char giveRandomChar(String word){
        int index = rndInt(0, word.length()-1);
        return word.charAt(index);
    }

    public static int rndInt(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static double rndDouble(double min, double max)
    {
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }
}
