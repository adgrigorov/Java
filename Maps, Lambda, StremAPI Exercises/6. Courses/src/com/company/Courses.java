package com.company;

import java.util.*;

public class Courses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Map<String, List<String>> courses = new LinkedHashMap<>();

        while (!input.equals("end")) {
            String[] tokens = input.split(" : ");
            String course = tokens[0];
            String username = tokens[1];

            if (!courses.containsKey(course)) {
                courses.put(course, new ArrayList<>());
                courses.get(course).add(username);
            } else {
                if (!courses.get(course).contains(username)) {
                    courses.get(course).add(username);
                }
            }
            input = sc.nextLine();
        }

        courses.entrySet()
                .stream()
                .sorted((first, second) -> {
                    return second.getValue().size() - first.getValue().size();
                }).forEach(entry -> {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue().size());
            entry.getValue()
                    .stream()
                    .sorted((first, second) -> first.compareTo(second))
                    .forEach(user -> System.out.printf("-- %s%n", user));

        });
    }
}
