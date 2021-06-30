package com.java_backend.apiserver.model;

import java.util.Objects;

public class Activity {
    private String objID,category,name,userID;

    public Activity() {
    }
    public Activity(String category, String name,String userID) {
        this.category = category;
        this.name = name;
        this.userID=userID;
    }
    public Activity(String objID, String category, String name,String userID) {
        this.objID = objID;
        this.category = category;
        this.name = name;
        this.userID=userID;
    }

    public String getObjID() {
        return this.objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Activity objID(String objID) {
        setObjID(objID);
        return this;
    }

    public Activity category(String category) {
        setCategory(category);
        return this;
    }

    public Activity name(String name) {
        setName(name);
        return this;
    }

    public Activity userID(String userID) {
        setUserID(userID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Activity)) {
            return false;
        }
        Activity activity = (Activity) o;
        return Objects.equals(objID, activity.objID) && Objects.equals(category, activity.category) && Objects.equals(name, activity.name) && Objects.equals(userID, activity.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objID, category, name, userID);
    }

    @Override
    public String toString() {
        return "{" +
            " objID='" + getObjID() + "'" +
            ", category='" + getCategory() + "'" +
            ", name='" + getName() + "'" +
            ", userID='" + getUserID() + "'" +
            "}";
    }

  

}
