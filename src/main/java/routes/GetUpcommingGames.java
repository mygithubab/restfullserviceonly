package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Game;
import models.ResponseGame;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/games")
public class GetUpcommingGames {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUpcommingGames() {
        Game game = new Game();
        ResponseGame responseGame = game.getUpcommingGames();
        if (responseGame == null) {
            System.out.println("null response");
        }
        Game[] games = responseGame.data;
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(games);
    }
}