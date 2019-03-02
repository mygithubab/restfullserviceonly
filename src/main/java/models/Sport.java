package models;


import com.google.gson.Gson;
import controller.DatabaseController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sport  extends DatabaseController {

    OkHttpClient client = new OkHttpClient();
    public String id;
    public Boolean key;
    public String group;
    public String detail;
    public String title;


    public Sport() {
        super("sports");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getKey() {
        return key;
    }

    public void setKey(Boolean key) {
        this.key = key;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ResponseSport getSports(){
        String result = "";
        Gson gson = new Gson();
        try {
            result  = getMethod("https://api.the-odds-api.com/v3/sports/?apiKey=b82fb87021bac76f7c6106de85a90d48");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        ResponseSport rs =  gson.fromJson(result, ResponseSport.class);
        return rs;
    }



    String getMethod(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("response http : "+ response.body().string());
            return response.body().string();
        }
        catch (Exception e){}
        return "";
    }

}