package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Judge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();


        LinkedHashMap<String, Map<String, Integer>> contests = new LinkedHashMap<>();
        //key - contestName; value - userContestScore

        Map<String, Integer> individualStats = new LinkedHashMap<>();
        //key - username, value - totalScoreForAllContests

        while(!input.equals("no more time")) {
            String[] tokens = input.split(" -> ");
            String username = tokens[0];
            String contest = tokens[1];
            int points = Integer.parseInt(tokens[2]);

            if(!individualStats.containsKey(username)) {
                individualStats.put(username, 0);
            }

            if (!contests.containsKey(contest)) {
                contests.put(contest, new LinkedHashMap<>());
                contests.get(contest).put(username, points);
                int modifiedScore = individualStats.get(username);
                modifiedScore += points;
                individualStats.put(username, modifiedScore);

            } else {
                if (contests.get(contest).containsKey(username)) {
                    int currentPoints = contests.get(contest).get(username);
                    if (points > currentPoints) {
                        contests.get(contest).put(username, points);
                        int modifiedScore = individualStats.get(username);
                        modifiedScore -= currentPoints;
                        modifiedScore += points;
                        individualStats.put(username, modifiedScore);
                    }
                } else {
                    contests.get(contest).put(username, points);
                    int modifiedScore = individualStats.get(username);
                    modifiedScore += points;
                    individualStats.put(username, modifiedScore);
                }
            }

            input = sc.nextLine();
        }

        //ето го този AtomicInteger клас, който може да се итерира вътре в тялото на цикъла
        //примитивните типове ги храни, че не са final, каквото и да означава това за цикъла вътре
        AtomicInteger userCount = new AtomicInteger(0);

        contests.entrySet()
                .stream()
                .forEach(contest -> {
                    System.out.printf("%s: %d participants%n", contest.getKey(), contest.getValue().size());

                    contest.getValue()
                            .entrySet()
                            .stream()
                            .sorted((f, s) -> {
                                int result = s.getValue().compareTo(f.getValue());
                                if (result == 0) {
                                    result = f.getKey().compareTo(s.getKey());
                                } return result;
                            }).forEach(user -> {
                                userCount.getAndIncrement();
                        System.out.printf("%d. %s <::> %d%n", userCount.get(), user.getKey(), user.getValue());
                        //за да не продължава итерацията и след като мине големината на нестнатия Map, тук го ресетвам
                        //и започва да итерира наново
                        if (userCount.get() > contest.getValue().size() - 1) {
                            userCount.set(0);
                        }
                    });
                });

        userCount.set(0);
        System.out.println("Individual standings:");
        individualStats.entrySet()
                .stream()
                .sorted((f, s) -> s.getValue().compareTo(f.getValue()))
                .forEach(user ->  {
                    userCount.getAndIncrement();
                    System.out.printf("%d. %s -> %d%n", userCount.get(), user.getKey(), user.getValue());
                });
    }
}
