package com.example.gainztime.entities;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Photo {
    int id;
    String path, takedDate, destination;

    public Photo(@Nullable Integer id, String path, @Nullable String takedDate, String destination) {
        this.id = id;
        this.path = path;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.takedDate = dateFormat.format(getCurrentDate());
        this.destination = destination; //get photo where destination = Exercice

    }

    public Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getTakedDate() {
        return takedDate;
    }

    public String getDestination() {
        return destination;
    }
}
