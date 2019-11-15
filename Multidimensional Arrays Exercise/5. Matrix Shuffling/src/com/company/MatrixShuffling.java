package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MatrixShuffling {

    public static void main(String[] args) throws IOException {
	    //чете String матрица от конзолата
        //изпълнява различни команди:
        //swap row1 row2 col1 col2, където row1, ro2, col1, col2 са координати в матрицата
        //заменя стойностите matrix[row1][col1] и matrix[row2][col2]
        //принтира матрицата на всяка стъпка

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] rowsAndCols = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] currentRow = reader.readLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        String command = reader.readLine();
        while (!command.equals("END")) {
            String[] tokens = command.split("\\s+");
            //проверка за валидност на команда:
            //дали се състои от 5 отделни части, координатите дали са в рамките на матрицата и дали съдържа "swap"
            if (tokens.length != 5
                || !tokens[0].equals("swap")
                || !isInBounds(matrix, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]))) {
                System.out.println("Invalid input!");
            } else {
                int row1 = Integer.parseInt(tokens[1]);
                int col1 = Integer.parseInt(tokens[2]);
                int row2 = Integer.parseInt(tokens[3]);
                int col2 = Integer.parseInt(tokens[4]);

                String temp = matrix[row1][col1];
                matrix[row1][col1] = matrix[row2][col2]; //current element = elementToSwap
                matrix[row2][col2] = temp; //elementToSwap = temp

                printMatrix(matrix);
            }
            command = reader.readLine();
        }
    }

    private static boolean isInBounds(String[][] matrix, int row1, int col1, int row2, int col2) {
        return row1 >= 0 && row1 < matrix.length
                && col1 >= 0 && col1 < matrix[row1].length
                && row2 >= 0 && row2 < matrix.length
                && col2 >= 0 && col2 < matrix[row2].length;
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%s ", matrix[row][col]);
            }
            System.out.println();
        }
    }
}
