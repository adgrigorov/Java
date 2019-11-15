package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static boolean isNum (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfEngines = Integer.parseInt(sc.nextLine());

        LinkedList<Engine> engines = new LinkedList<>();
        LinkedList<Car> cars = new LinkedList<>();

        for (int i = 0; i < numberOfEngines; i++) {
            String[] engineInput = sc.nextLine().split("\\s+");
            String engineModel = engineInput[0];
            int enginePower = Integer.parseInt(engineInput[1]);
            Engine engine = null;

            if (engineInput.length == 4) {
                String engineDisplacement = engineInput[2];
                String engineEfficiency = engineInput[3];
                engine = new Engine(engineModel, enginePower, engineDisplacement, engineEfficiency);
            }
            else if (engineInput.length == 3) {
                String engineDisplacement = engineInput[2];
                if (isNum(engineDisplacement)) {
                    engine = new Engine(engineModel, enginePower, engineDisplacement, "n/a");
                }
                else {
                    String engineEfficiency = engineInput[2];
                    engine = new Engine(engineModel, enginePower, "n/a", engineEfficiency);
                }
            }
            else {
                engine = new Engine(engineModel, enginePower);
            }
            engines.add(engine);
        }

        int numberOfCars = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfCars; i++) {
            Car car = null;
            String[] carInput = sc.nextLine().split("\\s+");
            String carModel = carInput[0];
            String carEngine = carInput[1];
            Engine engine = null;
            for (Engine engine1 : engines) {
                if(engine1.getModel().equals(carEngine)) {
                    engine = engine1;
                    break;
                }
            }
            if (carInput.length == 4) {
                String carWeight = carInput[2];
                String carColor = carInput[3];
                car = new Car(carModel, engine, carWeight, carColor);
            }

            else if (carInput.length == 3) {
                String carWeight = carInput[2];
                if (isNum(carWeight)) {
                    car = new Car(carModel, engine, carWeight, "n/a");
                }
                else {
                    String carColor = carInput[2];
                    car = new Car(carModel, engine, "n/a", carColor);
                }
            }

            else {
                car = new Car(carModel, engine);
            }

            cars.add(car);
        }

        for (Car car : cars) {
            System.out.print(car.toString());
        }
    }
}
