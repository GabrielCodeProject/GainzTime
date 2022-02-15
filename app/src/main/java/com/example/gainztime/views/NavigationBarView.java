package com.example.gainztime.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.gainztime.R;

public class NavigationBarView extends LinearLayout {

    ImageView actionLogo;
    ImageButton btnRetour;

    public NavigationBarView(Context context) {
        super(context);

    }

    public NavigationBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LinearLayout ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_navigation_bar, this);
        actionLogo = findViewById(R.id.logo_action);
        btnRetour = findViewById(R.id.btn_return);
        //faire un attr declare stylable pour setter un logo
        TypedArray attributPerso = context.obtainStyledAttributes(attrs, R.styleable.NavigationBarView);
        String icon = attributPerso.getString(R.styleable.NavigationBarView_src);

        switch (icon) {
            case "create plan":
                actionLogo.setImageResource(R.drawable.user_128px);
                break;
            case "trainings":
                actionLogo.setImageResource(R.drawable.bench_press_128px);
                break;
            case "today training":
                actionLogo.setImageResource(R.drawable.running_128px);
                break;
            case "progression":
                actionLogo.setImageResource(R.drawable.goal_128px);
                break;
        }
    }
}
