package com.example.task12a;

public class Task12a {

    public static void main(String[] args) {
        String[] array1 = {"Alex", "Dima", "Kate", "Galina", "Ivan"};
        String[] array2 = {"Dima", "Ivan", "Kate"};
        boolean[] resultArrayBool = new boolean[array1.length];

        int resultArrayCount = 0;
        for (int i = 0; i < array1.length; i++) {
            boolean check = true;
            for (String name2 : array2) {
                if (array1[i].equals(name2)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                resultArrayCount++;
                resultArrayBool[i] = true;
            }
        }
        String[] resultArray = new String[resultArrayCount];
        for (int i = 0; i < resultArrayCount; i++) {
            for (int j = 0; j < array1.length; j++) {
                if (resultArrayBool[j]) {
                    resultArray[i] = array1[j];
                    resultArrayBool[j] = false;
                    break;
                }
            }
        }
        for (String s : resultArray) {
            System.out.print(s + "  ");
        }
    }
}
