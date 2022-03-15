package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static com.example.Utils.RandomUtils.rnd;
import static com.example.Utils.RandomUtils.rndSizeLetter;


public class testPenCreating {
    private final int maxContainerValue = 10000;
    private final int maxSizeLetter = 100;

    @Test
    public void newPenNegativeContVal() throws NoSuchFieldException, IllegalAccessException {

        int ContainerValue = rnd(1, maxContainerValue);
        ContainerValue = ContainerValue*(-1);
        Pen pen = new Pen(ContainerValue);
        Field field = pen.getClass().getDeclaredField("inkContainerValue");
        field.setAccessible(true);
        int actualValue = (int) field.get(pen);
        Assert.assertEquals(0, actualValue);
    }

    @Test
    public void newPenNegativeSizeLetter() throws NoSuchFieldException, IllegalAccessException {
        int ContainerValue = rnd(1, maxContainerValue);
        double sizeLetterTest = rndSizeLetter(0, maxSizeLetter);
        sizeLetterTest = sizeLetterTest*(-1);
        Pen pen = new Pen(ContainerValue, sizeLetterTest);
        Field field = pen.getClass().getDeclaredField("sizeLetter");
        field.setAccessible(true);
        field.set(pen.getClass(), (String) "new value");
        double actualValue = (double) field.get(pen);
        double defaultSizeLetter = getDefaultSizeLetter();
        Assert.assertEquals(defaultSizeLetter, actualValue, 0);
    }

    public double getDefaultSizeLetter() throws NoSuchFieldException, IllegalAccessException {
        int ContainerValue = rnd(1, maxContainerValue);
        Pen pen = new Pen(ContainerValue);
        Field field = pen.getClass().getDeclaredField("sizeLetter");
        field.setAccessible(true);
        return (double) field.get(pen);
    }
}
