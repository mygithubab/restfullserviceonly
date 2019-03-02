package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/games")
public class GetGames {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGames(@QueryParam("key") String sport_key) {
        Game game = new Game();
        System.out.println(sport_key +" ----key ");
        ResponseGame responseGame=game.getGames(sport_key);
        if(responseGame == null){
            System.out.println("null response");
        }
        System.out.println("before");
        Game[] games =responseGame.data;

        for(Game gm : games){

            BasicDBObject bs = new BasicDBObject();
            bs.put("commence_time", gm.commence_time);
            bs.put("home_team", gm.home_team);
            DBObject dbCursor = game.getOneDocuments(bs);

            if (dbCursor == null) {


                Odd averageOdd = new Odd();


                Double sum1 = 0.0;
                Double sum2 = 0.0;
                Double sum3 = 0.0;

                int count = 0;
                for(Site site: gm.sites){


                    if(site.getOdds().h2h.size() == 2){
                        sum1 += site.getOdds().h2h.get(0);
                        sum3 += site.getOdds().h2h.get(1);
                    }
                    if(site.getOdds().h2h.size() == 3){
                        sum1 += site.getOdds().h2h.get(0);
                        sum2 += site.getOdds().h2h.get(1);
                        sum3 += site.getOdds().h2h.get(2);
                    }
                    count++;
                }


                averageOdd.h2h.add(sum1/count);
                averageOdd.h2h.add(sum2/count);
                averageOdd.h2h.add(sum3/count);


                //@todo add the new data here
                bs.put("teams" , gm.teams);
                bs.put("sport_key" , gm.sport_key);
                bs.put("sport_nice" , gm.sport_nice);
                bs.put("active" , true);
                bs.put("commence_time" , gm.commence_time);
                bs.put("home_team" , gm.home_team);
                bs.put("averageOdd" , averageOdd.h2h);



                game.insertDocument(bs);


            }

        }

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("sport_key" , sport_key);
        DBCursor dbCursor = game.getDocuments(dbObject);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();

        Game[] game1 = gson.fromJson(JSON.serialize(dbCursor) , Game[].class);
        ArrayList<Game> filtered = new ArrayList<Game>();

        for(Game g : game1){
            if(g.isActive()){
                filtered.add(g);
            }
        }

        Game[] finalResult = filtered.toArray(new Game[0]);
        return  gson.toJson(finalResult);
    }
}