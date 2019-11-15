package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FillTheMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] info = reader.readLine().split(",\\s+");
        int size = Integer.parseInt(info[0]);
        char type = info[1].charAt(0);

        int[][] matrix = new int[size][size];

        if (type == 'A') {
            int element = 1;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    matrix[col][row] = element++;
                }
            }
        }

        if (type == 'B') {
            int element = 1;
            for (int col = 0; col < size; col++) {
                if (col % 2 == 0) {
                    for (int row = 0; row < size; row++) {
                        matrix[row][col] = element++;
                    }
                } else {
                    for (int row = size - 1; row >= 0; row--) {
                        matrix[row][col] = element++;
                    }
                }
            }
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.printf("%d ", matrix[row][col]);
            }
            System.out.println();
        }
    }
}
