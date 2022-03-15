package com.example;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Random;


@RunWith(Parameterized.class)
public class testPenGetColour {

    private String colour;

    public testPenGetColour(String colour) {
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

    @Test
    public void getColour(){
        Random random = new Random();
        int ContainerValue = random.nextInt();
        double sizeOfWord = random.nextDouble();
        Pen pen = new Pen(ContainerValue, sizeOfWord, colour);
        Assert.assertEquals(colour, pen.getColor());
    }
}


