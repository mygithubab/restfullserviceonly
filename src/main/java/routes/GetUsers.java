package routes;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/users")
public class GetUsers {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        User u = new User();

        DBCursor dbCursor = u.getAllDocuments();
//
//        ArrayList<User> users = new ArrayList<User>();
//
//        while(dbCursor.hasNext())
//        {
//            DBObject object = dbCursor.next();
//            users.add((User)object);
//
//        }
//        Gson gson = new Gson();

        return  JSON.serialize(dbCursor);
    }
}