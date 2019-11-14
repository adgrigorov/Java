package com.company;

import java.util.*;

public class CompanyUsers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        Map<String, List<String>> companies = new LinkedHashMap<>();

        while (!input.equals("End")) {
            String[] tokens = input.split(" -> ");
            String companyName = tokens[0];
            String employeeID = tokens[1];

            if (!companies.containsKey(companyName)) {
                companies.put(companyName, new ArrayList<>());
                companies.get(companyName).add(employeeID);
            } else {
                if (!companies.get(companyName).contains(employeeID)) {
                    companies.get(companyName).add(employeeID);
                }
            }

            input = sc.nextLine();
        }

        companies.entrySet()
                .stream()
                .sorted((f, s) -> f.getKey().compareTo(s.getKey()))
                .forEach(company -> {
                    System.out.println(company.getKey());
                    company.getValue()
                            .stream()
                            .forEach(employee -> System.out.printf("-- %s%n", employee));
                });
    }
}
