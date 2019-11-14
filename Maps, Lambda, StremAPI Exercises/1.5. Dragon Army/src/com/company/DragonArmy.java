package com.company;

import java.util.*;

public class DragonArmy {

    private static final int DEFAULT_DMG = 45;
    private static final int DEFAULT_HEALTH = 250;
    private static final int DEFAULT_ARMOR = 10;

    private static void addDragon(Map<String, List<Dragon>> dragons, String dragonType, String name, int damage, int health, int armor) {
        dragons.get(dragonType).add(new Dragon(name, damage, health, armor));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Dragon>> dragons = new LinkedHashMap<>();
        //key-dragonType, values-list of dragon objects(name, damage, health, armor)
        //map sorting is in the order if input

        int numberOfDragons = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numberOfDragons; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String dragonType = input[0];
            String name = input[1];
            //checking if input is null whether to assign default values or not
            int damage = input[2].equals("null") ? DEFAULT_DMG : Integer.parseInt(input[2]);
            int health = input[3].equals("null") ? DEFAULT_HEALTH : Integer.parseInt(input[3]);
            int armor = input[4].equals("null") ? DEFAULT_ARMOR : Integer.parseInt(input[4]);

            //adds a new dragon to the map
            if (!dragons.containsKey(dragonType)) {
                dragons.put(dragonType, new ArrayList<>());
                addDragon(dragons, dragonType, name, damage, health, armor);
            } else {
                //checking a current key(dragonType)'s value's list of dragons
                //to overwrite the dragon's values if there's a duplicate entry
                //overwriting is done by deleting all equal elements and creating a new element based on the duplicate entry 
                for (int j = 0; j < dragons.get(dragonType).size(); j++) {
                    if (dragons.get(dragonType).get(j).getName().equals(name)) {
                        dragons.get(dragonType).remove(dragons.get(dragonType).get(j));
                        break;
                    }
                }
                addDragon(dragons, dragonType, name, damage, health, armor);
            }
        }


        //sorting dragons by their name for each dragonType
        Comparator<Dragon> comparator = Comparator.comparing(Dragon::getName);
        dragons.values().forEach(dragon -> {
            dragon.sort(comparator);
        });

        //maps that keep average values for every dragonType
        //key-dragonType
        Map<String, Double> averageDragonDamage = new LinkedHashMap<>();
        Map<String, Double> averageDragonHealth = new LinkedHashMap<>();
        Map<String, Double> averageDragonArmor = new LinkedHashMap<>();

        //fills up averageValues maps
        for (Map.Entry<String, List<Dragon>> dragon : dragons.entrySet()) {
            double avgDmg = dragon.getValue()
                    .stream()
                    .mapToDouble(Dragon::getDamage)
                    .average()
                    .getAsDouble();

            double avgArmor = dragon.getValue()
                    .stream()
                    .mapToDouble(Dragon::getArmor)
                    .average()
                    .getAsDouble();

            double avgHealth = dragon.getValue()
                    .stream()
                    .mapToDouble(Dragon::getHealth)
                    .average()
                    .getAsDouble();

            averageDragonDamage.put(dragon.getKey(), avgDmg);
            averageDragonArmor.put(dragon.getKey(), avgArmor);
            averageDragonHealth.put(dragon.getKey(), avgHealth);
        }

        //output
        dragons.entrySet()
                .stream()
                .forEach(dragon -> {
                    //prints the key with all average values for the current key
                    System.out.printf("%s::(%.2f/%.2f/%.2f)%n", dragon.getKey(),
                            averageDragonDamage.get(dragon.getKey()),
                            averageDragonHealth.get(dragon.getKey()),
                            averageDragonArmor.get(dragon.getKey()));

                    //prints dragons for each key
                    dragon.getValue()
                            .stream()
                            .forEach(detail -> {
                                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                                        detail.getName(), detail.getDamage(), detail.getHealth(), detail.getArmor());
                            });
                });
    }
}
