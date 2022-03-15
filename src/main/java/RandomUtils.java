import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String giveRandomWord(int count) {
        String generatedString = RandomStringUtils.randomAlphabetic(count);
        return generatedString;
    }
}
