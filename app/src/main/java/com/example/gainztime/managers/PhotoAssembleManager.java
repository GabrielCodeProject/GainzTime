package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Photo;
import com.example.gainztime.entities.PhotoAssemble;

import java.util.ArrayList;

public class PhotoAssembleManager {
    static ArrayList<PhotoAssemble> photoAssembles;

    //1 listExercicesPtoho  2 listWorkoutPhoto  3 listGalleryUser
    //utiliser quand on get les photos since on les instancie a se moment
    public static void createPhotoAssemble(Context context, int idPhoto, String destination) {
//        int id = PhotoManager.getPhotoById(context, idPhoto).getId();
        SQLiteDatabase db = ConnexionBD.getBd(context);
        ContentValues cv = new ContentValues();

        cv.put("idPhoto", idPhoto);
        cv.put("destination", destination);
        db.insert("photoAssembler", null, cv);
        db.close();
    }

    public static ArrayList<PhotoAssemble> getAllPhotoAssemble(Context context) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photoAssembler";
        Cursor cursor = db.rawQuery(query, null);
        photoAssembles = new ArrayList<>();
        PhotoAssemble photoAssemble = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
            int idPhoto = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String destination = cursor.getString(cursor.getColumnIndex("destination"));
            photoAssemble = new PhotoAssemble(id, idPhoto, destination);
            photoAssembles.add(photoAssemble);
        }
        return photoAssembles;
    }

    public static PhotoAssemble getPhotoAssembleById(Context context, int id) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photoAssembler where destination = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        PhotoAssemble photoAssemble = null;
        while (cursor.moveToNext()) {
            int idDb = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
            int idPhoto = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String destinationDB = cursor.getString(cursor.getColumnIndex("destination"));
            photoAssemble = new PhotoAssemble(id, idPhoto, destinationDB);
        }
        db.close();
        return photoAssemble;
    }

    public static ArrayList<PhotoAssemble> getAllPhotoExerciceByDestination(Context context, String destination) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photoAssembler where destination = ?";
        Cursor cursor = db.rawQuery(query, new String[]{destination});
        photoAssembles = new ArrayList<>();
        PhotoAssemble photoAssemble = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
            int idPhoto = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String destinationDB = cursor.getString(cursor.getColumnIndex("destination"));
            photoAssemble = new PhotoAssemble(id, idPhoto, destinationDB);
            photoAssembles.add(photoAssemble);
        }
        db.close();
        return photoAssembles;
    }

    public static ArrayList<PhotoAssemble> getAllListPhotoExerciceFromPhotoDestination(Context context, String destination) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photoAssembler as pA JOIN photo as p on pA.idPhotoAssembler = p.idPhoto where p.destination = ?";
        Cursor cursor = db.rawQuery(query, new String[]{destination});
        photoAssembles = new ArrayList<>();
        PhotoAssemble photoAssemble = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPhotoAssembler"));
            int idPhoto = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String destinationDB = cursor.getString(cursor.getColumnIndex("destination"));
            photoAssemble = new PhotoAssemble(id, idPhoto, destinationDB);
            photoAssembles.add(photoAssemble);
        }
        return photoAssembles;
    }


}
