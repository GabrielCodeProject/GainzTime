package com.example.gainztime.activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gainztime.R;
import com.example.gainztime.adapters.ExerciceAdapter;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.User;
import com.example.gainztime.entities.WorkoutAssembler;
import com.example.gainztime.managers.AlerManager;
import com.example.gainztime.managers.ExerciseManager;
import com.example.gainztime.managers.UserManager;
import com.example.gainztime.managers.WorkoutAssemblerManager;
import com.example.gainztime.managers.WorkoutManager;

import java.util.ArrayList;

public class CurrentWorkoutActivity extends Activity {
    ListView lv;
    Context ctx;
    ImageButton btnRetour;

    ExerciceAdapter exerciceAdapter;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_workout);
        lv = findViewById(R.id.lv_day_exercice);
        ctx = this;
        btnRetour = findViewById(R.id.btn_return);
        u = UserManager.getUser(ctx);
        u.setList_workouts(WorkoutManager.getAllWorkout(ctx));

        ArrayList<Exercice> exercices = ExerciseManager.getAllExercise(ctx);
        ArrayList<WorkoutAssembler> workoutAssembles = WorkoutAssemblerManager.getAllFromWorkoutAssembleByIdWorkout(ctx, 1);
        ArrayList<Exercice> currentWorkoutExercices = new ArrayList<>();

        Exercice e;
        for (WorkoutAssembler workoutAssembler : workoutAssembles
        ) {
            e = exercices.get(workoutAssembler.getIdExercice() - 1);
            currentWorkoutExercices.add(e);
        }

        exerciceAdapter = new ExerciceAdapter(ctx, R.layout.layout_exercice_view, currentWorkoutExercices);
        lv.setAdapter(exerciceAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //alert dialog pour set le poid utiliser pour lexercice
                for (Exercice e : exercices
                ) {
                    AlerManager.alertSetWeightUse(ctx, e);
                }
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}