package models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseSport implements Serializable {
    @Expose
    public boolean success;
    @Expose
    public Sport[] data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Sport[] getData() {
        return data;
    }

    public void setData(Sport[] data) {
        this.data = data;
    }
}
