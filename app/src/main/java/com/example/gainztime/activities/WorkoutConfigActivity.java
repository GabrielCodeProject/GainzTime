package com.example.gainztime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.gainztime.R;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.entities.WorkoutAssembler;
import com.example.gainztime.managers.ExerciseManager;
import com.example.gainztime.managers.WorkoutAssemblerManager;
import com.example.gainztime.managers.WorkoutManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WorkoutConfigActivity extends Activity {

    private static final String TAG = "debug";
    ImageButton imgBtn;
    Spinner spinnerTrainingType;
    String[] trainingTypeValue = {null};
    String[] selectedTrainingType = {null};

    Spinner spinnerWorkoutGoal;
    String[] goalType = {null};
    String[] selectedGoal = {null};
    Button btnCreatePlan;

    EditText edDay;
    Context ctx;
    Gson json;

    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_config);
        ctx = this;
        json = new Gson();
        imgBtn = findViewById(R.id.btn_return);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinnerTrainingType = findViewById(R.id.spinner_training_type);
        trainingTypeValue = getResources().getStringArray(R.array.training_type);
        ArrayAdapter<String> staticAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, trainingTypeValue);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrainingType.setAdapter(staticAdapter);
        spinnerTrainingType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTrainingType[0] = (String) adapterView.getItemAtPosition(i);
                Log.d(TAG, "onItemSelected: test " + selectedTrainingType[0]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerWorkoutGoal = findViewById(R.id.spinner_workout_goal);
        goalType = getResources().getStringArray(R.array.goal_list);
        ArrayAdapter<String> goalTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, goalType);
        goalTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWorkoutGoal.setAdapter(goalTypeAdapter);
        spinnerWorkoutGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGoal[0] = (String) adapterView.getItemAtPosition(i);
                Log.d(TAG, "onItemSelected: test " + selectedGoal[0]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ed = findViewById(R.id.ed);

        edDay = findViewById(R.id.ed_training_day);
        btnCreatePlan = findViewById(R.id.btn_create_plan);
        btnCreatePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //test
                ArrayList<Exercice> lists = ExerciseManager.getAllExercise(ctx);
                lists.add(new Exercice(999, "blob", "this", 1, 1, 1, 999));
                lists.add(new Exercice(999, "sehss", "this", 1, 1, 1, 999));
                lists.add(new Exercice(999, "wessh", "this", 1, 1, 1, 999));
                lists.add(new Exercice(999, "wask", "this", 1, 1, 1, 999));
                lists.add(new Exercice(999, "msuh", "this", 1, 1, 1, 999));

                String test = json.toJson(lists);
                Log.d(TAG, "onClick: before database " + test);
                //end test

                WorkoutManager.createWorkout(ctx, Integer.parseInt(edDay.getText().toString()), ed.getText().toString(), selectedTrainingType[0], test);
//                WorkoutManager.createWorkout(ctx, Integer.parseInt(edDay.getText().toString()), ed.getText().toString(), selectedTrainingType[0], selectedGoal[0]);
                Workout w = WorkoutManager.getWorkoutById(ctx, 2);
                ArrayList<Exercice> exerciceList = ExerciseManager.getAllExercise(ctx);
                Log.d(TAG, "onClick: CreatePLan :" + w.getId() + " " + w.getStrWorkoutName() +
                        " Exercice :" + exerciceList.size() + " " + exerciceList.get(1).getNameExercice());
                for (Exercice e : exerciceList
                ) {
                    WorkoutAssemblerManager.createWorkoutAssembleDB(ctx, w, e);
                }
                finish();
            }
        });


    }
}