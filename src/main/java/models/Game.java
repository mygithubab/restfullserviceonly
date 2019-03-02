package models;

import com.google.gson.Gson;
import controller.DatabaseController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Game extends DatabaseController {
    public String id;
    public String sport_key;
    public String sport_nice;
    public ArrayList<String> teams;
    public long commence_time;
    public String home_team;
    public int sites_count;
    public ArrayList<Site> sites;

    OkHttpClient client = new OkHttpClient();
    String apiKey= "b82fb87021bac76f7c6106de85a90d48";
    public Game() {
        super("games");

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSport_key() {
        return sport_key;
    }

    public void setSport_key(String sport_key) {
        this.sport_key = sport_key;
    }

    public String getSport_nice() {
        return sport_nice;
    }

    public void setSport_nice(String sport_nice) {
        this.sport_nice = sport_nice;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public long getCommence_time() {
        return commence_time;
    }

    public void setCommence_time(long commence_time) {
        this.commence_time = commence_time;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public int getSites_count() {
        return sites_count;
    }

    public void setSites_count(int sites_count) {
        this.sites_count = sites_count;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }


    public ResponseGame getGames(String sport_key){
        String result = "";
        Gson gson = new Gson();
        try {
            result  = getMethod("https://api.the-odds-api.com/v3/odds/?apiKey="+apiKey+"&sport="+sport_key+"&region=uk&mkt=h2h");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        ResponseGame rs =  gson.fromJson(result, ResponseGame.class);
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