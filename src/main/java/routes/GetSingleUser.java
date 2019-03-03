package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.User;
import org.bson.types.ObjectId;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class GetSingleUser {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("user_id") String user_id) {
        User u = new User();
        BasicDBObject basicDBObject =new BasicDBObject();
        basicDBObject.put("_id", new ObjectId(user_id));

        DBObject dbCursor = u.getOneDocuments(basicDBObject);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        User myJSONObject = gson.fromJson(JSON.serialize(dbCursor), User.class);

        myJSONObject.id = myJSONObject.get_id().get$oid();


        myJSONObject.password = null;
        return Response.ok(gson.toJson(myJSONObject)).header("Access-Control-Allow-Origin", "*").build();

    }


}