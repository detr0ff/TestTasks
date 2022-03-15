package com.example.task11;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.StrictMath.abs;


public class Task11 {

    private static long longTime = 10000L;


    public static void main(String[] args) throws IOException {
        Scanner directory = new Scanner(System.in);
        Scanner extension = new Scanner(System.in);
        Path dir = Path.of(directory.nextLine());
        String ext = extension.nextLine();

        List<File> lst = null;
        lst = GetAllFilesOfExtension(dir, ext);


        long lastTime = 0;
        File lastFile = null;
        for (File file : lst) {
            long time = Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toMillis();
            if (lastTime < time) {
                lastFile = file;
                lastTime = time;
            }
        }
        System.out.println(lastFile.getName());


        for (File file : lst) {
            long time = Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toMillis();
            if (abs(lastTime - time) <= longTime && lastTime - time != 0) {
                System.out.println(file.getName());
            }
        }
    }

    public static List<File> GetAllFilesOfExtension(Path dir, String ext) {
        List<File> lst = new ArrayList<>();
        try (DirectoryStream<Path> files = Files.newDirectoryStream(dir)) {
            for (Path file : files) {
                if (file.toFile().isFile() && file.toFile().getName().endsWith(ext)) {
                    lst.add(file.toFile());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }
}