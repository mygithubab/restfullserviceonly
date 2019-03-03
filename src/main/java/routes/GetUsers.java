package routes;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import models.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/users")
public class GetUsers implements ContainerResponseFilter{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser() {
        User u = new User();

        DBCursor dbCursor = u.getAllDocuments();


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        User[] myJSONObject = gson.fromJson(JSON.serialize(dbCursor), User[].class);
        for(User us :myJSONObject){
            us.id = us.get_id().get$oid();
        }

        for(User use : myJSONObject){
            use.password = null;
        }
        return Response.ok(gson.toJson(myJSONObject)).header("Access-Control-Allow-Origin", "*").build();

    }

    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext cres) throws IOException {
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}