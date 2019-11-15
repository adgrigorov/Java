package com.company;

import java.util.List;

public class Department {
    private String name;
    private List<Employee> employees;
    private double averageSalary;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
        this.averageSalary = employees
                .stream()
                .mapToDouble(Employee::getSalary)
                .average().getAsDouble();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}