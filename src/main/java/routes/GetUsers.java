package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        User[] myJSONObject = gson.fromJson(JSON.serialize(dbCursor), User[].class);

        for(User use : myJSONObject){
            use.password = null;
        }
        return  gson.toJson(myJSONObject);
    }
}