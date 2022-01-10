package com.pro.takemedrive.Models;

public class FullTrip {

    int id;
    String driver;
    String car;
    String start_place;
    String end_place;
    String the_date;
    String the_time;
    int duration;
    int price;
    int seats;
    int regs;

    public FullTrip() {
    }

    public FullTrip(int id, String driver, String car, String start_place, String end_place, String the_date, String the_time, int duration, int price, int seats,int regs) {
        this.id = id;
        this.driver = driver;
        this.car = car;
        this.start_place = start_place;
        this.end_place = end_place;
        this.the_date = the_date;
        this.the_time = the_time;
        this.duration = duration;
        this.price = price;
        this.seats = seats;
        this.regs = regs;
    }

    public int getRegs() {
        return regs;
    }

    public void setRegs(int regs) {
        this.regs = regs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getStart_place() {
        return start_place;
    }

    public void setStart_place(String start_place) {
        this.start_place = start_place;
    }

    public String getEnd_place() {
        return end_place;
    }

    public void setEnd_place(String end_place) {
        this.end_place = end_place;
    }

    public String getThe_date() {
        return the_date;
    }

    public void setThe_date(String the_date) {
        this.the_date = the_date;
    }

    public String getThe_time() {
        return the_time;
    }

    public void setThe_time(String the_time) {
        this.the_time = the_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}
