package models;


import com.google.gson.annotations.Expose;
import controller.DatabaseController;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class User extends DatabaseController implements Serializable{
    @Expose
    public String id;
    @Expose
    public BDObjectID _id;
    @Expose
    public String name;
    @Expose
    public String password;
    @Expose
    public double ammount = 0.0;
    @Expose
    public boolean admin;


    public User() {
        super("users");
    }

    public User(String dbName, String id, String name, String password, double ammount) {
        super(dbName);
        this.id = id;
        this.name = name;
        this.password = password;
        this.ammount = ammount;
    }

    public BDObjectID get_id() {
        return _id;
    }

    public void set_id(BDObjectID _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
