package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Student> students = new ArrayList<>();

        int countOfStudents = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < countOfStudents; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String firstName = input[0];
            String lastName = input[1];
            double grade = Double.parseDouble(input[2]);

            Student student = new Student(firstName, lastName, grade);
            students.add(student);
        }

        students.sort(Comparator.comparingDouble(Student::getGrade).reversed());

        for (Student student : students) {
            System.out.print(student.toString());
        }
    }
}