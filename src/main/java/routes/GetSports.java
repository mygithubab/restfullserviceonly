package routes;

import com.google.gson.Gson;
import com.mongodb.DBCursor;
import models.ResponseSport;
import models.Sport;
import models.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/sports")
public class GetSports {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSports() {
        Sport sport = new Sport();
        ResponseSport responseSport=sport.getSports();
        if(responseSport == null){
            System.out.println("null sport");
        }
        ArrayList<Sport> sports =responseSport.data;
        Gson gson = new Gson();

        return  gson.toJson(sports);
    }
}
