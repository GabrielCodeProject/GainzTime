package com.example.gainztime.managers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gainztime.R;
import com.example.gainztime.entities.Exercice;

import java.util.ArrayList;

public class ViewManager {

    public static LinearLayout getViewWorkoutProgression(Context ctx, ArrayList<Exercice> exercices, String title) {
        LinearLayout llToReturn = (LinearLayout) LayoutInflater.from(ctx).inflate(R.layout.view_workout_progression, null);
        llToReturn.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        TextView tvTitle = llToReturn.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setTextSize(25);
        TextView tvExerciceName;
        for (Exercice e : exercices
        ) {
            tvExerciceName = new TextView(ctx);
            tvExerciceName.setText(e.getNameExercice() + " poids: " + e.getDescriptionProcedure());
            llToReturn.addView(tvExerciceName);
        }

        return llToReturn;
    }

}
