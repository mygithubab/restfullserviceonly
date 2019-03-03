package routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import models.Bet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bets")
public class GetBets {
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getBets() {
//        Bet bt = new Bet();
//
//        DBCursor dbCursor = bt.getAllDocuments();
//
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
//        Bet[] myJSONObject = gson.fromJson(JSON.serialize(dbCursor), Bet[].class);
//
//
//        return JSON.serialize(dbCursor);
//    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBetByUserId(@QueryParam("user_id") String user_id) {
        Bet bt = new Bet();

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("user_id" , user_id);

        DBCursor dbCursor;
        if(user_id ==null){
            dbCursor = bt.getAllDocuments();
        }
        else {
            dbCursor = bt.getDocuments(dbObject);
        }


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        Bet[] myJSONObject = gson.fromJson(JSON.serialize(dbCursor), Bet[].class);

        return Response.ok(JSON.serialize(dbCursor)).header("Access-Control-Allow-Origin", "*").build();

    }
}