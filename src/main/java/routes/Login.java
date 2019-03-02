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

@Path("/login")
public class Login {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String Login(String user) {
        Gson gson = new Gson();
        User user1 = gson.fromJson(user, User.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("name", user1.getName());
        bs.put("password", user1.getPassword());
        DBObject dbCursor = user1.getOneDocuments(bs);

        if (dbCursor == null) {
            return "{\"login\":\"false\"}";
        }
        String response = JSON.serialize(dbCursor);
        User user2 = gson.fromJson(response, User.class);
        // return user1.getName() + ":" + user2.getName();
        if (user1.getName() .equalsIgnoreCase( user2.getName())) {
            return "{\"login\":\""+ user2._id.get$oid() +"\" , \"admin\":\""+
                    user2.isAdmin() +"\" , \"ammount\":\""+ user2.getAmmount() +"\"}";
        }
        return "{\"login\":\"false\"}";
    }
}