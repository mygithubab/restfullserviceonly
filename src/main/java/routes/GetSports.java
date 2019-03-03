package routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.ResponseSport;
import models.Sport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sports")
public class GetSports {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSports() {
        Sport sport = new Sport();
        ResponseSport responseSport=sport.getSports();
        if(responseSport == null){
            System.out.println("null sport");
        }
        Sport[] sports = responseSport.data;

        if(sports == null){
            System.out.println("null array");
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();


        if(gson.toJson(sports) == null){
            System.out.println("null return");
        }
        return Response.ok(gson.toJson(sports)).header("Access-Control-Allow-Origin", "*").build();

    }
}
