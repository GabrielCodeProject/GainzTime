package com.example.gainztime.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gainztime.database.ConnexionBD;
import com.example.gainztime.entities.Photo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhotoManager {

    static ArrayList<Photo> photos;

    public static ArrayList<Photo> getAllPhoto(Context context) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photo";
        Cursor cursor = db.rawQuery(query, null);
        photos = new ArrayList<>();
        Photo p = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String path = cursor.getString(cursor.getColumnIndex("sourceImg"));
            String takedDate = cursor.getString(cursor.getColumnIndex("takedDate"));
            String destination = cursor.getString(cursor.getColumnIndex("destination"));

            p = new Photo(id, path, takedDate, destination);
            photos.add(p);
        }
        db.close();
        return photos;
    }

    public static ArrayList<Photo> getPhotoByDestination(Context context, String destination) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photo where destination = ?";
        Cursor cursor = db.rawQuery(query, new String[]{destination});
        photos = new ArrayList<>();
        Photo p = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String path = cursor.getString(cursor.getColumnIndex("sourceImg"));
            String takedDate = cursor.getString(cursor.getColumnIndex("takedDate"));
            String destinationDb = cursor.getString(cursor.getColumnIndex("destination"));

            p = new Photo(id, path, takedDate, destinationDb);
            photos.add(p);
        }
        db.close();
        return photos;
    }

    public static Photo getPhotoById(Context context, int id) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        String query = "Select * from photo where idPhoto = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        Photo p = null;
        while (cursor.moveToNext()) {
            int idDB = cursor.getInt(cursor.getColumnIndex("idPhoto"));
            String path = cursor.getString(cursor.getColumnIndex("sourceImg"));
            String takedDate = cursor.getString(cursor.getColumnIndex("takedDate"));
            String destinationDb = cursor.getString(cursor.getColumnIndex("destination"));

            p = new Photo(idDB, path, takedDate, destinationDb);
        }
        db.close();
        return p;
    }

    public static void createPhoto(Context context, String path, String destination) {
        SQLiteDatabase db = ConnexionBD.getBd(context);
        ContentValues cv = new ContentValues();
        cv.put("sourceImg", path);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        cv.put("takedDate", dateFormat.format(getCurrentDate()));
        cv.put("destination", destination);
        db.insert("photo", null, cv);
        db.close();
    }

    public static Date getCurrentDate() {
        Date date = new Date();
        return date;
    }
}
