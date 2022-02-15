package com.example.gainztime.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gainztime.R;
import com.example.gainztime.entities.User;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.managers.PhotoManager;
import com.example.gainztime.managers.UserManager;
import com.example.gainztime.managers.WorkoutManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final String TAG = "debug";
    Context ctx;
    TextView tv_fitnessLvl, tv_fullName, tv_gender, tv_age, tv_weight;
    ImageView img0;
    ImageView img1;
    Button btnAddGallery;
    User u;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int contentView;
    Gson json;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    ArrayList<String> photoPaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        json = new Gson();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        //check if user exist if it dont inscription page load first
        if (UserManager.getUser(ctx) == null) {
//            Log.d(TAG, "onCreate: INSCIPTION ACTIVITY");
//            ConnexionBD.copyFronAsset(ctx);
            Intent intent = new Intent(ctx, InscriptionActivity.class);
            startActivity(intent);
        } else if (u == null) {
//            Log.d(TAG, "onCreate: MAIN ACTIVITY");
            u = UserManager.getUser(ctx);
            setContentView(R.layout.activity_main);
            initMainActivity();

            //photoPaths = getPhotoPathFromAssets();
//            for (String path : photoPaths
//            ) {
//                PhotoManager.createPhoto(ctx, path, "Exercices");
//            }
        }
    }

    public ArrayList<String> getPhotoPathFromAssets() {
        ArrayList<String> files = (ArrayList<String>) listAssetFiles(ctx, "exercices");
        ArrayList<String> listDimagePath = null;
        ArrayList<String> listDimagePath2 = null;
        ArrayList<String> pathFoundFile = new ArrayList<>();

        for (String s : files
        ) {
//            Log.d(TAG, "onCreate:  nom du files: Premier Etage " + s);
            listDimagePath = (ArrayList<String>) listAssetFiles(ctx, s);
            for (String s1 : listDimagePath
            ) {
//                Log.d(TAG, "onCreate:  nom du images Sous dossier: " + s1);
                listDimagePath2 = (ArrayList<String>) listAssetFiles(ctx, s1);
                pathFoundFile.add(s1);
                for (String s2 : listDimagePath2
                ) {
//                    Log.d(TAG, "onCreate:  nom du images Sous-sous dossier: " + s2);
                    pathFoundFile.add(s2);
                }
            }
        }

        ArrayList<String> validPathPhoto = new ArrayList<>();
        for (String s : pathFoundFile
        ) {
            if (s.indexOf(".") != -1) {
//                Log.d(TAG, "checkAssetFolder:  PathFileFound : " + s);
                validPathPhoto.add(s);
            } else {
//                Log.d(TAG, "checkAssetFolder:  PathFileFound new contient pas de . file " + s);
            }
        }
        return validPathPhoto;
    }

    public void initMainActivity() {
        tv_fitnessLvl = findViewById(R.id.tv_fitnessLvl);
        tv_fullName = findViewById(R.id.tv_fullname);
        tv_gender = findViewById(R.id.tv_gender);
        tv_age = findViewById(R.id.tv_age);
        tv_weight = findViewById(R.id.tv_weight);
        btnAddGallery = findViewById(R.id.btn_add_picture_gallery);

        ImageButton btnGoal = findViewById(R.id.btn_goals);
        ImageButton btnOnGoingTraining = findViewById(R.id.btn_ongoing_training);
        ImageButton btnTrainingDay = findViewById(R.id.btn_training_day);
        ImageButton btnUser = findViewById(R.id.btn_user);

        tv_fitnessLvl.setText(u.getLevel_fitness());
        tv_fullName.setText(u.getFirstName() + " " + u.getLastName());
        tv_gender.setText(u.getGender());
        tv_age.setText(u.getAge());
        tv_weight.setText(u.getWeight());

        img0 = findViewById(R.id.img0);

        btnAddGallery.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, WorkoutConfigActivity.class);
                startActivity(intent);
            }
        });

        btnTrainingDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, WorkoutDaysActivity.class);
                startActivity(intent);
            }
        });
        btnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, WorkoutProgression.class);
                startActivity(intent);
            }
        });
        btnOnGoingTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, CurrentWorkoutActivity.class);
                startActivity(intent);
            }
        });
    }

    //function to list files and directories
    public static List<String> listAssetFiles(Context c, String rootPath) {
        List<String> files = new ArrayList<>();
        try {
            String[] Paths = c.getAssets().list(rootPath);
            if (Paths.length > 0) {
                // This is a folder
                for (String file : Paths) {
                    String path = rootPath + "/" + file;
                    if (new File(path).isDirectory())
                        files.addAll(listAssetFiles(c, path));
                    else files.add(path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public void handleLogoClick(View view) {
        Intent intent = new Intent(ctx, InscriptionActivity.class);
        startActivityForResult(intent, 42);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ctx, "camera granted", Toast.LENGTH_SHORT).show();
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(ctx, "camera denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Gson gson = new Gson();
        if (requestCode == 42) {
            if (resultCode == RESULT_OK) {
                String user = data.getStringExtra("user");
                u = gson.fromJson(user, User.class);
                editor.putString(user, "no user");
                editor.commit();

                tv_fitnessLvl.setText(u.getLevel_fitness());
                tv_fullName.setText(u.getFirstName() + " " + u.getLastName());
                tv_gender.setText(u.getGender());
                tv_age.setText(u.getAge());
                tv_weight.setText(u.getWeight());
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img0.setImageBitmap(photo);
        }
    }
}