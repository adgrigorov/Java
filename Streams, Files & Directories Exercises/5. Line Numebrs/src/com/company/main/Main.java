package com.company.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
	    //reads text from file
        //inserts numbers in front each of its lines
        //result is written to another text file
        String dir = System.getProperty("user.dir");
        String inputDir = dir + "\\inputLineNumbers.txt";
        String outputDir = dir + "\\text with line numbers.txt";

        Path inputPath = Paths.get(inputDir);
        Path outputPath = Paths.get(outputDir);

        try (BufferedReader reader = Files.newBufferedReader(inputPath);
        BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String line = reader.readLine();
            int lineCounter = 1;
            while (line != null) {
                String newLine = String.format("%d. %s%n",
                        lineCounter, line);
                writer.write(newLine);
                lineCounter++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
