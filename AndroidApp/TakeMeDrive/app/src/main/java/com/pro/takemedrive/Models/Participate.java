package com.pro.takemedrive.Models;

public class Participate {
    int id;
    int trip_id;
    int user_id;

    public Participate() {
    }

    public Participate(int id, int trip_id, int user_id) {
        this.id = id;
        this.trip_id = trip_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
