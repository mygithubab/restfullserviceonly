package models;



import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import controller.DatabaseController;

import java.util.ArrayList;

public class Team implements DatabaseController {
    public String id;
    public String name;
    public String sport_id;

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

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
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
