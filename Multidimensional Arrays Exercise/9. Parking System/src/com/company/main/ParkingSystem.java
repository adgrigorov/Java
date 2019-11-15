package com.company.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ParkingSystem {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] rowsAndCols = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        boolean[][] parking = new boolean[rows][cols];

        String command = reader.readLine();
        while (!command.equals("stop")) {
            String[] tokens = command.split("\\s+");
            int entryRow = Integer.parseInt(tokens[0]);
            int targetRow = Integer.parseInt(tokens[1]);
            int targetCol = Integer.parseInt(tokens[2]);

            int distance = 0;
            boolean rowIsFull = false;
            boolean targetSpotIsTaken = parking[targetRow][targetCol];
            if (targetSpotIsTaken) {
                //търсим най-близкото свободно място до първоначалното желано, което е заето
                int counter = 1;
                while (true) {
                    int leftCol = targetCol - counter; //проверяваме за място ОТЛЯВО
                    int rightCol = targetCol + counter; //проверяваме за място ОТДЯСНО
                    if (leftCol < 1 && rightCol > parking[1].length) {
                        System.out.printf("Row %d full", targetRow);
                        rowIsFull = true;
                        break;
                    } //няма повече места в лявата колона, но може да има отдясно
                    else if (leftCol < 1 && rightCol < parking[1].length) {
                        boolean rightCellFull = parking[targetRow][rightCol];
                        if (!rightCellFull) {
                            distance = calculateDistance(entryRow, targetRow, rightCol);
                            parking[targetRow][rightCol] = true; //паркирал е
                            break;
                        }
                    } //няма повече места в дясната колона, но може да има отляво
                    else if (rightCol >= parking[1].length && leftCol >= 1) {
                        boolean leftCellFull = parking[targetRow][leftCol];
                        if (!leftCellFull) {
                            distance = calculateDistance(entryRow, targetRow, leftCol);
                            parking[targetRow][targetCol] = true;
                            break;
                        }
                    } //и двете колони са заети
                    else {
                        boolean leftCellFull = parking[targetRow][leftCol];
                        boolean rightCellFull = parking[targetRow][rightCol];
                        if (!leftCellFull) {
                            parking[targetRow][leftCol] = true; //паркирал е
                            distance = calculateDistance(entryRow, targetRow, leftCol);
                            break;
                        } else if (!rightCellFull) {
                            parking[targetRow][rightCol] = true; //паркирал е
                            distance = calculateDistance(entryRow, targetRow, rightCol);
                            break;
                        }
                    } counter++;
                }
            } else {
                distance = calculateDistance(entryRow, targetRow, targetCol);
                parking[targetRow][targetCol] = true;
            }

            if (!rowIsFull) {
                System.out.println(distance);
            }

            rowIsFull = false;

            command = reader.readLine();
        }
    }

    private static int calculateDistance(int entryRow, int targetRow, int targetCol) {
        return Math.abs(targetRow - entryRow) + targetCol + 1;
    }
}
