package com.example;
import com.example.Pen;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class testPenIsWork {

    @Test
    public void isWorkEmptyPen() {
        Pen pen = new Pen(0);
        Assert.assertFalse(pen.isWork());
    }

    @Test
    public void isWorkNoEmptyPen() {
        Random random = new Random();
        int ContainerValue = random.nextInt();
        ContainerValue++;
        Pen pen;
        pen = new Pen(ContainerValue);
        Assert.assertTrue(pen.isWork());
    }

}
