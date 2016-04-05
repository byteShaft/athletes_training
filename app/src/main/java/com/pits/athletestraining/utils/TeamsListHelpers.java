package com.pits.athletestraining.utils;


import java.util.ArrayList;

public class TeamsListHelpers {

    public static ArrayList<String[]> getAllTeams() {
        ArrayList<String[]> teamList = new ArrayList<>();
        String[] team1 = {"logo", "Pakistan"};
        String[] team2 = {"logo", "India"};
        String[] team3 = {"logo", "Australia"};
        String[] team4 = {"logo", "New Zealand"};
        String[] team5 = {"logo", "WIndies"};

        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        teamList.add(team4);
        teamList.add(team5);

        return teamList;
    }
}
