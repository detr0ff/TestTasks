package Utilities;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.Path;

public class LoadUtils {

    private static final int delay = 3000;

    public static void setClipboardData(String string) {
        Path path = Path.of(string).toAbsolutePath();
        StringSelection stringSelection = new StringSelection(path.toString());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void LoadFile(String filePath) {
        setClipboardData(filePath);
        Robot robot;
        try{
            robot = new Robot();
            robot.delay(delay);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
