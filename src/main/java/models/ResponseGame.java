package models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseGame implements Serializable {
    @Expose
    public boolean success;
    @Expose
    public Game[] data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Game[] getData() {
        return data;
    }

    public void setData(Game[] data) {
        this.data = data;
    }
}
