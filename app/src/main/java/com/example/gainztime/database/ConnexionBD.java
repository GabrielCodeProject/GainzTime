package com.example.gainztime.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConnexionBD {

    private static String nomBd = "gainztime.db";
    private static int version = 1;
    private static SQLiteDatabase db;

    public static SQLiteDatabase getBd(Context context){
        BdHelper bdHelper = new BdHelper(context, nomBd, null, version);
        db = bdHelper.getWritableDatabase();
        return  db;
    }
    public static void close(){
        db.close();
    }

    public static void copyFronAsset(Context ctx){
        try {

            InputStream in = ctx.getAssets().open(nomBd);
            File file = ctx.getDatabasePath(nomBd);
            FileOutputStream out = new FileOutputStream(file);
            byte[] tampon = new byte[10];
            while (in.read(tampon)!= -1){
                out.write(tampon);
                tampon = new byte[10];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
