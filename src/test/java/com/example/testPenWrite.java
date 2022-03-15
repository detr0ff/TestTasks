package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.example.Utils.RandomUtils.*;


public class testPenWrite {

    private final int MaxCountWord = 10;
    private final int maxContainerValue = 1000;
    private final double minSizeLetter = 0.5d;
    private final double maxSizeLetter = 10.0d;

    @Test
    public void WriteEmptyPen(){
        Pen pen = new Pen(0);
        int count = rnd(0, MaxCountWord);
        String word;
        word = giveRandomWord(count);
        Assert.assertEquals("", pen.write(word));
    }

    @Test
    public void WriteContainerValueEnough(){
        List data = GenerateDataContainerValueEnough();
        int ContainerValue = (int) data.get(0);
        double sizeLetter = (double) data.get(1);
        String word = (String) data.get(2);
        Pen pen = new Pen(ContainerValue, sizeLetter);
        String writtenWord = pen.write(word);
        Assert.assertEquals(word, writtenWord);
    }

    @Test
    public void WriteContainerValueNotEnough(){
        List data = GenerateDataContainerValueNotEnough();
        data.get(0);
        int ContainerValue = (int) data.get(0);
        double sizeLetter = (double) data.get(1);
        String word = (String) data.get(2);
        int NumberOfWrittenChar = (int) (ContainerValue/sizeLetter);
        char[] WrittenChar=new char[NumberOfWrittenChar];
        word.getChars(0, WrittenChar.length, WrittenChar, 0);
        String expectedWord = new String(WrittenChar);
        Pen pen = new Pen(ContainerValue, sizeLetter);
        String writtenWord = pen.write(word);
        Assert.assertEquals(expectedWord, writtenWord);
    }

    @Test
    public void CheckContainerValueAfterWriteCVEnough() throws NoSuchFieldException, IllegalAccessException {
        List data = GenerateDataContainerValueEnough();
        int ContainerValue = (int) data.get(0);
        double sizeLetter = (double) data.get(1);
        String word = (String) data.get(2);
        Pen pen = new Pen(ContainerValue, sizeLetter);
        Field field = pen.getClass().getDeclaredField("inkContainerValue");
        field.setAccessible(true);
        int actualValue = (int) field.get(pen);
        ContainerValue = (int) (ContainerValue - sizeLetter*word.length());
        Assert.assertEquals(ContainerValue, actualValue);
    }

    @Test
    public void CheckContainerValueAfterWriteCVnotEnough() throws NoSuchFieldException, IllegalAccessException {
        List data = GenerateDataContainerValueNotEnough();
        int ContainerValue = (int) data.get(0);
        double sizeLetter = (double) data.get(1);
        String word = (String) data.get(2);
        Pen pen = new Pen(ContainerValue, sizeLetter);
        pen.write(word);
        Field field = pen.getClass().getDeclaredField("inkContainerValue");
        field.setAccessible(true);
        int actualValue = (int) field.get(pen);
        int NumberOfWrittenChar = (int) (ContainerValue/sizeLetter);
        ContainerValue = (int) (ContainerValue - sizeLetter*NumberOfWrittenChar);
        Assert.assertEquals(ContainerValue, actualValue);
    }

    public List GenerateDataContainerValueEnough(){
        String word = GenerateWord();
        boolean check = false;
        List data = new ArrayList();
        int ContainerValue = 0;
        double sizeLetter = 0;
        while (!check){
            ContainerValue =  rnd(1, maxContainerValue);
            sizeLetter = rndSizeLetter(minSizeLetter, maxSizeLetter);
            if (ContainerValue>=sizeLetter*word.length()&& ContainerValue!=0 && sizeLetter!=0){
                check = true;
            }
        }
        data.add(ContainerValue);
        data.add(sizeLetter);
        data.add(word);
        return data;
    }

    public List GenerateDataContainerValueNotEnough(){
        String word = GenerateWord();
        boolean check = false;
        List data = new ArrayList();
        int ContainerValue = 0;
        double sizeLetter = 0;
        while (!check){
            ContainerValue =  rnd(1, maxContainerValue);
            sizeLetter = rndSizeLetter(minSizeLetter, maxSizeLetter);
            if (ContainerValue<sizeLetter*word.length()&& ContainerValue!=0 && sizeLetter!=0){
                check = true;
            }
        }
        data.add(ContainerValue);
        data.add(sizeLetter);
        data.add(word);
        return data;
    }

    public String GenerateWord(){
        int count = rnd(0, MaxCountWord);
        String word;
        word = giveRandomWord(count);
        return word;
    }
}
