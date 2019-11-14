package com.company;

import java.util.*;

public class StudentAcademy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Double>> students = new HashMap<>();

        int numberOfStudents = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfStudents; i++) {
            String name = sc.nextLine();
            double grade = Double.parseDouble(sc.nextLine());

            if (!students.containsKey(name)) {
                students.put(name, new ArrayList<>());
                students.get(name).add(grade);
            } else {
                students.get(name).add(grade);
            }
        }

        Map<String, Double> averageGrades = new LinkedHashMap<>();

        for (Map.Entry<String, List<Double>> entry : students.entrySet()) {
            double avgGrade = entry.getValue()
                    .stream()
                    .mapToDouble(a -> a)
                    .average()
                    .getAsDouble();

            if (avgGrade >= 4.50) {
                averageGrades.put(entry.getKey(), avgGrade);
            }
        }

        averageGrades.entrySet()
                .stream()
                .sorted((f, s) -> {
                    return s.getValue().compareTo(f.getValue());
                }).forEach(grade -> {
            System.out.printf("%s -> %.2f%n", grade.getKey(), grade.getValue());
        });
    }
}
