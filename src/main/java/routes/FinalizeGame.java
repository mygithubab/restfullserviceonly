package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.Bet;
import models.Game;
import models.GameResponse;
import models.User;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game/finalize")

public class FinalizeGame {



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response finalizeGame(String request){
        System.out.println("re : -- r1");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        System.out.println("re : -- r2");
        GameResponse requestGame = gson.fromJson(request,GameResponse.class);
        System.out.println("re : -- r3");
        Game game  = new Game();
        BasicDBObject query = new BasicDBObject();
        query.put("_id" , new ObjectId(requestGame.getGame_id()));
        System.out.println("re : -- r4");
        DBObject actualGameDb= game.getOneDocuments(query);
        Game actualGame = gson.fromJson(JSON.serialize(actualGameDb),Game.class);
        System.out.println("re : -- r5");
        System.out.println("actual game od :" + JSON.serialize(actualGameDb));
        System.out.println("re : -- r6");
        Bet bet = new Bet();
        BasicDBObject queryBet = new BasicDBObject();
        queryBet.put("game_id", actualGame.get_id().get$oid());
        System.out.println("re : -- r7");
        DBCursor betCursor = bet.getDocuments(queryBet);

        Bet[] actualBet = gson.fromJson(JSON.serialize(betCursor) , Bet[].class);
        System.out.println("re : -- r8");
        String response = "";
        for(Bet bt :actualBet){
            //response += (bt.get_id().get$oid()) + " : ";
            BasicDBObject singleBet = new BasicDBObject();
            singleBet.put("_id",new ObjectId(bt.user_id));

            DBObject userBet  = bet.getOneDocuments(singleBet);
            User better = gson.fromJson(JSON.serialize(userBet),User.class);

            if(requestGame.team1 > requestGame.team2 && bt.choice == 0){
                //@todo team1 wins and user bet on it

                User user = new User();
                BasicDBObject queryD = new BasicDBObject();
                queryD.put("_id" ,new ObjectId(better.get_id().get$oid()));


                BasicDBObject result = new BasicDBObject();
                result.put("_id" ,new ObjectId(better.get_id().get$oid()));
                result.put("id" ,better.get_id());
                result.put("admin" ,better.isAdmin());
                result.put("ammount" ,better.ammount);
                result.put("name" ,better.name);
                result.put("password" ,better.password);

                //updates database
                user.updateDocument(queryD , result);

            }
            else if(requestGame.team1 == requestGame.team2){
                //@todo draw
            }
            else{
                //@todo team2 wins
            }

        }
        System.out.println("re : -- r9");

        return Response.ok(JSON.serialize(betCursor)).header("Access-Control-Allow-Origin", "*").build();

    }
}
