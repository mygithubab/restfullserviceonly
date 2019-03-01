package models;



import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import controller.DatabaseController;

import java.util.ArrayList;
import java.util.List;

public class Sport  implements DatabaseController {

    public String id;
    public String name;
    public List<Game> games;

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


    public boolean insertDocument(BasicDBObject document) {
        return false;
    }

    public boolean updateDocument(BasicDBObject document) {
        return false;
    }

    public boolean deleteDocument(String id) {
        return false;
    }

    public DBCursor getDocuments(ArrayList<ArgumetParse> argument) {
        return null;
    }
}
