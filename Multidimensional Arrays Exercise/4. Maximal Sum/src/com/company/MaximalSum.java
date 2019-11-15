package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaximalSum {

    //чете NxM матрица от конзолата
    //търси 3х3 подматрицата, чиято сума от елементи е най-голяма
    //печата сумата и самата подматрица

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] rowsAndCols = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = rowsAndCols[0];
        int cols = rowsAndCols[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] currentRow = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int submatrixRow = 0;
        int submatrixCol = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                int sum = getSum(matrix, row, col);
                if (sum > maxSum) {
                    maxSum = sum;
                    //взимаме индексите на текущата матрица, чиято сума е най-голяма
                    submatrixRow = row;
                    submatrixCol = col;
                }
            }
        }

        System.out.printf("Sum = %d%n", maxSum);

        submatrixRow--;
        submatrixCol--;
        for (int row = submatrixRow; row < submatrixRow + 3; row++) {
            for (int col = submatrixCol; col < submatrixCol + 3; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static int getSum(int[][] matrix, int row, int col) {
        int sum = 0;

        row--;
        col--;

        for (int r = row; r < row + 3; r++) {
            for (int c = col; c < col + 3; c++) {
                sum += matrix[r][c];
            }
        } return sum;
    }
}
