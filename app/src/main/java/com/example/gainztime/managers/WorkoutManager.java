package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.User;
import com.example.gainztime.entities.Workout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WorkoutManager {

    //    static ArrayList<Exercice> list_exercises = ExerciseManager.getAllExercise();
    static ArrayList<Workout> listWorkout;

    public static void createWorkout(Context ctx, int daysToWorkout, String workoutName, String type, String goal) {
        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        ContentValues cv = new ContentValues();

        cv.put("daystoworkout", daysToWorkout);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        cv.put("startingdate", dateFormat.format(getCurrentDate()));
        DateFormat dateFormatend = new SimpleDateFormat("yyyy/MM/dd");
        cv.put("enddate", dateFormatend.format(setLastWorkoutDayDate(getCurrentDate(), daysToWorkout)));
        cv.put("workoutname", workoutName);
        cv.put("type", type);
        cv.put("goal", goal);
        cv.put("idPhotoAssembler", 3);
        db.insert("workout", null, cv);
        db.close();
    }

    public static Date getCurrentDate() {
        Date date = new Date();
        return date;
    }

    public static Date setLastWorkoutDayDate(Date startDate, int nbDay) {
        long expiremilis = 60000l; // 1 minute
        int minute = 60000;//1minute en ms
        int heure = 3600000;// 1heure en ms
        int day = 86400000;// 1days en ms
        // Expires on one minute from the date object date
        Date expireDate = new Date(startDate.getTime() + (day * nbDay));
        return expireDate;
    }

    public static ArrayList<Workout> getAllWorkout(Context ctx) {
        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        String query = "Select * from workout";
        Cursor cursor = db.rawQuery(query, null);
        listWorkout = new ArrayList<>();
        Workout w = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idWorkout"));
            int daysToWorkout = cursor.getInt(cursor.getColumnIndex("daysToWorkout"));
            String startingDate = cursor.getString(cursor.getColumnIndex("startingDate"));
            String endDate = cursor.getString(cursor.getColumnIndex("endDate"));
            String workoutName = cursor.getString(cursor.getColumnIndex("workoutName"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String goal = cursor.getString(cursor.getColumnIndex("goal"));
            int idPhotoAssembler = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));

            w = new Workout(id, idPhotoAssembler, workoutName, type, goal, daysToWorkout, startingDate, endDate);
            listWorkout.add(w);
        }
        db.close();
        return listWorkout;
    }

    public static Workout getWorkoutById(Context ctx, int id) {
        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        String query = "Select * from workout where idWorkout = ?";
        Cursor cursor = db.rawQuery(query, new String[]{"" + id});
        Workout w = null;
        while (cursor.moveToNext()) {
            int idWorkout = cursor.getInt(cursor.getColumnIndex("idWorkout"));
            int daysToWorkout = cursor.getInt(cursor.getColumnIndex("daysToWorkout"));
            String startingDate = cursor.getString(cursor.getColumnIndex("startingDate"));
            String endDate = cursor.getString(cursor.getColumnIndex("endDate"));
            String workoutName = cursor.getString(cursor.getColumnIndex("workoutName"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String goal = cursor.getString(cursor.getColumnIndex("goal"));
            int idPhotoAssembler = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
            w = new Workout(idWorkout, idPhotoAssembler, workoutName, type, goal, daysToWorkout, startingDate, endDate);

        }
        return w;
    }
}
