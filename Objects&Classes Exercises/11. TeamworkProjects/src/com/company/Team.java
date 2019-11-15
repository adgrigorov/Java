package com.company;

import java.util.List;

public class Team {

    private String name;
    private List<String> members;

    public Team(String name, List<String> members) {
        this.name = name;
        this.members = members;
    }

    public int getMembersCount() {
        return members.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getMember(int i) {
        return members.get(i);
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
