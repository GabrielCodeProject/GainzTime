package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.entities.WorkoutAssembler;

import java.util.ArrayList;

public class WorkoutAssemblerManager {
    private static ArrayList<WorkoutAssembler> workoutAssemblerArrayList;

    public static void createWorkoutAssembleDB(Context ctx, Workout w, Exercice e) {
        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        ContentValues cv = new ContentValues();
        cv.put("idWorkout", w.getId());
        cv.put("idExercice", e.getId());
        db.insert("workoutAssembler", null, cv);
        db.close();
    }

    public static ArrayList<WorkoutAssembler> getAllFromWorkoutAssembleByIdWorkout(Context context, int idWorkout) {

        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from workoutAssembler where idWorkout = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idWorkout)});
        WorkoutAssembler workoutAssembler = null;
        workoutAssemblerArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int idWorkoutDB = cursor.getInt(cursor.getColumnIndex("idWorkout"));
            int idExercice = cursor.getInt(cursor.getColumnIndex("idExercice"));

            workoutAssembler = new WorkoutAssembler(id, idWorkoutDB, idExercice);
            workoutAssemblerArrayList.add(workoutAssembler);
        }
        db.close();

        return workoutAssemblerArrayList;
    }
}
