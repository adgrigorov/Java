package com.company.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    //sums the ASCII values of symbols on a line in the file

    public static void main(String[] args) {

        //build path
        Path filePath = Paths.get("D:\\ALEX\\Java Advanced\\" +
                "9. 10. Streams, Files & Directories + Exercise\\" +
                "10. Resources Exercise\\input.txt");

        //create reader to read selected file
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line = reader.readLine(); //current line
            while (line != null) {
                int sumOfAsciiSymbols = 0;
                for (int i = 0; i < line.length(); i++) {
                    sumOfAsciiSymbols += line.charAt(i);
                }
                System.out.println(sumOfAsciiSymbols);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
