package com.example.gainztime.entities;

import android.media.Image;

import androidx.annotation.Nullable;

public class Exercice {
    int id, idPhotoAssembler;
    int currentMaxWeight, firstMaxWeight;
    String nameExercice, imgPath, descriptionProcedure;
    //exercie executer et mettre a true quand cocher
    int isExecute;

    public Exercice(int id, String nameExercice, String imgPath,
                    @Nullable Integer isExecute, @Nullable Integer firstMaxWeight,
                    @Nullable Integer currentMaxWeight, @Nullable Integer idPhotoAssembler) {
        this.id = id;
        this.idPhotoAssembler = idPhotoAssembler;
        this.nameExercice = nameExercice;
        this.imgPath = imgPath;
        this.descriptionProcedure = descriptionProcedure;
        if (isExecute != null) {
            this.isExecute = isExecute;
        }
        if (currentMaxWeight != null) {
            this.currentMaxWeight = currentMaxWeight;
        }
        if (firstMaxWeight != null) {
            this.firstMaxWeight = firstMaxWeight;
        }

        //if is execute = 1 true   = 0 false
    }


    public void setIdPhotoAssembler(int idPhotoAssembler) {
        this.idPhotoAssembler = idPhotoAssembler;
    }

    public int getIdPhotoAssembler() {
        return idPhotoAssembler;
    }

    public int getCurrentMaxWeight() {
        return currentMaxWeight;
    }

    public void setCurrentMaxWeight(int currentMaxWeight) {
        this.currentMaxWeight = currentMaxWeight;
    }

    public int getFirstMaxWeight() {
        return firstMaxWeight;
    }

    public void setFirstMaxWeight(int firstMaxWeight) {
        this.firstMaxWeight = firstMaxWeight;
    }

    public int getId() {
        return id;
    }

    public String getNameExercice() {
        return nameExercice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getDescriptionProcedure() {
        return descriptionProcedure;
    }

    public int getExecute() {
        return isExecute;
    }
}
