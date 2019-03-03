package routes;


import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import models.Bet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bet")
public class PlaceBet {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response bet(String betData) {
        Gson gson = new Gson();
        Bet bet = gson.fromJson(betData, Bet.class);
        BasicDBObject bs = new BasicDBObject();
        bs.put("user_id" ,bet.user_id);
        bs.put("game_id" ,bet.game_id);
        bs.put("sport_key" ,bet.sport_key);
        bs.put("teams" ,bet.teams);
        bs.put("odd" ,bet.odd);
        bs.put("choice" ,bet.choice);
        bs.put("price" ,bet.price);





        if(bet.insertDocument(bs)){
            return Response.ok(gson.toJson(JSON.parse("{\"bet\":\"betted success fully\"}"))).header("Access-Control-Allow-Origin", "*").build();

        }
        return Response.ok(gson.toJson(JSON.parse("{\"bet\":\"something went wrong\"}"))).header("Access-Control-Allow-Origin", "*").build();


    }
}
