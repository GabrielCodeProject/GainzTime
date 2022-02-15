package com.example.gainztime.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.gainztime.R;
import com.example.gainztime.entities.Exercice;
import com.example.gainztime.entities.Workout;
import com.example.gainztime.managers.AlerManager;
import com.example.gainztime.managers.UserManager;
import com.example.gainztime.managers.WorkoutManager;

import java.util.List;

public class ExerciceAdapter extends ArrayAdapter<Exercice> {
    private static final String TAG = "debug";
    int idlayout;

    ListView lvExercice;
    Exercice e;

    public ExerciceAdapter(@NonNull Context context, int resource, @NonNull List<Exercice> objects) {
        super(context, resource, objects);
        idlayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String tag = "";
//        Log.d(TAG, "getView: PArent tag" + parent.getTag().toString());
//        String parentTag = parent.getTag().toString();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(idlayout, null);
            tag = convertView.getTag().toString();

            //si layout exercice view
            if (tag.equals("Exercice info")) {
//                Log.d(TAG, "getView: TAG" + tag);
                e = getItem(position);
                lvExercice = convertView.findViewById(R.id.lv_day_exercice);

            }
        }

        String finalTag = tag;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick: WORKOUT" + e.getId() + " " + e.getNameExercice() + " TAG " + view.getParent());
                //si la tag du parent = exercices on est dans la page current workout
//                if (parentTag.equals("exercices")) {
//                Log.d(TAG, "getView: TAG" + finalTag);
                AlertDialog alertSetWeight = AlerManager.alertSetWeightUse(getContext(), e);
                alertSetWeight.show();
//                }
//                //si le tag du parent = all day workout view on est dans la page workoutdays
//                if (parentTag.equals("all day workout view")) {
//                    AlertDialog alertExerciceInfo = AlerManager.alertSelectedDayWorkoutExercice(getContext(), WorkoutManager.getWorkoutById(getContext(), position));
//                    alertExerciceInfo.show();
//                }

            }
        });
        TextView tv = convertView.findViewById(R.id.tv_info_exercice);
        tv.setText(e.getNameExercice());

        return convertView;
    }
}
