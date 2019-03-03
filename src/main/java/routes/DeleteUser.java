package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.User;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/user/delete")
public class DeleteUser {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("id") String user_id)  {
        User u = new User();
        BasicDBObject userQuery = new BasicDBObject();
        userQuery.put("_id", new ObjectId(user_id));
        boolean dbObject = u.deleteDocument(userQuery);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        Dresponce dresponce = gson.fromJson(gson.toJson(JSON.parse("{\"count\": 1 }")) ,Dresponce.class);
        if(dbObject){

            return Response.ok(gson.toJson(dresponce)).header("Access-Control-Allow-Origin", "*").build();
        }
        Dresponce dresponce1 = gson.fromJson(gson.toJson(JSON.parse("{\"count\": 1 }")) ,Dresponce.class);
        return Response.ok(gson.toJson(dresponce1)).header("Access-Control-Allow-Origin", "*").build();

    }



}

class Dresponce{
    @Expose
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}