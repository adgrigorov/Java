package com.company;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MOBAChallenger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, LinkedHashMap<String, Integer>> pool = new LinkedHashMap<>();
        //contains players with their information: positions, skills

        while(!input.equals("Season end")) {
            //adding players to the pool
            if (input.contains("->")) {
                String[] tokens = input.split(" -> ");
                String playerName = tokens[0];
                String position = tokens[1];
                int skill = Integer.parseInt(tokens[2]);

                //adds a player directly to the pool if he dosn't exist
                if (!pool.containsKey(playerName)) {
                    pool.put(playerName, new LinkedHashMap<>());
                    pool.get(playerName).put(position, skill);
                }

                //if a player already exists:
                else { //updates their skill to the current position
                    if (pool.get(playerName).containsKey(position)) {
                        if (pool.get(playerName).get(position) < skill) {
                            pool.get(playerName).put(position, skill);
                        }
                    }
                    //adds new position with corresponding skills
                    else {
                        pool.get(playerName).put(position, skill);
                    }
                }
            }

            //duels: possible removal of players from the pool
            if (input.contains("vs")) {
                String[] tokens = input.split(" vs ");
                String playerOne = tokens[0];
                String playerTwo = tokens[1];

                //checks if both players are part of the pool
                if (pool.containsKey(playerOne) && pool.containsKey(playerTwo)) {
                    //maps to compare both players' positions and corresponding skills
                    Map<String, Integer> playerOneStats = pool.get(playerOne);
                    Map<String, Integer> playerTwoStats = pool.get(playerTwo);
                    boolean firstIsLonger = playerOneStats.size() > playerTwoStats.size();

                    //always loops through the longer map
                    //if positions match, the player with higher skill wins; loser is removed from pool
                    if (firstIsLonger) {
                        for (Map.Entry<String, Integer> firstEntry : playerOneStats.entrySet()) {
                            if (playerTwoStats.containsKey(firstEntry.getKey())) {
                                int firstPlayerSkill = firstEntry.getValue();
                                int secondPlayerSkill = playerTwoStats.get(firstEntry.getKey());
                                if (firstPlayerSkill > secondPlayerSkill) {
                                    pool.remove(playerTwo);
                                } else if (secondPlayerSkill > firstPlayerSkill) {
                                    pool.remove(playerOne);
                                }
                            }
                        }
                    } else {
                        for (Map.Entry<String, Integer> secondEntry : playerTwoStats.entrySet()) {
                            if(playerOneStats.containsKey(secondEntry.getKey())) {
                                int secondPlayerSkill = secondEntry.getValue();
                                int firstPlayerSkill = playerOneStats.get(secondEntry.getKey());
                                if (secondPlayerSkill > firstPlayerSkill) {
                                    pool.remove(playerOne);
                                } else if (firstPlayerSkill > secondPlayerSkill) {
                                    pool.remove(playerTwo);
                                }
                            }
                        }
                    }
                }
            }

            input = sc.nextLine();
        }

        Map<String, Integer> playersTotalSkills = new HashMap<>();
        //holds each player's total skill (for every position combined)

        //adds every player's total skill to the map
        for (Map.Entry<String, LinkedHashMap<String, Integer>> player : pool.entrySet()) {
            int skill = player.getValue()
                    .values()
                    .stream()
                    .mapToInt(s -> s)
                    .sum();

            playersTotalSkills.put(player.getKey(), skill);
        }


        playersTotalSkills.entrySet()
                .stream()
                .sorted((a, b) -> { //sorts players by total skill(descending) and by name(alphabetically)
                            int result = b.getValue().compareTo(a.getValue());
                            if (result == 0) {
                                result = a.getKey().compareTo(b.getKey());
                            }
                            return result;
                        }).forEach(player -> {
                            System.out.printf("%s: %d skill%n", player.getKey(), player.getValue());

                            pool.get(player.getKey())
                                    .entrySet()
                                    .stream()
                                    .sorted((a, b) -> { //sorts each player's positions by skill(descending) and by
                                                        //position name(alphabetically)
                                        int result = b.getValue().compareTo(a.getValue());
                                        if (result == 0) {
                                            result = a.getKey().compareTo(b.getKey());
                                        } return result;
                                    }).forEach(p -> System.out.printf("- %s <::> %d%n", p.getKey(), p.getValue()));
        });
    }
}
