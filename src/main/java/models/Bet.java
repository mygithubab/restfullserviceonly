package models;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import controller.DatabaseController;
import java.util.ArrayList;

public class Bet implements DatabaseController {
    public String id;
    public String user_id;
    public String team_id;
    public String game_id;

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

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
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
