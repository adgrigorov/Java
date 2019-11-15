package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MatrixOfPalindromes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] rowsAndColumns = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndColumns[0];
        int cols = rowsAndColumns[1];

        String[][] matrix = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            char firstAndLastLetter = (char)('a' + row);
            for (int col = 0; col < cols; col++) {
                char middleLetter = (char)(col + row + 'a');
                String word = String.valueOf(firstAndLastLetter) +
                        middleLetter +
                        firstAndLastLetter;
                matrix[row][col] = word;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.printf("%s ", matrix[row][col]);
            }
            System.out.println();
        }
    }
}
