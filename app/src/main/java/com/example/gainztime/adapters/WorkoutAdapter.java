package com.example.gainztime.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gainztime.R;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.managers.AlerManager;
import com.example.gainztime.managers.WorkoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private static final String TAG = "debug";
    int idlayout;
    String tag = "";


    public WorkoutAdapter(@NonNull Context context, int resource, @NonNull List<Workout> objects) {
        super(context, resource, objects);
        idlayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Workout w = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(idlayout, null);
            Log.d(TAG, "getView: " + convertView.getId() + " tag " + convertView.getTag());
            //plusieur action sur le meme adapter
            tag = convertView.getTag().toString();

            if (!tag.equals("day_container") == true) {
                TextView tv = convertView.findViewById(R.id.tv_workoutName);
                tv.setText(w.getStrWorkoutName());
            }


        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: WORKOUT" + w.getId() + " " + w.getStrWorkoutName());

                //onclick sur une journee show aler avec les exercice de la journee
                if (tag.equals("day_container")) {
                    AlerManager.alertSelectedDayWorkoutExercice(getContext(), WorkoutManager.getWorkoutById(getContext(), 1)).show();
                }

                //if activity = WorkoutProgression
                else if (!tag.equals("tag workoout")) {
                    AlerManager.alertWorkoutDay(getContext(), w);
                }
            }
        });


        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
