package controller;

import com.mongodb.*;


import java.net.UnknownHostException;
import java.util.List;

public class DatabaseConnection {
    public DatabaseConnection () {
        try {
           MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            DB database = mongoClient.getDB("betting");
            System.out.println("connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
