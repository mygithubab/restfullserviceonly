package models;


import com.google.gson.annotations.Expose;
import controller.DatabaseController;

import java.util.ArrayList;

public class Bet extends DatabaseController {
    @Expose
    public String id;
    @Expose
    public BDObjectID _id;
    @Expose
    public String user_id;
    @Expose
    public String game_id;
    @Expose
    public double odd;
    @Expose
    public int choice;
    @Expose
    public ArrayList<String> teams;
    @Expose
    public double price;
    @Expose
    public String sport_key;


    public BDObjectID get_id() {
        return _id;
    }

    public void set_id(BDObjectID _id) {
        this._id = _id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSport_key() {
        return sport_key;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    public void setSport_key(String sport_key) {
        this.sport_key = sport_key;
    }

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

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
