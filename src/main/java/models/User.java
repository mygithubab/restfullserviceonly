package models;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import controller.DatabaseController;

import java.util.ArrayList;

public class User implements DatabaseController {
    public String id;
    public String name;
    public String password;
    public String ammount;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
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
