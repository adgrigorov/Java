package com.company.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u';
    }

    //counting space as punctuation character
    //in order to skip spaces in counting
    private static boolean isPunctuation (char c) {
        return c == '!' || c == '.' || c == ',' || c == '?' || c == ' ';
    }

    public static void main(String[] args) {
	    //reads a list of words from a file and finds the count
        //of vowels, consonants and punctuation marks
        //a, e, i, o, u are vowels (lower case only)
        //all others are consonants
        //punctuation marks are ! , . ?
        //DOES NOT count spaces
        //result goes into a new file

        String dir = System.getProperty("user.dir");
        String inputDir = dir + "\\input.txt";
        String outputDir = dir + "\\output.txt";

        Path inputPath = Paths.get(inputDir);
        File outputFile = new File(outputDir);

        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             PrintWriter writer = new PrintWriter(outputFile)) {

            String line = reader.readLine();
            int vowels = 0;
            int consonants = 0;
            int punctuation = 0;
            while (line != null) {
                for (char c : line.toCharArray()) {
                    if (isVowel(c)) vowels++;
                    else if (isPunctuation(c)) punctuation++;
                    else consonants++;
                } line = reader.readLine();
            }
            String result = String.format("Vowels: %d%nConsonants: %d%nPunctuation %d%n",
                    vowels, consonants, punctuation);
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
