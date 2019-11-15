package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //дадена е последователност от редове входни данни
    //тези редове сформират char матрица (празните позиции се запълват със спейосеве, за да се формира
    //правоъгълна матрица, а не Jagged)
    //матрицата трябва да се завърти на 90, 180 и 270 градуса
    //всяка от завъртяните матрици се принтира на конзолата

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> matrix = new ArrayList<>();
        int maxLength = Integer.MIN_VALUE;

        String matrixElement = reader.readLine();
        while (!matrixElement.equals("END")) {
            matrix.add(matrixElement);
            if (matrixElement.length() > maxLength) {
                maxLength = matrixElement.length();
            }
            matrixElement = reader.readLine();
        }
        //90 degrees rotation
        System.out.println("Rotate(90)");
        matrix = rotate(matrix, maxLength);
        print(matrix);

        System.out.println();

        //180 degrees rotation
        System.out.println("Rotate(180)");
        matrix = rotate(matrix, maxLength);
        print(matrix);

        System.out.println();

        //270 degrees rotation
        System.out.println("Rotate(270)");
        matrix = rotate(matrix, maxLength);
        print(matrix);

    }

    private static List<String> rotate(List<String> strings, int maxLength) {
        List<String> temp = new ArrayList<>();
        for (int col = 0; col < maxLength; col++) {
            StringBuilder currentString = new StringBuilder();
            for (int row = strings.size() - 1; row >= 0; row--) {
                if (col >= strings.get(row).length()) {
                    currentString.append(" ");
                } else {
                    currentString.append(strings.get(row).charAt(col));
                }
            } temp.add(currentString.toString());
        } return temp;
    }

    private static void print(List<String> strings) {
        for (String string : strings) {
            System.out.printf("%s%n", String.join("", string));
        }
    }
}
