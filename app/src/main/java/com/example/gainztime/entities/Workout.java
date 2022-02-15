package com.example.gainztime.entities;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.gainztime.managers.ExerciseManager;
import com.example.gainztime.managers.WorkoutManager;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Workout {
    int id, idPhotoAssembler;
    int nbJourPlanExecution;
    String goal, type;
    String dateStartWorkout;
    String dateEndWorkout;
    String strWorkoutName;  //endurance 1 avec date -- force 2 avec date

    public Workout(@Nullable Integer id, @Nullable Integer idPhotoAssembler, String strWorkoutName, String type, String goal, int nbJourPlanExecution, @Nullable String startDate, @Nullable String endDate) {
        Date date = getCurrentDate();
        if (id != null) {
            this.id = id;
        }
        this.idPhotoAssembler = idPhotoAssembler;
        this.strWorkoutName = strWorkoutName;
        this.type = type;
        this.goal = goal;
        this.nbJourPlanExecution = nbJourPlanExecution;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.dateStartWorkout = dateFormat.format(date);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
        this.dateEndWorkout = dateFormat2.format(setLastWorkoutDayDate(date, nbJourPlanExecution));
    }

    public String workoutToJson() {
        Gson json = new Gson();
        return json.toJson(this);
    }

    //on va creer une date a la creation du workout (constructeur)
    public Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    //selon le nombre de jour entrer par le user
    public Date setLastWorkoutDayDate(Date startDate, int nbDay) {
        long expiremilis = 60000l; // 1 minute
        int minute = 60000;//1minute en ms
        int heure = 3600000;// 1heure en ms
        int day = 86400000;// 1days en ms
        // Expires on one minute from the date object date
        Date expireDate = new Date(startDate.getTime() + (day * nbDay));
        return expireDate;
    }

    public int getNbJourPlanExecution() {
        return nbJourPlanExecution;
    }

    public String getGoal() {
        return goal;
    }

    public String getType() {
        return type;
    }

    public String getDateStartWorkout() {
        return dateStartWorkout;
    }

    public String getDateEndWorkout() {
        return dateEndWorkout;
    }

    public int getId() {
        return id;
    }

    public String getStrWorkoutName() {
        return strWorkoutName;
    }
}
