package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Team> teams = new ArrayList<>();

        int numberOfTeams = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfTeams; i++) {
            String[] teamInfo = sc.nextLine().split("-");
            String teamCreator = teamInfo[0];
            String teamName = teamInfo[1];
            boolean newTeam = true;   // checks if a new team can be created

            //chcecking if the team desired to be created already exists in the list of already created teams
            for (Team team : teams) {
                if (team.getName().equals(teamName)) {
                    newTeam = false;
                    System.out.printf("Team %s was already created!%n", teamName);
                    break;
                }
            }

            //checking if the {user} hasn't already created another team
            for (Team team : teams) {
                if (team.getMembers().get(0).equals(teamCreator)) {
                    newTeam = false;
                    System.out.printf("%s cannot create another team!%n", teamCreator);
                    break;
                }
            }

            //creating the team if both checks passed successfully
            if (newTeam) {
                List<String> members = new ArrayList<>();
                members.add(teamCreator);

                Team team = new Team(teamName, members);

                System.out.printf("Team %s has been created by %s!%n", teamName, teamCreator);

                teams.add(team);
            }
        }
        //finished with adding teams


        String assignment = sc.nextLine();

        while (!assignment.equals("end of assignment")) {
            String[] assignmentTokens = assignment.split("->");
            String userToAdd = assignmentTokens[0];
            String teamToJoin = assignmentTokens[1];


            boolean existingTeam = false;  //checking if the team exist with it
            boolean canJoin = true;  //checking if the {user} can join
            int teamIndex = 0;  //team index in the list of teams, always starting from first team

            //checks for existing team
            //finds the existing team in the list and sets the index to the index in the list
            for (int i = 0; i < teams.size(); i++) {
                if (teamToJoin.equals(teams.get(i).getName())) {
                    existingTeam = true;
                    teamIndex = i;
                }
            }

            if (!existingTeam) {
                System.out.printf("Team %s does not exist!%n", teamToJoin);
            }

            //checking if {user} can join the found existing team
            else {
                for (Team team : teams) {
                    if (!canJoin) {
                        break;
                    }
                    //loop through team members to check if {user} isn't already in another team
                    for (String member : team.getMembers()) {
                        if (userToAdd.equals(member)) {
                            System.out.printf("Member %s cannot join team %s!%n", userToAdd, teamToJoin);
                            canJoin = false;
                            break;
                        }
                    }
                }

                //adds the {user} to the existing team in the already found index in the list
                if (canJoin) {
                    teams.get(teamIndex).getMembers().add(userToAdd);
                }
            }

            assignment = sc.nextLine();
        }

        List<String> teamsToDisband = new ArrayList<>();

        //sorting by count of members descending and by team name alphabetically ascending
        teams = teams
                .stream()
                .sorted(Comparator.comparingInt(Team::getMembersCount).reversed().thenComparing(Team::getName))
                .collect(Collectors.toList());

        for (Team team : teams) {
            if (team.getMembers().size() > 1) {
                System.out.println(team.getName());
                System.out.println("- " + team.getMembers().get(0)); //{team} creator
                team.getMembers().remove(0);

                //{team} members w/o the creator
                List<String> teamMembers = team.getMembers();
                teamMembers = team.getMembers()
                        .stream()
                        .sorted()
                        .collect(Collectors.toList());

                for (int i = 0; i < teamMembers.size(); i++) {
                    System.out.println("-- " + teamMembers.get(i));
                }
            }

            //marks {team} for disbanding if the only member is its creator
            else {
                teamsToDisband.add(team.getName());
            }
        }

        System.out.println("Teams to disband:");

        if (teamsToDisband.size() > 0) {
            teamsToDisband
                    .stream()
                    .sorted()
                    .forEach(System.out::println);
        }
    }
}
