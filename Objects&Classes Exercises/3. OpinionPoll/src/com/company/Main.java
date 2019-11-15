package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Main {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(sc.nextLine());
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String name = input[0];
            int age = Integer.parseInt(input[1]);
            Person person = new Person(name, age);
            if (person.getAge() >= 30) {
                people.add(person);
            }
        }

        Collections.sort(people);
        people.forEach(person -> System.out.printf("%s - %d%n", person.getName(), person.getAge()));
    }
}