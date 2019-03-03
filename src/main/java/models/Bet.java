package models;


import controller.DatabaseController;

import java.util.ArrayList;

public class Bet extends DatabaseController {
    public String id;
    public String user_id;
    public String game_id;
    public ArrayList<String> teams;
    public long price;

    public Bet() {
        super("bets");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public ArrayList<String> getTeams()  {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
