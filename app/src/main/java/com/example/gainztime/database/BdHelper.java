package com.example.gainztime.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gainztime.entities.Exercice;

import java.util.ArrayList;

public class BdHelper extends SQLiteOpenHelper {
    public BdHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void onWorkoutAssemble(SQLiteDatabase sqLiteDatabase, ArrayList<Exercice> exercices){

    }
}
