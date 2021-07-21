package com.example.ipl;

public class PlayerRecords extends Player {

    int total_runs_scored;
    int total_balls_played;
    float strike_rate;
    int highest_score;
    int total_fours;
    int total_sixes;
    int total_fifties;
    int total_centuries;

    int total_runs_given;
    int total_balls_bowled;
    int total_wickets;
    float total_economy;
    float bowling_strike_rate;

    int total_catches;

    public int getTotal_runs_scored() {
        return total_runs_scored;
    }

    public void setTotal_runs_scored(int total_runs_scored) {
        this.total_runs_scored = total_runs_scored;
    }

    public int getTotal_balls_played() {
        return total_balls_played;
    }

    public void setTotal_balls_played(int total_balls_played) {
        this.total_balls_played = total_balls_played;
    }

    public float getStrike_rate() {
        if (total_balls_played == 0) {
            return 0;
        }
        strike_rate = ((float) total_runs_scored/total_balls_played) * 100;
        return strike_rate;
    }


    public int getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(int highest_score) {
        this.highest_score = highest_score;
    }

    public int getTotal_fours() {
        return total_fours;
    }

    public void setTotal_fours(int total_fours) {
        this.total_fours = total_fours;
    }

    public int getTotal_sixes() {
        return total_sixes;
    }

    public void setTotal_sixes(int total_sixes) {
        this.total_sixes = total_sixes;
    }

    public int getTotal_fifties() {
        return total_fifties;
    }

    public void setTotal_fifties(int total_fifties) {
        this.total_fifties = total_fifties;
    }

    public int getTotal_centuries() {
        return total_centuries;
    }

    public void setTotal_centuries(int total_centuries) {
        this.total_centuries = total_centuries;
    }

    public int getTotal_runs_given() {
        return total_runs_given;
    }

    public void setTotal_runs_given(int total_runs_given) {
        this.total_runs_given = total_runs_given;
    }

    public int getTotal_balls_bowled() {
        return total_balls_bowled;
    }

    public void setTotal_balls_bowled(int total_balls_bowled) {
        this.total_balls_bowled = total_balls_bowled;
    }

    public int getTotal_wickets() {
        return total_wickets;
    }

    public void setTotal_wickets(int total_wickets) {
        this.total_wickets = total_wickets;
    }

    public float getTotal_economy() {
        if (total_balls_bowled == 0) {
            return 0;
        }
        total_economy = ( (float) total_runs_given / total_balls_bowled) * 6;
        return total_economy;
    }

    public float getBowling_strike_rate() {
        if (total_wickets == 0) {
            return 0;
        }
        bowling_strike_rate = ((float) total_balls_bowled / total_wickets);
        return bowling_strike_rate;
    }

    public int getTotal_catches() {
        return total_catches;
    }

    public void setTotal_catches(int total_catches) {
        this.total_catches = total_catches;
    }
}
