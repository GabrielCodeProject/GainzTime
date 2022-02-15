package com.example.gainztime.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gainztime.R;
import com.example.gainztime.entities.User;
import com.example.gainztime.managers.UserManager;
import com.example.gainztime.views.FieldView;

import java.util.ArrayList;

public class InscriptionActivity extends Activity {

    private static final String TAG = "debug";
    private static final int CAMERA_REQUEST = 1888;
    Context ctx;
    final String[] lvlFitnessStr = {null};
    final String[] selectedHeight = {null};
    private final int MY_CAMERA_PERMISSION_CODE = 100;
    ImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        ctx = this;
        //image container element
        imgUser = findViewById(R.id.img_user);
        Button btnAddPicture = findViewById(R.id.btn_add_picture);


        btnAddPicture.setOnClickListener(new View.OnClickListener() {
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
        //firstname value
        FieldView firstNameView = findViewById(R.id.view_firstname_field);
        EditText edFirstName = firstNameView.getEd();

        //lastname value
        FieldView lasstNameView = findViewById(R.id.view_lastname_field);
        EditText edlastName = lasstNameView.getEd();

        //Gender value
        RadioGroup radioGroupGender = findViewById(R.id.radiogroup_btn);

        //Age value
        FieldView ageView = findViewById(R.id.view_age_field);
        EditText edAge = ageView.getEd();

        //Weight
        FieldView weightView = findViewById(R.id.view_weight_field);
        EditText edWeight = weightView.getEd();

        //Height
        Spinner heightDropDown = (Spinner) findViewById(R.id.spinner_height);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayList<String> listHeights = new ArrayList<>();
        for (int pied = 4; pied < 7; pied++) {
            for (int pouce = 0; pouce < 12; pouce++) {

                Log.d(TAG, "onCreate: Pied: " + pied + "'" + pouce);

                String height = pied + "'" + pouce;
                listHeights.add(height);

            }
        }

        ArrayAdapter<String> staticAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listHeights);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        heightDropDown.setAdapter(staticAdapter);

        heightDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("debug", (String) adapterView.getItemAtPosition(i));

                selectedHeight[0] = (String) adapterView.getItemAtPosition(i);

                Log.d(TAG, "onItemSelected: test " + selectedHeight[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Level Fitness
        Spinner lvlFitnessDropDown = (Spinner) findViewById(R.id.spinner_level_fitness);
        final String[] levelFitness = {"Beginer", "intermediare", "advance", "expert", "AnorldX"};
        ArrayAdapter<String> levelFitnessAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levelFitness);
        levelFitnessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lvlFitnessDropDown.setAdapter(levelFitnessAdapter);
        lvlFitnessDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lvlFitnessStr[0] = (String) adapterView.getItemAtPosition(i);
                Log.d(TAG, "onItemSelected: test " + lvlFitnessStr[0]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //button confirm
        Button btnConfirmUser = findViewById(R.id.btn_confirm_add_user);
        btnConfirmUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gender = radioGroupGender.getCheckedRadioButtonId();
                String genderStr;
                switch (gender) {
                    case 1:
                        genderStr = "Male";
                        break;
                    case 2:
                        genderStr = "Female";
                        break;
                    case 3:
                        genderStr = "Other";
                        break;
                    default:
                        genderStr = "None";
                        break;
                }

                String firstname = edFirstName.getText().toString();
                String lastName = edlastName.getText().toString();
                String age = edAge.getText().toString();
                String weight = edWeight.getText().toString();
                String height = selectedHeight[0];
                String levelFitness = lvlFitnessStr[0];
                UserManager.createUser(age, weight, firstname, lastName, levelFitness, genderStr, height, ctx);
                Toast.makeText(ctx, firstname + " " + lastName + " gender " + genderStr + " age: " + age + " weight: " + weight + "Height:  " + height + " LevelFitness: " + levelFitness, Toast.LENGTH_SHORT).show();

                Intent intentRetour = new Intent();
                intentRetour.putExtra("user", UserManager.getUser(ctx).toJSON());
                setResult(RESULT_OK, intentRetour);
                finish();
            }
        });
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
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgUser.setImageBitmap(photo);
        }
    }
}