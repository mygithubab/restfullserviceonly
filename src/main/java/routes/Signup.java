package routes;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.User;

@Path("/signup")
public class Signup {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response Signup(String user) {
        Gson gson = new Gson();
        User user1 = gson.fromJson(user, User.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("name", user1.getName());
//        bs.put("password", user1.getPassword());
        DBObject dbCursor = user1.getOneDocuments(bs);
        if(dbCursor == null){
            bs.put("password" ,user1.getPassword());
            bs.put("ammount" ,user1.ammount);
            bs.put("admin" ,user1.isAdmin());
            DBCollection collection = user1.getCollection();
            user1.insertDocument(bs);
            DBObject registeredUser = user1.getOneDocuments(bs);
            User lastUser = gson.fromJson(JSON.serialize(registeredUser), User.class);
            lastUser.password = "";
            lastUser.id = lastUser._id.get$oid();
           // String result = gson.toJson(lastUser);
            return Response.ok(gson.toJson(JSON.parse("{\"signup\":\""+lastUser.id +"\" , \"admin\":\""+lastUser.getAmmount() +"\" , \"ammount\":\""+lastUser.getAmmount() +"\"}"))).header("Access-Control-Allow-Origin", "*").build();


        }

        String response = JSON.serialize(dbCursor);
        User user2 = gson.fromJson(response, User.class);
        return Response.ok(gson.toJson(JSON.parse("{\"signup\":\"user already exists\"}"))).header("Access-Control-Allow-Origin", "*").build();

    }
}