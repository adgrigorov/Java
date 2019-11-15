package com.company.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");

        Path inputOne = Paths.get(dir + "\\inputOne.txt");
        Path inputTwo = Paths.get(dir + "\\inputTwo.txt");
        Path outputPath = Paths.get(dir + "\\merged files.txt");

        try (BufferedReader one = Files.newBufferedReader(inputOne);
        BufferedReader two = Files.newBufferedReader(inputTwo);
        BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line = one.readLine();
            while (line != null) {
                writer.write(line);
                writer.write("\n");
                line = one.readLine();
            }

            line = two.readLine();
            while (line != null) {
                writer.write(line);
                writer.write("\n");
                line = two.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
