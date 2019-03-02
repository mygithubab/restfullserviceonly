package models;

import java.util.ArrayList;

public class ResponseGame {
    public boolean success;
    public ArrayList<Game> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Game> getData() {
        return data;
    }

    public void setData(ArrayList<Game> data) {
        this.data = data;
    }
}
