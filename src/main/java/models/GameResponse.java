package models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class GameResponse implements Serializable{

    @Expose
    public String game_id;
    @Expose
    public int team1;
    @Expose
    public int team2;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public int getTeam1() {
        return team1;
    }

    public void setTeam1(int team1) {
        this.team1 = team1;
    }

    public int getTeam2() {
        return team2;
    }

    public void setTeam2(int team2) {
        this.team2 = team2;
    }
}
