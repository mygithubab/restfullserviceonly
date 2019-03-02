package controller;

import com.mongodb.*;

import java.io.Serializable;

public class DatabaseController  implements Serializable{
    public DatabaseConnection instance;
    public DBCollection collection;

    public DatabaseController(String dbName) {
        instance = DatabaseConnection.getInstance();
        DB database = instance.getDatabase();
        collection = database.getCollection(dbName);

    }

    public boolean insertDocument(BasicDBObject document) {

        if (collection.insert(document) != null) {
            return true;
        }
        return false;

    }

    public DBCollection getCollection() {

        return collection;

    }

    public boolean updateDocument(BasicDBObject searchQuery, BasicDBObject document) {

        if (collection.update(searchQuery, document) != null) {
            return true;
        }

        return false;
    }

    public boolean deleteDocument(DBObject document) {

        if (collection.remove(document) != null) {
            return true;
        }

        return false;
    }

    public DBCursor getDocuments(BasicDBObject query) {
        DBCursor cursor = collection.find(query);
        return cursor;
    }

    public DBCursor getAllDocuments() {
        DBCursor cursor = collection.find();
        return cursor;
    }

    public DBObject getOneDocuments(BasicDBObject query) {
        DBObject cursor = collection.findOne(query);
        return cursor;
    }
}
