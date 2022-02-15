package com.example.gainztime.entities;

import androidx.annotation.Nullable;

public class PhotoAssemble {
    int id, idPhoto;
    String destination;

    public PhotoAssemble(int id, int idPhoto, @Nullable String destination) {
        this.id = id;
        this.idPhoto = idPhoto;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }

    public int getIdPhoto() {
        return idPhoto;
    }
}
