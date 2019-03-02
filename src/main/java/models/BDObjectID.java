package models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class BDObjectID implements Serializable{
    @Expose
    public String $oid;

    public String get$oid() {
        return $oid;
    }

    public void set$oid(String $oid) {
        this.$oid = $oid;
    }
}
