package com.company.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    //sums ASCII values of all symbols in a file

    public static void main(String[] args) {
        //build path
        Path filePath = Paths.get("D:\\ALEX\\Java Advanced\\" +
                "9. 10. Streams, Files & Directories + Exercise\\" +
                "10. Resources Exercise\\input.txt");

        int totalSum = 0;
        //create reader to read the selected file
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = reader.readLine(); //current line
            while (line != null) {
                int currentLineSum = 0;
                for (char c : line.toCharArray()) {
                    currentLineSum += c;
                } totalSum += currentLineSum;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(totalSum);
    }
}
