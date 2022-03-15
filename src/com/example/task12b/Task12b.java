package com.example.task12b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task12b {
    public static void main(String[] args) {
        List<String> array1 = new ArrayList<>(Arrays.asList("Alex", "Dima", "Kate", "Galina", "Ivan"));
        List<String> array2 = new ArrayList<>(Arrays.asList("Dima", "Ivan", "Kate"));
        List<String> resultArray = new ArrayList<>(array1);
        resultArray.removeAll(array2);
        System.out.println(resultArray);
    }
}

