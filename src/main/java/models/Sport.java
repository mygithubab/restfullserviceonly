package models;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import controller.DatabaseController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sport  extends DatabaseController implements Serializable {


    @Expose
    public String id;
    @Expose
    public String key;
    @Expose
    public boolean active;
    @Expose
    public String group;
    @Expose
    public String detail;
    @Expose
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        try {
            result  = getMethod("https://api.the-odds-api.com/v3/sports/?apiKey=b82fb87021bac76f7c6106de85a90d48");
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("sdsdsds");
        ResponseSport rs =  gson.fromJson(result , ResponseSport.class);
        if(rs == null){
            System.out.println("----- before");
        }
        return rs;
    }



    String getMethod(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            //System.out.println( response.body().string());
            return response.body().string();
        }
        catch (Exception e){}
        return "";
    }

}