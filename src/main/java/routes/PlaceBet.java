package routes;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.Bet;
import models.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bet")
public class PlaceBet {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public String bet(String betData) {
        Gson gson = new Gson();
        Bet bet = gson.fromJson(betData, Bet.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("user_id" ,bet.user_id);
        bs.put("game_id" ,bet.game_id);
        bs.put("teams" ,bet.teams);
        bs.put("price" ,bet.price);


        if(bet.insertDocument(bs)){
            return    "{\"bet\":\"betted success fully\"}";
        }

        return    "{\"bet\":\"something went wrong\"}";


    }
}
