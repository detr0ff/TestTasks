package com.example;

import com.example.Pen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

@RunWith(Parameterized.class)
public class testPenDoSomethingElse {

    private String colour;
    private Pen pen;

    public testPenDoSomethingElse(String colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters(name = "{index}: colour: {0}")
    public static Iterable dataForTest() {
        Object[][] colours= new Object[][] {
                {"BLUE"},
                {"RED"},
                {"GREEN"},
                {"BLACK"}
        };
        return Arrays.asList(colours);
    }

    @Before
    public void CreatePen(){
        Random random = new Random();
        int ContainerValue = random.nextInt();
        double sizeOfWord = random.nextDouble();
        pen = new Pen(ContainerValue, sizeOfWord, colour);
    }



    @Test
    public void testDoSomethingElse() throws IOException {
        File file = new File("d:\\temp.txt"); //  создаем файл
        PrintStream ps = new PrintStream(file); // создаем поток вывода в файл
        PrintStream standardOut = System.out; // сохраняем стандартный поток вывода
        System.setOut(ps); // присваиваем файловый поток в качестве основного
        pen.doSomethingElse(); // вызываем метод
        Assert.assertEquals(Files.readAllLines(Paths.get(file.toURI())).get(0), colour); // проверяем что результат в файле равен ожидаемому
        System.setOut(standardOut); // возвращаем метод в исходное состояние
    }


}
