package Utilities;

public class RandomRusChar {
    private static final String RUS = "абвгдеёжзиклмнопрстуфхцчшщъыьэюя";
    public static char getRandomRusChar(){
        int index = RandomUtils.rndInt(0, RUS.length()-1);
        return RUS.charAt(index);
    }
}
