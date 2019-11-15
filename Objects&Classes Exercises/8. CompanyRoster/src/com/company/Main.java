package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {

    private static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Employee> employees = new ArrayList<>();

        int numberOfEmployees = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfEmployees; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String department = input[3];

            Employee employee = new Employee();
            employee.setName(name);
            employee.setSalary(salary);
            employee.setPosition(position);
            employee.setDepartment(department);

            switch (input.length) {
                case 5:
                    if(input[4].contains("@")) {
                        String email = input[4];
                        if (isValidEmail(email)) {
                            employee.setEmail(email);
                        }
                    }
                    else {
                        int age = Integer.parseInt(input[4]);
                        employee.setAge(age);
                    }
                    break;

                case 6:
                    if(input[4].contains("@")) {
                        String email = input[4];
                        int age = Integer.parseInt(input[5]);
                        if (isValidEmail(email)) {
                            employee.setEmail(email);
                            employee.setAge(age);
                        }
                        else {
                            employee.setAge(age);
                        }
                    }

                    else if (input[5].contains("@")){
                        int age = Integer.parseInt(input[4]);
                        String email = input[5];
                        if (isValidEmail(email)) {
                            employee.setAge(age);
                            employee.setEmail(email);
                        }
                        else {
                            employee.setAge(age);
                        }
                        break;
                    }
            }
            employees.add(employee);
        }

        List<String> departmentsList = employees
                .stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        List<Department> departments = new ArrayList<>();

        for (String department : departmentsList) {
            departments.add(new Department(department,
                    employees
                            .stream()
                            .filter(employee -> employee.getDepartment().equals(department))
                            .collect(Collectors.toList())));
        }

        departments.sort(Comparator.comparingDouble(Department::getAverageSalary).reversed());
        Department department = departments.get(0);
        department.getEmployees().sort(Comparator.comparingDouble(Employee::getSalary).reversed());

        System.out.printf("Highest average salary: %s%n", department.getName());
        for (Employee employee : department.getEmployees()) {
            System.out.printf("%s %.2f %s %d%n", employee.getName(), employee.getSalary()
            , employee.getEmail(), employee.getAge());
        }
    }
}