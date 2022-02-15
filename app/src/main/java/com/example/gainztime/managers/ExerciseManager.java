package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Photo;
import com.example.gainztime.entities.PhotoAssemble;
import com.example.gainztime.entities.Workout;

import java.util.ArrayList;

public class ExerciseManager {
    static ArrayList<Exercice> listExercises;

    public static ArrayList<Exercice> getAllExercise(Context ctx) {
        ArrayList<PhotoAssemble> listPhotoAssemblesExercices = PhotoAssembleManager.getAllPhotoExerciceByDestination(ctx, "Exercices");

        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        String query = "Select * from exercice";
        Cursor cursor = db.rawQuery(query, null);
        listExercises = new ArrayList<>();
        Exercice e = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idExercice"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            int isExecute = cursor.getInt(cursor.getColumnIndex("isExecute"));
            int idPhotoAssembler = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));

            e = new Exercice(id, name, description, isExecute, null, null, idPhotoAssembler);
//            asignPhotoToExercice(ctx, e,listPhotoAssemblesExercices);
            listExercises.add(e);
        }
        db.close();
        return listExercises;
    }

    //get toute les photo qui on Exercice dans  la table photo
    private static void asignPhotoToExercice(Context ctx, Exercice e,ArrayList<PhotoAssemble> list) {
//        ArrayList<PhotoAssemble> listPhotoAssemblesExercices = PhotoAssembleManager.getAllPhotoExerciceByDestination(ctx, "Exercices");
        for (PhotoAssemble pA : list
        ) {
            e.setIdPhotoAssembler(pA.getIdPhoto());
            PhotoAssembleManager.createPhotoAssemble(ctx, pA.getIdPhoto(), "Exercices");
        }

    }

    public static void createExercice(Context ctx, Exercice e) {
        SQLiteDatabase db = ConnexionBD.getBd(ctx);
        ContentValues cv = new ContentValues();
        cv.put("name", e.getNameExercice());
        cv.put("description", e.getDescriptionProcedure());
        cv.put("isExecute", e.getExecute());
        cv.put("idPhotoAssembler", e.getIdPhotoAssembler());
        db.insert("exercice", null, cv);
        db.close();
    }
}
