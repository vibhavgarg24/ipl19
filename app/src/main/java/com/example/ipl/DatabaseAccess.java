package com.example.ipl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess dbAccess;
    Cursor cursor = null;

    private DatabaseAccess (Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance (Context context) {
        if (dbAccess == null) {
            dbAccess = new DatabaseAccess(context);
        }
        return dbAccess;
    }

    public void openConnection() {
        this.db = openHelper.getWritableDatabase();
    }

    public void closeConnection() {
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();

        String query = "select * from teams";
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Team team = new Team();
            team.setTeam_id(cursor.getInt(0));
            team.setTeam_name(cursor.getString(1));
            team.setTeam_abv(cursor.getString(2));

            team.setMatches(cursor.getInt(3));
            team.setWon(cursor.getInt(4));
            team.setLost(cursor.getInt(5));
            team.setNrr(cursor.getString(6));
            team.setPoints(cursor.getInt(7));

            teams.add(team);
        }
        return teams;
    }


    public ArrayList<String> getTeamNames() {
        ArrayList<String> teams = new ArrayList<>();

        String query = "select * from teams";
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String team_name = cursor.getString(1);
            teams.add(team_name);
        }
        return teams;
    }

    public ArrayList<Match> getMatches() {
        ArrayList<Match> matches = new ArrayList<>();

        String query = "select matchID,team1,team2, a.team_name as team1Name, b.team_name as team2Name, a.team_abv as team1_abv, b.team_abv as team2_abv, team1score, team2score, result, win_by_runs, win_by_wickets, bat_first from matches inner join (select teamID,team_name,team_abv from teams) as a on a.teamID = matches.team1 inner join (select teamID,team_name,team_abv from teams) as b on b.teamID = matches.team2";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Match match = new Match();
            match.setMatch_id(cursor.getInt(0));

            match.setTeam1_id(cursor.getInt(1));
            match.setTeam2_id(cursor.getInt(2));

            match.setTeam1_name(cursor.getString(3));
            match.setTeam2_name(cursor.getString(4));

            match.setTeam1_abv(cursor.getString(5));
            match.setTeam2_abv(cursor.getString(6));

            match.setTeam1_score(cursor.getString(7));
            match.setTeam2_score(cursor.getString(8));

            match.setResult(cursor.getString(9));
            match.setWin_by_runs(cursor.getInt(10));
            match.setWin_by_wickets(cursor.getInt(11));

            match.setBat_first(cursor.getInt(12));

            matches.add(match);
        }
        return matches;
    }

    public ArrayList<Player> getPlayers (int team_id) {
        ArrayList<Player> players = new ArrayList<>();

        String query = "select * from players where teamID=" + team_id;
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Player player = new Player();
            player.setPlayer_id(cursor.getInt(0));
            player.setPlayer_name(cursor.getString(2));
            player.setPlayer_type(cursor.getString(4));
            player.setPic_url(cursor.getString(5));

            players.add(player);
        }
        return players;
    }

    public int getCaptainID (int team_id) {
        int captain_id = 0;
        
        String query = "select captainID from teams where teamID = " + team_id;
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            captain_id = cursor.getInt(0);
        }
        return captain_id;
    }

    public Match getMatch (int match_id) {
        Match match = new Match();

        String query = "select matchID,team1,team2, a.team_name as team1Name, b.team_name as team2Name, a.team_abv as team1_abv, b.team_abv as team2_abv, team1score, team2score, date, city,toss_winner, toss_decision, matchwinnerID, players.name as Man_of_the_match, players.url, bat_first from matches inner join (select teamID,team_name,team_abv from teams) as a on a.teamID = matches.team1 inner join (select teamID,team_name,team_abv from teams) as b on b.teamID = matches.team2 inner join players on players.playerID=matches.matchwinnerID where matchID =" + match_id;
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            match.setTeam1_id(cursor.getInt(1));
            match.setTeam2_id(cursor.getInt(2));
            match.setTeam1_name(cursor.getString(3));
            match.setTeam2_name(cursor.getString(4));
            String team1_abv = cursor.getString(5);
            match.setTeam1_abv(team1_abv);
            String team2_abv = cursor.getString(6);
            match.setTeam2_abv(team2_abv);

            match.setTeam1_score(cursor.getString(7));
            match.setTeam2_score(cursor.getString(8));

            match.setDate(cursor.getString(9));
            match.setCity(cursor.getString(10));

            int toss_winner_no = cursor.getInt(11);
            if (toss_winner_no == 1) {
                match.setToss_winner(team1_abv);
            } else if (toss_winner_no == 2) {
                match.setToss_winner(team2_abv);
            }
            match.setToss_decision(cursor.getString(12));

            match.setMatch_winner_id(cursor.getInt(13));
            match.setMatch_winner(cursor.getString(14));
            match.setMatch_winner_pic_url(cursor.getString(15));

            match.setBat_first(cursor.getInt(16));
        }
        return match;
    }

    public Player getPlayer (int player_id) {
        Player player = new Player();

        String query = "select playerID, players.teamID,name,country,type,team_name,team_abv, url from players inner join teams on teams.teamID=players.teamID where playerID =" + player_id;
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            player.setPlayer_id(cursor.getInt(0));
            player.setPlayer_name(cursor.getString(2));

            player.setTeam_id(cursor.getInt(1));
            player.setCountry(cursor.getString(3));
            player.setPlayer_type(cursor.getString(4));

            player.setTeam_name(cursor.getString(5));
            player.setTeam_abv(cursor.getString(6));

            player.setPic_url(cursor.getString(7));

        }
        return player;
    }

    public ArrayList<Batsman> getBatsmen(int match_id, int team_id) {
        ArrayList<Batsman> batsmen = new ArrayList<>();

        String query = "select scorecard.playerID, players.name, runs_scored, balls_played, fours, sixes from scorecard inner join players on scorecard.playerID = players.playerID where did_bat = 'Yes' and matchID = " + match_id + " and players.teamID = " + team_id;

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Batsman batsman = new Batsman();

            batsman.setPlayer_id(cursor.getInt(0));
            batsman.setPlayer_name(cursor.getString(1));
            batsman.setRuns(cursor.getInt(2));
            batsman.setBalls(cursor.getInt(3));
            batsman.setFours(cursor.getInt(4));
            batsman.setSixes(cursor.getInt(5));

            batsmen.add(batsman);
        }
        return batsmen;
    }

    public ArrayList<Bowler> getBowlers(int match_id, int team_id) {
        ArrayList<Bowler> bowlers = new ArrayList<>();

        String query = "select scorecard.playerID, players.name, balls_bowled, runs_given, wickets_taken from scorecard inner join players on scorecard.playerID = players.playerID where balls_bowled>0 and matchID = " + match_id + " and players.teamID = " + team_id;

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Bowler bowler = new Bowler();

            bowler.setPlayer_id(cursor.getInt(0));
            bowler.setPlayer_name(cursor.getString(1));
            bowler.setBalls_bowled(cursor.getInt(2));
            bowler.setRuns_given(cursor.getInt(3));
            bowler.setWickets(cursor.getInt(4));

            bowlers.add(bowler);
        }
        return bowlers;
    }

    public PlayerRecords getPlayerRecords (int player_id) {
        PlayerRecords records = new PlayerRecords();

        String query = "select sum(runs_scored), sum(balls_played), max(runs_scored), sum(fours), sum(sixes), sum(runs_given), sum(wickets_taken), sum(catches), sum(balls_bowled) from scorecard where playerID = " + player_id;

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            records.setTotal_runs_scored(cursor.getInt(0));
            records.setTotal_balls_played(cursor.getInt(1));
            records.setHighest_score(cursor.getInt(2));
            records.setTotal_fours(cursor.getInt(3));
            records.setTotal_sixes(cursor.getInt(4));
            records.setTotal_runs_given(cursor.getInt(5));
            records.setTotal_wickets(cursor.getInt(6));
            records.setTotal_catches(cursor.getInt(7));
            records.setTotal_balls_bowled(cursor.getInt(8));
        }

        String query_fifties = "select count() as fifties from scorecard where runs_scored >= 50 and runs_scored <100 and playerID = " + player_id;
        cursor = db.rawQuery(query_fifties, new String[]{});
        while (cursor.moveToNext()) {
            records.setTotal_fifties(cursor.getInt(0));
        }

        String query_centuries = "select count() as centuries from scorecard where runs_scored >= 100 and playerID = " + player_id;
        cursor = db.rawQuery(query_centuries, new String[]{});
        while (cursor.moveToNext()) {
            records.setTotal_centuries(cursor.getInt(0));
        }

        return records;
    }

    public ArrayList<String5Data> getMostRuns() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Runs");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sum(runs_scored) as total_runs from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by total_runs desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostFours() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Fours");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sum(fours) as no_of_fours from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by no_of_fours desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostFoursInnings() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Fours");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,fours from scorecard inner join players on players.playerID=scorecard.playerID order by fours desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostSixes() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Sixes");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sum(sixes) as no_of_sixes from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by no_of_sixes desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostSixesInnings() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Sixes");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sixes from scorecard inner join players on players.playerID=scorecard.playerID order by sixes desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getHighestScore() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Runs");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,max(runs_scored) as highest_score from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by highest_score desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }


    @SuppressLint("DefaultLocale")
    public ArrayList<String5Data> getBestBattingSR() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Batting S/R");
        playerRecords.add(def);

        String query = "select players.playerID,players.name, (sum(runs_scored*1.0)/ sum(balls_played))*100 as best_strike_rate from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID having sum(scorecard.balls_played)>=50 order by best_Strike_rate desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.format("%.2f",cursor.getFloat(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostWickets() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Wickets");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sum(wickets_taken) as no_of_wickets from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by no_of_wickets desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    @SuppressLint("DefaultLocale")
    public ArrayList<String5Data> getBestEconomy() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Economy");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,( (sum(runs_given)*1.0) / sum(balls_bowled) )*6 as economy from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID having sum(balls_bowled)>=12 order by economy limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.format("%.2f",cursor.getFloat(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    @SuppressLint("DefaultLocale")
    public ArrayList<String5Data> getBestBowlingSR() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Bowling S/R");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,( (sum(balls_bowled)*1.0) / sum(wickets_taken)) as bowling_strike_rate from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID having sum(wickets_taken)>4 order by bowling_strike_rate limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.format("%.2f",cursor.getFloat(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }


    public ArrayList<String5Data> getMostCatches() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Catches");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,sum(catches) as no_of_catches from scorecard inner join players on players.playerID=scorecard.playerID group by players.playerID order by no_of_catches desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public ArrayList<String5Data> getMostMotMs() {
        ArrayList<String5Data> playerRecords = new ArrayList<>();

        String5Data def = new String5Data();
        def.setData1("Player Name");
        def.setData2("Times");
        playerRecords.add(def);

        String query = "select players.playerID,players.name,count(matchwinnerID) as no_of_times from matches inner join players on players.playerID=matches.matchwinnerID group by playerID order by no_of_times desc limit 15";

        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            String5Data record = new String5Data();

            record.setData0(String.valueOf(cursor.getInt(0)));
            record.setData1(cursor.getString(1));
            record.setData2(String.valueOf(cursor.getInt(2)));

            playerRecords.add(record);
        }
        return playerRecords;
    }

    public boolean isFavourite(int player_id) {

        String query = "select * from favourites where playerID = " + player_id;
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()){
            return true;
        }
        return false;
    }

    public ArrayList<Player> getFavourites () {
        ArrayList<Player> players = new ArrayList<>();

        String query = "select favourites.playerID, players.name, players.type, players.url from favourites inner join players on favourites.playerID = players.playerID";
        cursor = db.rawQuery(query, new String[]{});
        while (cursor.moveToNext()) {
            Player player = new Player();
            player.setPlayer_id(cursor.getInt(0));
            player.setPlayer_name(cursor.getString(1));
            player.setPlayer_type(cursor.getString(2));
            player.setPic_url(cursor.getString(3));

            players.add(player);
        }
        return players;
    }


    public void addFav(int player_id) {

        String query = "insert into favourites values (" + player_id + ")";
        db.execSQL(query);
    }


    public void removeFav(int player_id) {

        String query = "delete from favourites where playerID = " + player_id;
        db.execSQL(query);
    }

}
