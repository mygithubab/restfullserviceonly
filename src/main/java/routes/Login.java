package routes;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.User;

@Path("/login")
public class Login {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response Login(String user) {
        Gson gson = new Gson();
        User user1 = gson.fromJson(user, User.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("name", user1.getName());
        bs.put("password", user1.getPassword());
        DBObject dbCursor = user1.getOneDocuments(bs);

        if (dbCursor == null) {
            return Response.ok(gson.toJson(JSON.parse("{\"login\":\"false\"}"))).header("Access-Control-Allow-Origin", "*").build();

        }
        String response = JSON.serialize(dbCursor);
        User user2 = gson.fromJson(response, User.class);
        // return user1.getName() + ":" + user2.getName();
        if (user1.getName() .equalsIgnoreCase( user2.getName())) {

            return Response.ok(gson.toJson(JSON.parse("{\"login\":\""+ user2._id.get$oid() +"\" , \"admin\":\""+ user2.isAdmin() +"\" , \"name\":\""+ user2.name +"\" , \"ammount\":\""+ user2.getAmmount() +"\"}"))).header("Access-Control-Allow-Origin", "*").build();
        }
        return Response.ok(gson.toJson(JSON.parse("{\"login\":\"false\"}"))).header("Access-Control-Allow-Origin", "*").build();

    }
}