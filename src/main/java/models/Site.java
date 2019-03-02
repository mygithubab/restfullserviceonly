package models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Site implements Serializable {
    @Expose
    public String site_key;
    @Expose
    public String site_nice;
    @Expose
    public long last_update;
    @Expose
    public Odd odds;


    public Site(String site_key, String site_nice, long last_update, Odd odds) {
        this.site_key = site_key;
        this.site_nice = site_nice;
        this.last_update = last_update;
        this.odds = odds;
    }

    public String getSite_key() {
        return site_key;
    }

    public void setSite_key(String site_key) {
        this.site_key = site_key;
    }

    public String getSite_nice() {
        return site_nice;
    }

    public void setSite_nice(String site_nice) {
        this.site_nice = site_nice;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public Odd getOdds() {
        return odds;
    }

    public void setOdds(Odd odds) {
        this.odds = odds;
    }
}



