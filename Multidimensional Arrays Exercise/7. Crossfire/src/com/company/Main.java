package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    //дадени са размерите на матрица, която трябва да се запълни
    //с нарастващи числа, започващи от 1:
    //ред1: 1, 2, 3, ..., n
    //ред2: n+1, n+2, ..., n+n
    //ред3: 2*n+1, 2*n+2, ..., 2*n+n
    //подават се команди под формата на 3 числа, разделени по спейс
    //числата репрезентират РЕД, КОЛОНА и РАДИУС
    //трябва да се унищожат всички клетки, които отговарят на координатите
    //под формата на Х
    //унищожаването на клетка означава, че тя остава НЕСЪЩЕСТВУВАЩА
    //унищожаването под формата на Х означава, че този Х има център клетката,
    //имаща подадените координати

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] rowsAndCols = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        List<List<Integer>> matrix = new ArrayList<>();
        int index = 1;

        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(index++);
            }
        }

        String command = reader.readLine();
        while (!command.equals("Nuke it from orbit")) {
            String[] data = command.split("\\s+");
            int targetRow = Integer.parseInt(data[0]);
            int targetCol = Integer.parseInt(data[1]);
            int radius = Integer.parseInt(data[2]);

            for (int row = targetRow - radius; row <= targetRow + radius; row++) {
                if (isInRange(matrix, row, targetCol) && row != targetRow) {
                    matrix.get(row).remove(targetCol);
                }
            }

            for (int col = targetCol + radius; col >= targetCol - radius; col--) {
                if (isInRange(matrix, targetRow, col)) {
                    matrix.get(targetRow).remove(col);
                }
            }

            matrix.removeIf(List::isEmpty);
            command = reader.readLine();
        }

        for (List<Integer> row : matrix) {
            for (Integer col : row) {
                System.out.printf("%d ", col);
            }
            System.out.println();
        }
    }

    private static boolean isInRange(List<List<Integer>>matrix, int row, int col) {
        return  row >= 0 && row < matrix.size()
                && col >= 0 && col < matrix.get(row).size();
    }
}
