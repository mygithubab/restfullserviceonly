package routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.Game;
import models.User;
import org.bson.types.ObjectId;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Path("/game")
public class GetSpecificGame {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("game_id") String game_id) {
        Game u = new Game();
        BasicDBObject basicDBObject =new BasicDBObject();
        basicDBObject.put("_id", new ObjectId(game_id ));

        DBObject dbCursor = u.getOneDocuments(basicDBObject);


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        Game myJSONObject = gson.fromJson(JSON.serialize(dbCursor), Game.class);

        myJSONObject.id = myJSONObject.get_id().get$oid();
        ArrayList<Double> average=  myJSONObject.getAverageOdd();

        ArrayList<Double> newAverage= new ArrayList<Double>();

        System.out.println("size of array" + average.size());

        if(average.size() == 2 ){
            int count =0;
            for(Double odd : average){
                if(count == 0){
                    newAverage.add(round(odd,2));
                }
                else if(count == 1){
                    newAverage.add(round(0,2));
                    newAverage.add(round(odd,2));
                }

                count++;
            }
        }

        if(average.size() == 3 ){
            int count =0;
            for(Double odd : average){
                newAverage.add(round(odd,2));
                count++;
            }
        }

        myJSONObject.averageOdd =newAverage;

        return Response.ok(gson.toJson(myJSONObject)).header("Access-Control-Allow-Origin", "*").build();

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}