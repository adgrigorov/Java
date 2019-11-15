package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfCars = Integer.parseInt(sc.nextLine());

        ArrayList<Car> cars = new ArrayList<>();

        for (int i = 0; i < numberOfCars; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String carModel = input[0];
            int engineSpeed = Integer.parseInt(input[1]);
            int enginePower = Integer.parseInt(input[2]);
            int cargoWeight = Integer.parseInt(input[3]);
            String cargoType = input[4];
            ArrayList<Tire> tires = new ArrayList<>();
            for (int inputElement = 5; inputElement < 12; inputElement+=2) {
                int nextInputElement = inputElement + 1;
                double tirePressure = Double.parseDouble(input[inputElement]);
                int tireAge = Integer.parseInt(input[nextInputElement]);
                Tire tire = new Tire(tirePressure, tireAge);
                tires.add(tire);
            }

            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Car car = new Car(carModel, engine, cargo, tires);

            cars.add(car);
        }

        String carType = sc.nextLine();
        List<String> model = new ArrayList<>();
        if (carType.equals("fragile")) {
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getCargo().getType().equals("fragile")) {
                    if (cars.get(i).getTires().get(i).getPressure() < 1) {
                        model.add(cars.get(i).getModel());
                    }
                }
            }
            model.forEach(System.out::println);
        }

        else if (carType.equals("flammable")) {
            for (int i = 0; i < cars.size(); i++) {
                if (cars.get(i).getCargo().getType().equals("flammable")) {
                    if (cars.get(i).getEngine().getPower() > 250) {
                        model.add(cars.get(i).getModel());
                    }
                }
            }
            model.forEach(System.out::println);
        }
    }
}