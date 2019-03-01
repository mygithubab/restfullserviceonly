package controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import models.ArgumetParse;


import java.util.ArrayList;

public interface DatabaseController {
    public boolean insertDocument(BasicDBObject document);
    public boolean updateDocument(BasicDBObject document);
    public boolean deleteDocument(String id);
    public DBCursor getDocuments(ArrayList<ArgumetParse> argument);
}
