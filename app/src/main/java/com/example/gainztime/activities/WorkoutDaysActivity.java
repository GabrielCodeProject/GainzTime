package com.example.gainztime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gainztime.R;
import com.example.gainztime.adapters.WorkoutAdapter;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.managers.AlerManager;
import com.example.gainztime.managers.WorkoutManager;

public class WorkoutDaysActivity extends Activity {
    Context ctx;
    WorkoutAdapter workoutAdapter;
    ImageButton btnRetour;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_days);
        ctx = this;
        lv = findViewById(R.id.lv_workout_days_container);
        btnRetour = findViewById(R.id.btn_return);

        //get workout by id  getActiveWorkout return le id du workout avec isactive true
        workoutAdapter = new WorkoutAdapter(ctx, R.layout.layout_workout_day_view, WorkoutManager.getAllWorkout(ctx));
        lv.setAdapter(workoutAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //on click sur day1 un alert qui montre les exercie de la journer et le poid max jusqua present
                Workout w = (Workout) adapterView.getItemAtPosition(i);
//                AlerManager.alertSelectedDayWorkoutExercice(ctx, w).show();
                Log.d("debug", "onItemClick: " + w.getStrWorkoutName() + " " + w.getDateStartWorkout());
//                Toast.makeText(ctx, w.getStrWorkoutName() + " " + w.getDateStartWorkout(), Toast.LENGTH_SHORT).show();
//                AlerManager.alertWorkoutDay(ctx, (Workout) adapterView.getItemAtPosition(i)).show();
//                AlerManager.alertSelectedDayWorkoutExercice(ctx, w).show();
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