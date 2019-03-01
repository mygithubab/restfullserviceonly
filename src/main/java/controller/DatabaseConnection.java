package controller;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DatabaseConnection {
    public static DatabaseConnection instance;
    private DB database;

    public DatabaseConnection() {
        try {
            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            database = mongoClient.getDB("betting");
            System.out.println("mongo db connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }


    public DB getDatabase() {
        return database;
    }
}
