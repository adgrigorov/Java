package com.company;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Orders {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Double> orders = new LinkedHashMap<>();
        Map<String, Integer> totalProducts = new LinkedHashMap<>();

        String input = sc.nextLine();
        while(!input.equals("buy")) {
            String[] tokens = input.split("\\s+");
            String productName = tokens[0];
            double productPrice = Double.parseDouble(tokens[1]);
            int productQuantity = Integer.parseInt(tokens[2]);

            if (!orders.containsKey(productName)) {
                orders.put(productName, productPrice * productQuantity);
                totalProducts.put(productName, productQuantity);
            } else {
                totalProducts.put(productName, totalProducts.get(productName) + productQuantity);
                orders.put(productName, totalProducts.get(productName) * productPrice);
            }
            input = sc.nextLine();
        }

        for (Map.Entry<String, Double> entry : orders.entrySet()) {
            System.out.printf("%s -> %.2f%n", entry.getKey(), entry.getValue());
        }
    }
}
