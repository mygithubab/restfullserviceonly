package routes;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import models.User;
import org.bson.types.ObjectId;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/deposit/add")

public class AddDeposite {


        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)

        public Response deposite(String deposite) {
            Gson gson = new Gson();
            User requestUser = gson.fromJson(deposite, User.class);
            BasicDBObject queryDocument = new BasicDBObject();
            queryDocument.put("_id" , new ObjectId(requestUser.id));

            DBObject userObject = requestUser.getOneDocuments(queryDocument);
            if(userObject == null){
                return Response.ok(gson.toJson(JSON.parse("{\"deposit\":\"user doesn't exist\"}"))).header("Access-Control-Allow-Origin", "*").build();


            }

            String json = JSON.serialize(userObject);

            System.out.println("database" + json);
            User responseUser = gson.fromJson(json, User.class);

            BasicDBObject responseData = new BasicDBObject();
            responseData.put("user_id" ,requestUser.id);
            double result =(double)(responseUser.ammount + requestUser.ammount);
            responseData.put("ammount", result );
            responseData.put("name", responseUser.name );
            responseData.put("password", responseUser.password );

            System.out.println("ammount " + (responseUser.ammount + requestUser.ammount));


            if(requestUser.updateDocument(queryDocument , responseData)){

                return Response.ok(gson.toJson(JSON.parse("{\"deposit\":\""+result+"\"}"))).header("Access-Control-Allow-Origin", "*").build();

            }

            return Response.ok(gson.toJson(JSON.parse("{\"bet\":\"something went wrong\"}"))).header("Access-Control-Allow-Origin", "*").build();



        }


    }

