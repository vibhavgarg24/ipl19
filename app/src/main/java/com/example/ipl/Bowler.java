package com.example.ipl;

public class Bowler extends Player {

    String overs = "";
    int runs_given;
    int balls_bowled;
    int wickets;
    float economy;

    public String getOvers() {
        if (balls_bowled == 0) {
            return "0.0";
        }
        int abs_over = balls_bowled / 6;
        int balls_over = balls_bowled % 6;
        return abs_over + "." + balls_over;
    }

    public float getEconomy() {
        if (balls_bowled == 0) {
            return 0;
        }
        economy = ( (float) runs_given / balls_bowled) * 6;
        return economy;
    }

    public int getBalls_bowled() {
        return balls_bowled;
    }

    public void setBalls_bowled(int balls_bowled) {
        this.balls_bowled = balls_bowled;
    }

    public int getRuns_given() {
        return runs_given;
    }

    public void setRuns_given(int runs_given) {
        this.runs_given = runs_given;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
}
