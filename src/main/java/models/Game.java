package models;

import controller.DatabaseController;

import java.util.Date;

public class Game extends DatabaseController {
    public String id;
    public String name;
    public String t1_id;
    public String t2_id;
    public String sport_id;
    public Date start_time;
    public Date end_time;

    public Game() {
        super("games");

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getT1_id() {
        return t1_id;
    }

    public void setT1_id(String t1_id) {
        this.t1_id = t1_id;
    }

    public String getT2_id() {
        return t2_id;
    }

    public void setT2_id(String t2_id) {
        this.t2_id = t2_id;
    }

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }


}
