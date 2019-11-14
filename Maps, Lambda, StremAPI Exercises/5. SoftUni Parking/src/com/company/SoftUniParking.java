package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfCommands = Integer.parseInt(sc.nextLine());
        String input = sc.nextLine();

        Map<String, String> parking = new LinkedHashMap<>();

        while (numberOfCommands-- > 0) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String username = tokens[1];
            if (command.equals("register")) {
                String licensePlateNumber = tokens[2];
                if (!parking.containsKey(username)) {
                    parking.put(username, licensePlateNumber);
                    System.out.printf("%s registered %s successfully.%n", username, licensePlateNumber);
                } else {
                    if (parking.get(username).equals(licensePlateNumber)) {
                        System.out.printf("ERROR: already registered plate number %s%n", licensePlateNumber);
                    }
                }
            } if (command.equals("unregister")) {
                if (!parking.containsKey(username)) {
                    System.out.printf("ERROR: user %s not found.%n", username);
                } else {
                    parking.remove(username);
                    System.out.printf("%s unregistered successfully.%n", username);
                }
            }

            input = sc.nextLine();
        }

        for (Map.Entry<String, String> entry : parking.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }
}
