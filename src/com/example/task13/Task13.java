package com.example.task13;

import java.io.*;
import java.util.*;


public class Task13 {

    private static final int NumberOfTests = 10;

    public static void main(String[] args) throws IOException {

        String path = "D:\\App\\fsfsf.txt";
        selectСases(path, 2);

    }


    public static void selectСases(String path, int count) throws IOException {
        File sourceFile = new File(path);
        String FullFileName = sourceFile.getName();
        String fileName = getFileNameWithoutExt(FullFileName);
        String fileExc = getFileExt(FullFileName);

        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        LinkedList<String> source = new LinkedList<>();
        LinkedList<String> out = new LinkedList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            source.add(line);
        }
        String firstLine = source.getFirst();
        source.removeFirst();
        GetRandomElem(source, out, count);
        source.addFirst(firstLine);
        out.addFirst(firstLine);
        write(path, source);
        String outName = fileName + "_res" + fileExc;
        String outDir = sourceFile.getParent() + "\\" + outName;
        write(outDir, out);

    }

    public static void selectСases(String path) throws IOException {
        selectСases(path, NumberOfTests);
    }

    public static void GetRandomElem(List sourceArray, List outArray, int number) {
        Random objGenerator = new Random();
        for (int i = 0; i < number; i++) {
            int rand = objGenerator.nextInt(sourceArray.size());
            outArray.add(sourceArray.get(rand));
            sourceArray.remove(rand);
        }
    }

    public static String getFileNameWithoutExt(String FullFileName) {
        String fileName = null;
        int lastPeriodPos = FullFileName.lastIndexOf('.');
        if (lastPeriodPos > 0) {
            fileName = FullFileName.substring(0, lastPeriodPos);;
        }
        return fileName;
    }

    public static String getFileExt(String FullFileName) {
        String fileExc = null;
        int lastPeriodPos = FullFileName.lastIndexOf('.');
        if (lastPeriodPos > 0) {
            fileExc = FullFileName.substring(lastPeriodPos);
        }
        return fileExc;
    }

    public static void write(String path, List<String> str) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            for (String line : str) {
                writer.write(line);
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

