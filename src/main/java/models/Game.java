package models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import controller.DatabaseController;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Game extends DatabaseController implements Serializable{
    @Expose
    public String id;
    @Expose
    public String sport_key;
    @Expose
    public String sport_nice;
    @Expose
    public ArrayList<String> teams;
    @Expose
    public long commence_time;
    @Expose
    public String home_team;
    @Expose
    public int sites_count;
    @Expose
    public Site[] sites;

    @Expose
    public boolean active;

    @Expose
    public ArrayList<Double> averageOdd;

    OkHttpClient client = new OkHttpClient();
    String apiKey= "b82fb87021bac76f7c6106de85a90d48";
    public Game() {
        super("games");

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Site[] getSites() {
        return sites;
    }

    public void setSites(Site[] sites) {
        this.sites = sites;
    }

    public ArrayList<Double> getAverageOdd() {
        return averageOdd;
    }

    public void setAverageOdd(ArrayList<Double> averageOdd) {
        this.averageOdd = averageOdd;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ResponseGame getGames(String sport_key){
        String result = "";
        Gson gson = new Gson();
        try {
            System.out.println("https://api.the-odds-api.com/v3/odds/?apiKey="+apiKey+"&sport="+sport_key+"&region=uk&mkt=h2h");
            result  = getMethod("https://api.the-odds-api.com/v3/odds/?apiKey="+apiKey+"&sport="+sport_key+"&region=uk&mkt=h2h");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseGame rs =  gson.fromJson(result, ResponseGame.class);
        return rs;
    }

    public ResponseGame getUpcommingGames(){
        OkHttpClient client = new OkHttpClient();
        String result = "";
        Gson gson = new Gson();
        try {
            System.out.println("https://api.the-odds-api.com/v3/odds/?sport=upcoming&region=uk&mkt=h2h&apiKey="+apiKey+"");
            result  = getMethod("https://api.the-odds-api.com/v3/odds/?sport=upcoming&region=uk&mkt=h2h&apiKey="+apiKey+"");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseGame rs =  gson.fromJson(result, ResponseGame.class);
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