package com.company;

import java.util.*;

public class LegendaryFarming {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> junkMaterials = new TreeMap<>(); //sorting alphabetically by key
        Map<String, Integer> legendaryMaterials = new HashMap<>(); //sort is defined manually later in the code, hence we don't need pre-defined map

        //legendary materials are pre-defined
        //and are always 0 at the begining of the farm
        legendaryMaterials.put("motes", 0);
        legendaryMaterials.put("fragments", 0);
        legendaryMaterials.put("shards", 0);

        int[] collectedMaterials = new int[3];
        //0: motes, 1: fragments, 2: shards

        boolean itemObtained = false;

        while(!itemObtained) {
            String[] reagents = sc.nextLine().split("\\s+");
            for(int i = 0; i < reagents.length; i+=2 ) {
                int quantity = Integer.parseInt(reagents[i]);
                String material = reagents[i + 1].toLowerCase();

                if (legendaryMaterials.containsKey(material)) {
                    int currentQuantity = legendaryMaterials.get(material);
                    legendaryMaterials.put(material, quantity + currentQuantity);

                    if (material.equals("motes")) {
                        collectedMaterials[0] += quantity;
                        itemObtained = collectedMaterials[0] >= 250;
                    } else if (material.equals("fragments")) {
                        collectedMaterials[1] += quantity;
                        itemObtained = collectedMaterials[1] >= 250;
                    } else if (material.equals("shards")) {
                        collectedMaterials[2] += quantity;
                        itemObtained = collectedMaterials[2] >= 250;
                    }

                    if (itemObtained) {
                        break;
                    }
                } else {
                    if (!junkMaterials.containsKey(material)) {
                        junkMaterials.put(material, quantity);
                    } else {
                        int currentQuantity = junkMaterials.get(material);
                        junkMaterials.put(material, currentQuantity + quantity);
                    }
                }
            }
        }

        String itemName = "";
        if (collectedMaterials[0] >= 250) {
            int newQuantity = legendaryMaterials.get("motes") - 250;
            legendaryMaterials.put("motes", newQuantity);
            itemName = "Dragonwrath";
        } else if(collectedMaterials[1] >= 250) {
            int newQuantity = legendaryMaterials.get("fragments") - 250;
            legendaryMaterials.put("fragments", newQuantity);
            itemName = "Valanyr";
        } else {
            int newQuantity = legendaryMaterials.get("shards") - 250;
            legendaryMaterials.put("shards", newQuantity);
            itemName = "Shadowmourne";
        }

        System.out.printf("%s obtained!%n", itemName);

        legendaryMaterials.entrySet()
                .stream()
                .sorted((first, second) -> {
                    int result = second.getValue().compareTo(first.getValue());
                    if (result == 0) {
                        result = first.getKey().compareTo(second.getKey());
                    }
                    return result;
                 }) //desc sort by quantity
                .forEach(entry -> System.out.printf("%s: %d%n", entry.getKey(), entry.getValue()));


        for (Map.Entry<String, Integer> entry : junkMaterials.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }
}
