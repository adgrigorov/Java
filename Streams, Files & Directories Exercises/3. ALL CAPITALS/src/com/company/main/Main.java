package com.company.main;
import java.io.*;

public class Main {

    //changes every symbol in a file to upper-case
    public static void main(String[] args) {

        String dir = System.getProperty("user.dir"); //project root dir
        String inputDir = dir + "\\input.txt";
        String outputDir = dir + "\\All Capitals.txt";

        //file to modify
        File inputFile = new File(inputDir);

        //file with result
        File outputFile = new File(outputDir);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        PrintWriter writer = new PrintWriter(outputFile)) {

            String line = reader.readLine(); //current line
            while (line != null) {
                String upperCaseLine = line.toUpperCase();
                writer.write(upperCaseLine);
                writer.println();
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
