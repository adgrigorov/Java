package com.company;

import java.util.*;

public class ForceBook {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, List<String>> users = new HashMap<>();

        while (!input.equals("Lumpawaroo")) {
            //first input type: {forceSide} | {forceUser}
            if (input.contains("|")) {
                String[] tokens = input.split("\\s+\\|\\s+");
                String forceSide = tokens[0];
                String forceUser = tokens[1];

                users.putIfAbsent(forceSide, new ArrayList<>());
                boolean isPresent = false;
                for (Map.Entry<String, List<String>> entry : users.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        isPresent = true;
                        break;
                    }
                }
                //adding user to a force side if the user doesn't exist
                if (!users.get(forceSide).contains(forceUser) && !isPresent) {
                    users.get(forceSide).add(forceUser);
                }
            //second input type: {forceUser} -> {forceSide}
            } else if (input.contains("->")) {
                String[] tokens = input.split("\\s+->\\s+");
                String forceUser = tokens[0];
                String forceSide = tokens[1];
                boolean isPresent = false;
                //checking if {forceUser} belongs to a {forceSide}
                //in order for the side to be changed, the user is first removed and then added again
                for (Map.Entry<String, List<String>> entry : users.entrySet()) {
                    if (entry.getValue().contains(forceUser)) {
                        entry.getValue().remove(forceUser);
                        users.putIfAbsent(forceSide, new ArrayList<>()); //if side doesn't exist, it creates it and adds the new user
                        users.get(forceSide).add(forceUser);
                        isPresent = true;
                        break;
                    }
                }
                //adding user to a side if he doesn't exist
                if (!isPresent) {
                    users.putIfAbsent(forceSide, new ArrayList<>()); //if not found, creates a new list for the side to add the new user
                    users.get(forceSide).add(forceUser);
                }

                System.out.printf("%s joins the %s side!%n", forceUser, forceSide);

            }

            input = sc.nextLine();
        }
        //side sort
        users.entrySet()
                .stream()
                .sorted((first, second) -> {
                    int result = second.getValue().size() - first.getValue().size(); //.size() isn't Integer, it's primitive int
                    //if both list sizes are equally sized, result is 0, if first > second: result > 0, if second > first: result < 0
                    if (result == 0) {
                        result = first.getKey().compareTo(second.getKey());
                    } return result;
                }).forEach(entry -> {
                    if (entry.getValue().size() != 0) {
                        System.out.printf("Side: %s, Members: %d%n", entry.getKey(), entry.getValue().size());
                    }//user sort
                    entry.getValue()
                            .stream()
                            .sorted((first, second) -> first.compareTo(second))
                            .forEach(user -> System.out.printf("! %s%n", user));
                });
    }
}
