package models;


import controller.DatabaseController;

public class History extends DatabaseController {
    public String id;
    public String game_id;
    public String team1_id;
    public String team2_id;
    public String result_t1;
    public String result_t2;

    public History() {
        super("history");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(String team1_id) {
        this.team1_id = team1_id;
    }

    public String getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(String team2_id) {
        this.team2_id = team2_id;
    }

    public String getResult_t1() {
        return result_t1;
    }

    public void setResult_t1(String result_t1) {
        this.result_t1 = result_t1;
    }

    public String getResult_t2() {
        return result_t2;
    }

    public void setResult_t2(String result_t2) {
        this.result_t2 = result_t2;
    }


}
