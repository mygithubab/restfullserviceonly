package models;


import controller.DatabaseController;
import org.bson.types.ObjectId;

import java.io.Serializable;

public class User extends DatabaseController implements Serializable{
    public String id;
    public BDObjectID _id;
    public String name;
    public String password;
    public String ammount;

    public User() {
        super("users");
    }

    public User(String dbName, String id, String name, String password, String ammount) {
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

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }


}
