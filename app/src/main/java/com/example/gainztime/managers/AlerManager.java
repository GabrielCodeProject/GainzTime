package com.example.gainztime.managers;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.gainztime.R;
import com.example.gainztime.adapters.ExerciceAdapter;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;

import java.util.ArrayList;

public class AlerManager {

    public static AlertDialog simpleAlert(Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("workout info");

        AlertDialog dialog = builder.show();
        return dialog;
    }

    public static AlertDialog alertSetWeightUse(Context context, Exercice e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("How much did you lift");
        EditText ed = new EditText(context);
        builder.setView(ed);
        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                e.setFirstMaxWeight(Integer.parseInt(ed.getText().toString()));
                Log.d("debug", "onClick: " + e.getFirstMaxWeight());
            }
        });

        AlertDialog dialog = builder.show();
        return dialog;
    }

    public static AlertDialog alertSelectedDayWorkoutExercice(Context ctx, Workout w) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        LinearLayout llToReturn = (LinearLayout) LayoutInflater.from(ctx).inflate(R.layout.view_workout_progression, null);
        llToReturn.setTag("TEST");
        LinearLayout llMasterContainer = new LinearLayout(ctx);
        llMasterContainer.setOrientation(LinearLayout.VERTICAL);

        TextView tvWorkoutNameAlert = new TextView(ctx);
        tvWorkoutNameAlert.setText("Today Workout");
        tvWorkoutNameAlert.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvWorkoutNameAlert.setTextSize(35);
        llMasterContainer.addView(tvWorkoutNameAlert);

        ListView lv = new ListView(ctx);
        ExerciceAdapter exerciceAdapter = new ExerciceAdapter(ctx, R.layout.layout_exercice_view, ExerciseManager.getAllExercise(ctx));
        lv.setAdapter(exerciceAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //set le poid utiliser

            }
        });
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(ctx).inflate(R.layout.layout_exercice_view, null);
        for (Exercice e : ExerciseManager.getAllExercise(ctx)
        ) {
            TextView tv = linearLayout.findViewById(R.id.tv_info_exercice);
            tv.setText(e.getNameExercice());
        }

        llMasterContainer.addView(lv);
        builder.setView(llMasterContainer);
        AlertDialog dialog = builder.show();
        return dialog;
    }

    public static AlertDialog alertWorkoutDay(Context ctx, Workout w) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        LinearLayout llMasterContainer = new LinearLayout(ctx);
        llMasterContainer.setOrientation(LinearLayout.VERTICAL);

        TextView tvWorkoutNameAlert = new TextView(ctx);
        tvWorkoutNameAlert.setText(w.getStrWorkoutName());
        tvWorkoutNameAlert.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvWorkoutNameAlert.setTextSize(35);
        llMasterContainer.addView(tvWorkoutNameAlert);

        //affichage de la progression du jour 1 contre le dernier jour
        //representer par la marche maximum entrer
        LinearLayout llWorkoutResult = new LinearLayout(ctx);
        llWorkoutResult.setOrientation(LinearLayout.HORIZONTAL);

        llWorkoutResult.addView(ViewManager.getViewWorkoutProgression(ctx, ExerciseManager.getAllExercise(ctx), w.getDateStartWorkout()));
        llWorkoutResult.addView(ViewManager.getViewWorkoutProgression(ctx, ExerciseManager.getAllExercise(ctx), w.getDateEndWorkout()));

        ListView lv = new ListView(ctx);
        ExerciceAdapter exerciceAdapter = new ExerciceAdapter(ctx, R.layout.layout_exercice_view, ExerciseManager.getAllExercise(ctx));
        lv.setAdapter(exerciceAdapter);

        llMasterContainer.addView(llWorkoutResult);
        llMasterContainer.addView(lv);

        builder.setView(llMasterContainer);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.show();
        return dialog;
    }
}
