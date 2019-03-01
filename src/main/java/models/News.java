package models;



import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import controller.DatabaseController;

import java.util.ArrayList;

public class News implements DatabaseController {
    public String id;
    public String title;
    public String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
