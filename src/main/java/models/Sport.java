package models;


import controller.DatabaseController;

import java.util.List;

public class Sport  extends DatabaseController {

    public String id;
    public String name;
    public List<Game> games;

    public Sport() {
        super("sports");
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


}