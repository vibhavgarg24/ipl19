package com.example.ipl;

public class Match {

    int match_id;
    int team1_id;
    int team2_id;
    String team1_name = "";
    String team2_name = "";
    String team1_abv = "";
    String team2_abv = "";
    String date = "";
    String city = "";
    int toss_winner_id;
    String toss_winner = "";
    String toss_decision = "";
    String result = "";
    int win_by_runs;
    int win_by_wickets;
    String team1_score = "";
    String team2_score = "";
    int match_winner_id;
    String match_winner;
    String match_winner_pic_url = "";
    int bat_first;

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(int team1_id) {
        this.team1_id = team1_id;
    }

    public int getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(int team2_id) {
        this.team2_id = team2_id;
    }

    public String getTeam1_name() {
        return team1_name;
    }

    public void setTeam1_name(String team1_name) {
        this.team1_name = team1_name;
    }

    public String getTeam2_name() {
        return team2_name;
    }

    public void setTeam2_name(String team2_name) {
        this.team2_name = team2_name;
    }

    public String getTeam1_abv() {
        return team1_abv;
    }

    public void setTeam1_abv(String team1_abv) {
        this.team1_abv = team1_abv;
    }

    public String getTeam2_abv() {
        return team2_abv;
    }

    public void setTeam2_abv(String team2_abv) {
        this.team2_abv = team2_abv;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getToss_winner_id() {
        return toss_winner_id;
    }

    public void setToss_winner_id(int toss_winner_id) {
        this.toss_winner_id = toss_winner_id;
    }

    public String getToss_winner() {
        return toss_winner;
    }

    public void setToss_winner(String toss_winner) {
        this.toss_winner = toss_winner;
    }

    public String getToss_decision() {
        return toss_decision;
    }

    public void setToss_decision(String toss_decision) {
        this.toss_decision = toss_decision;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getWin_by_runs() {
        return win_by_runs;
    }

    public void setWin_by_runs(int win_by_runs) {
        this.win_by_runs = win_by_runs;
    }

    public int getWin_by_wickets() {
        return win_by_wickets;
    }

    public void setWin_by_wickets(int win_by_wickets) {
        this.win_by_wickets = win_by_wickets;
    }

    public String getTeam1_score() {
        return team1_score;
    }

    public void setTeam1_score(String team1_score) {
        this.team1_score = team1_score;
    }

    public String getTeam2_score() {
        return team2_score;
    }

    public void setTeam2_score(String team2_score) {
        this.team2_score = team2_score;
    }

    public int getMatch_winner_id() {
        return match_winner_id;
    }

    public void setMatch_winner_id(int match_winner_id) {
        this.match_winner_id = match_winner_id;
    }

    public String getMatch_winner() {
        return match_winner;
    }

    public void setMatch_winner(String match_winner) {
        this.match_winner = match_winner;
    }

    public String getMatch_winner_pic_url() {
        return match_winner_pic_url;
    }

    public void setMatch_winner_pic_url(String match_winner_pic_url) {
        this.match_winner_pic_url = match_winner_pic_url;
    }

    public int getBat_first() {
        return bat_first;
    }

    public void setBat_first(int bat_first) {
        this.bat_first = bat_first;
    }
}
