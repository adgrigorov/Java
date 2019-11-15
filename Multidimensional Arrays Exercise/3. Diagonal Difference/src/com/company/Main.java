package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            int[] currentRow = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 0; col < size; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        int mainDiagonalSum = 0;
        for (int i = 0; i < size; i++) {
            mainDiagonalSum += matrix[i][i];
        }
        //System.out.println(mainDiagonalSum);

        int reverseDiagonalSum = 0;
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[row].length) {
            reverseDiagonalSum += matrix[row--][col++];
        }
        //System.out.println(reverseDiagonalSum);

        System.out.println(Math.abs(mainDiagonalSum - reverseDiagonalSum));

       /* for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }
            System.out.println();
        }*/
    }
}
