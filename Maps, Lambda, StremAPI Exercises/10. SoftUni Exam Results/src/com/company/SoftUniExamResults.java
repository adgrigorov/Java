package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniExamResults {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> userScores = new HashMap<>();
        Map<String, Integer> languagesSubmissions = new HashMap<>();
        String input = sc.nextLine();

        while (!input.equals("exam finished")) {
            String[] tokens = input.split("-");

            String username = tokens[0];
            String language = tokens[1];

            if (language.equals("banned")) {
                userScores.remove(username);
                input = sc.nextLine();
                continue; //skips the rest of the loop if a user is banned
            }

            int currentPoints = Integer.parseInt(tokens[2]);

            if (!userScores.containsKey(username)) {
                userScores.put(username, currentPoints);
            } else {
                int points = userScores.get(username);
                if (points < currentPoints) {
                    userScores.put(username, currentPoints);
                }
            }

            if (!languagesSubmissions.containsKey(language)) {
                languagesSubmissions.put(language, 1);
            } else {
                int currentSubmissions = languagesSubmissions.get(language);
                languagesSubmissions.put(language, currentSubmissions + 1);
            }

            input = sc.nextLine();
        }

        System.out.println("Results:");        
        userScores.entrySet()
                .stream()
                .sorted((first, second) -> {
                    int result = second.getValue().compareTo(first.getValue());
                    if (result == 0) {
                        result = first.getKey().compareTo(second.getKey());
                    } return result;
                }).forEach(entry -> System.out.printf("%s | %d%n", entry.getKey(), entry.getValue()));


        System.out.println("Submissions:");        
        languagesSubmissions.entrySet()
                .stream()
                .sorted((first, second) -> {
                    int result = second.getValue().compareTo(first.getValue());
                    if (result == 0) {
                        result = first.getKey().compareTo(second.getKey());
                    } return result;
                }).forEach(entry -> System.out.printf("%s - %d%n", entry.getKey(), entry.getValue()));
    }
}
