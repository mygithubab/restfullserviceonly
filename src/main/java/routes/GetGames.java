package routes;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.Game;
import models.ResponseGame;
import models.ResponseSport;
import models.Sport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/games/{sport_key}")
public class GetGames {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGames(String sport_key) {
        Game game = new Game();
        ResponseGame responseGame=game.getGames(sport_key);
        if(responseGame == null){
            System.out.println("null response");
        }
        ArrayList<Game> games =responseGame.data;

        for(Game gm : games){
            BasicDBObject bs = new BasicDBObject();
            bs.put("commence_time", gm.commence_time);
            bs.put("home_team", gm.home_team);
            DBObject dbCursor = game.getOneDocuments(bs);

            if (dbCursor == null) {
                //@todo add the new data here
                bs.put("teams" , gm.teams);
                bs.put("sport_key" , gm.sport_key);
                bs.put("sport_nice" , gm.sport_nice);
                bs.put("active" , true);
                bs.put("sites" , gm.sites);
                game.insertDocument(bs);
            }

        }

        Gson gson = new Gson();
        BasicDBObject bs = new BasicDBObject();
        bs.put("sport_key", sport_key);
        DBCursor cursor = game.getDocuments(bs);
        return  JSON.serialize(cursor);
    }
}
