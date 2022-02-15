package com.example.gainztime.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.gainztime.R;

public class MenuNavigationView extends LinearLayout {

    public MenuNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_menu_navigation, this);
        ImageButton btnGoal = findViewById(R.id.btn_goals);
        ImageButton btnOnGoingTraining = findViewById(R.id.btn_ongoing_training);
        ImageButton btnTrainingDay = findViewById(R.id.btn_training_day);
        ImageButton btnUser = findViewById(R.id.btn_user);




    }
}
