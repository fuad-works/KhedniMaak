package com.pro.takemedrive.Models;

public class User {
    int id;
    String user_name;
    int active;
    int user_type;

    public User() {
    }

    public User(int id, String user_name, int active, int user_type) {
        this.id = id;
        this.user_name = user_name;
        this.user_type = user_type;
        this.active = active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
