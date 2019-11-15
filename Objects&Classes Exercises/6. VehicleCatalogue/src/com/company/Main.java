package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Vehicle> vehicles = new ArrayList<>();

        String input = sc.nextLine();
        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String type = tokens[0];
            String model = tokens[1];
            String color = tokens[2];
            int horsepower = Integer.parseInt(tokens[3]);

            Vehicle vehicle = new Vehicle(type, model, color, horsepower);
            vehicles.add(vehicle);

            input = sc.nextLine();
        }

        String modelToPrint = "";
        while(!(modelToPrint = sc.nextLine()).equals("Close the Catalogue")) {
            String finalModel = modelToPrint;
            vehicles
                    .stream()
                    .filter(vehicle -> vehicle.getModel().equals(finalModel))
                    .forEach(System.out::println);
        }

        System.out.println(String.format("Cars have average horsepower of: %.2f",
                average(vehicles.stream()
                .filter(vehicle -> vehicle.getType().equals("car"))
                .collect(Collectors.toList()))));

        System.out.println(String.format("Trucks have average horsepower of: %.2f",
                average(vehicles.stream()
                        .filter(vehicle -> vehicle.getType().equals("truck"))
                        .collect(Collectors.toList()))));
    }

    private static double average(List<Vehicle> vehicles) {
        if (vehicles.size() == 0) {
            return 0.0;
        }

        double sum = 0;
        for (Vehicle vehicle : vehicles) {
            sum += vehicle.getHorsepower();
        }

        return sum / vehicles.size();
    }
}