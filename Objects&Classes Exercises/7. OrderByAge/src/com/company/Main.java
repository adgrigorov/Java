package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Person> people = new ArrayList<>();

        String input = sc.nextLine();
        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            int age = Integer.parseInt(tokens[2]);

            Person person = new Person(name, id, age);
            people.add(person);

            input = sc.nextLine();
        }

        people.sort((first, second) -> first.getAge() - second.getAge());

        for (Person person : people) {
            System.out.print(person.toString());
        }
    }
}