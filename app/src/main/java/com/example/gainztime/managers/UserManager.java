package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.User;
import com.example.gainztime.entities.Workout;

import java.util.ArrayList;

public class UserManager {
    public static ArrayList<Workout> getWorkouts(User u) {
        return u.getList_workouts();
    }

    public static void createUser(String age, String weight, String firstName, String lastName, String level_fitness, String gender, String height, Context context) {
        SQLiteDatabase bd = ConnexionBD.getBd(context);
        ContentValues cv = new ContentValues();
        cv.put("age", age);
        cv.put("weight", weight);
        cv.put("fisrtname", firstName);
        cv.put("lastname", lastName);
        cv.put("levelfitness", level_fitness);
        cv.put("gender", gender);
        cv.put("height", height);
        cv.put("idPhotoAssembler", 2);
        bd.insert("user", null, cv);
        bd.close();
    }

    //user by id dans la database
    public static User getUser(Context context) {
        SQLiteDatabase bd = ConnexionBD.getBd(context);
        String query = "select * from user where id=?";
        Cursor cursor = bd.rawQuery(query, new String[]{"1"});
        User u = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String weight = cursor.getString(cursor.getColumnIndex("weight"));
            String firstname = cursor.getString(cursor.getColumnIndex("fisrtname"));
            String lastname = cursor.getString(cursor.getColumnIndex("lastname"));
            String levelFitness = cursor.getString(cursor.getColumnIndex("levelfitness"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String height = cursor.getString(cursor.getColumnIndex("height"));
            int idPhotoAssembler = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
//            Log.d("debug", "getUser: " + id + age + weight + firstname + lastname + levelFitness + height + gender);
            u = new User(id, idPhotoAssembler, age, weight, firstname, lastname, levelFitness, height, gender);
        }
        bd.close();
        Log.d("debug", "getUser: " + u);
        return u;
    }

}
