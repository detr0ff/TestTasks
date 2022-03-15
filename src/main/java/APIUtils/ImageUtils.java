package APIUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ImageUtils {

    public static BufferedImage downloadImage(String url){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(url));
        }
        catch (IOException ignored) {
        }
        return image;
    }

    public static BufferedImage fromFile(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        }
        catch (IOException ignored) {
        }
        return image;
    }

    public static boolean imageEquals(BufferedImage image1, BufferedImage image2){
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        try {
            ImageIO.write(image1, "jpg", bos1);
            ImageIO.write(image2, "jpg", bos2);
            byte [] data1 = bos1.toByteArray();
            byte [] data2 = bos1.toByteArray();
            return Arrays.equals(data1, data2);
        } catch (IOException e) {
            throw new RuntimeException("ImageEquals Error");
        }
    }
}
