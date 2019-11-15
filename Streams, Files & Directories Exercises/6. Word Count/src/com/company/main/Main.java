package com.company.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    //compares words from text.txt file to words from words.txt file
    //counts each word that occurs in text.file and is in words.txt as well
    //saves result in new .txt file

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String wordsDir = dir + "\\words.txt";
        String textDir = dir + "\\text.txt";
        String resultDir = dir + "\\words with occurances.txt";

        Path wordsPath = Paths.get(wordsDir);
        Path textPath = Paths.get(textDir);
        Path outputPath = Paths.get(resultDir);

        try (BufferedReader reader = Files.newBufferedReader(wordsPath);
        BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            //extract just words as keys
            String[] keys = reader.readLine().split("[,. \\-]+");
            Map<String, Integer> wordsWithOccurencesCount = new HashMap<>();
            //key->word; value -> occurrence count

            //put every word from first file
            for (String key : keys) {
                wordsWithOccurencesCount.putIfAbsent(key, 0);
            }

            //check for every word in second file if it exists in first file
            Files.lines(textPath).forEach(line -> {
                Arrays.stream(line.split("[,. \\-]+"))
                        .forEach(word -> {
                            if (wordsWithOccurencesCount.containsKey(word)) {
                                int currentCount = wordsWithOccurencesCount.get(word);
                                wordsWithOccurencesCount.put(word, currentCount + 1);
                            }
                        });
            });

            //loop through words that occur in both files
            wordsWithOccurencesCount.entrySet()
                    .stream()
                    //sort by occurrence count descending
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                    .forEach(word -> {
                        String occuranceCount = String.format("%s -> %d%n",
                                word.getKey(), word.getValue());
                        //write into file with result
                        try {
                            writer.write(occuranceCount);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
