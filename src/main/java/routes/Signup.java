package routes;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.User;

@Path("/signup")
public class Signup {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String Signup(String user) {
        Gson gson = new Gson();
        User user1 = gson.fromJson(user, User.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("name", user1.getName());
//        bs.put("password", user1.getPassword());
        DBObject dbCursor = user1.getOneDocuments(bs);
        if(dbCursor == null){
            bs.put("password" ,user1.getPassword());
            bs.put("ammount" ,0);
            user1.insertDocument(bs);
            return    "{\"signup\":\"succesfully signedup\"}";
        }

        String response = JSON.serialize(dbCursor);
        User user2 = gson.fromJson(response, User.class);

        return    "{\"signup\":\"user already exists\"}";


    }
}