package com.example.gainztime.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.gainztime.R;
import com.example.gainztime.adapters.WorkoutAdapter;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.managers.AlerManager;
import com.example.gainztime.managers.WorkoutManager;
import com.google.gson.Gson;

import java.util.ArrayList;

public class WorkoutProgression extends Activity {

    ImageButton btnRetour;
    Context ctx;

    WorkoutAdapter workoutAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_progression);
        ctx = this;
        btnRetour = findViewById(R.id.btn_return);
        lv = findViewById(R.id.list_view);

        Gson json = new Gson();
        Workout w = WorkoutManager.getWorkoutById(ctx, 4);
        json.fromJson("hmm", Object.class);
        String test = json.toJson(w.getGoal());
        test = test.replace("\\", "");
        Log.d("debug", "onCreate: Fetch workout test json " + test);
        workoutAdapter = new WorkoutAdapter(ctx, R.layout.layout_workout_plan_view, WorkoutManager.getAllWorkout(ctx));
        lv.setAdapter(workoutAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlerManager.alertWorkoutDay(ctx, (Workout) adapterView.getItemAtPosition(i)).show();
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