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
import java.util.ArrayList;

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
                ChechAndDecide(better , bt ,"add");
                updatebet(requestGame ,bt);

            }
            else if(requestGame.team1 == requestGame.team2 && bt.choice == 1){
                //@todo draw
                ChechAndDecide(better , bt ,"add");
                updatebet(requestGame ,bt);

            }
            else if(requestGame.team1 < requestGame.team2 && bt.choice == 2){
                //@todo team2 wins
                ChechAndDecide(better , bt ,"add");
                updatebet(requestGame ,bt);
            }
            else {
                ChechAndDecide(better , bt ,"sub");
                int[] data = new int[2];
                data[0] = requestGame.team1;
                data[1] = requestGame.team2;

                BasicDBObject tempbt = new BasicDBObject();

                tempbt.put("user_id" , bt.user_id);
                tempbt.put("odd" , bt.odd);
                tempbt.put("price" , bt.price);
                tempbt.put("teams" , bt.teams);
                tempbt.put("game_id" , bt.game_id);
                tempbt.put("choice" , bt.choice);
                tempbt.put("sport_key" , bt.sport_key);
                tempbt.put("_id" , new ObjectId(bt._id.get$oid()));
                tempbt.put("id" , bt.id);
                tempbt.put("score" , data);
                tempbt.put("status" , "loss");


                BasicDBObject tempq = new BasicDBObject();
                tempq.put("_id" , new ObjectId(bt._id.get$oid()));

                bt.updateDocument(tempq ,tempbt);
            }

        }
        System.out.println("re : -- r9");

        BasicDBObject gquery = new BasicDBObject();
        gquery.put("_id" , new ObjectId(actualGame.get_id().get$oid()));

        BasicDBObject gdata = new BasicDBObject();
        gdata.put("_id",new ObjectId(actualGame.get_id().get$oid() ));
        gdata.put("commence_time",actualGame.commence_time);
        gdata.put("sites",actualGame.sites);
        gdata.put("sport_nice",actualGame.sport_nice);
        gdata.put("sport_key",actualGame.sport_key);
        gdata.put("teams",actualGame.teams);
        gdata.put("home_team",actualGame.home_team);
        gdata.put("averageOdd",actualGame.averageOdd);
        gdata.put("active",false);

        game.updateDocument(gquery ,gdata);


        return Response.ok(gson.toJson(JSON.parse("{\"finalize\":\"true\"}"))).header("Access-Control-Allow-Origin", "*").build();

    }

    public void updatebet(GameResponse requestGame , Bet bt){

        int[] data = new int[2];
        data[0] = requestGame.team1;
        data[1] = requestGame.team2;

        BasicDBObject tempbt = new BasicDBObject();

        tempbt.put("user_id" , bt.user_id);
        tempbt.put("odd" , bt.odd);
        tempbt.put("price" , bt.price);
        tempbt.put("teams" , bt.teams);
        tempbt.put("game_id" , bt.game_id);
        tempbt.put("choice" , bt.choice);
        tempbt.put("sport_key" , bt.sport_key);
        tempbt.put("_id" , new ObjectId(bt._id.get$oid()));
        tempbt.put("id" , bt.id);
        tempbt.put("score" , data);
        tempbt.put("active" , false);
        tempbt.put("status" , "win");


        BasicDBObject tempq = new BasicDBObject();
        tempq.put("_id" , new ObjectId(bt._id.get$oid()));

        bt.updateDocument(tempq ,tempbt);
    }

        public static void ChechAndDecide(User better , Bet bt , String add){

            User user = new User();
            BasicDBObject queryD = new BasicDBObject();
            queryD.put("_id" ,new ObjectId(better.get_id().get$oid()));

            double finalResult = 0;
            if("add".equals(add)){
                finalResult = better.ammount + bt.price;
            }

            if("sub".equals(add)){
                finalResult = better.ammount - bt.price;
            }



            BasicDBObject result = new BasicDBObject();
            result.put("_id" ,new ObjectId(better.get_id().get$oid()));
            result.put("id" ,better.get_id());
            result.put("admin" ,better.isAdmin());
            result.put("ammount" ,finalResult);
            result.put("name" ,better.name);
            result.put("password" ,better.password);

            //updates database
            user.updateDocument(queryD , result);
        }

}
