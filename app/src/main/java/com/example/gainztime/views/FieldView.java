package com.example.gainztime.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.gainztime.R;

public class FieldView extends LinearLayout {
    TextView tv;
    EditText ed;
    Button btn;

    public FieldView(Context ctx) {
        super(ctx);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_field_view, this);

        tv = findViewById(R.id.tv);
        ed = findViewById(R.id.ed);

    }

    public FieldView(Context ctx, String title) {
        super(ctx);
        setOrientation(HORIZONTAL);
        tv = new TextView(getContext());
        tv.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 50));
        tv.setText(title);
        ed = new EditText(getContext());
        ed.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 50));
        btn = new Button(getContext());
        addView(tv);
        addView(ed);
    }

    public FieldView(Context ctx, @Nullable AttributeSet attrs) {
        super(ctx, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_field_view, this);
        tv = findViewById(R.id.tv);
        ed = findViewById(R.id.ed);
        TypedArray attributPerso = ctx.obtainStyledAttributes(attrs, R.styleable.FieldView);
        String title = attributPerso.getString(R.styleable.FieldView_title);
        tv.setText(title);
    }

    public void setTv(String txt) {
        tv.setText(txt);
    }

    public EditText getEd() {
        return ed;
    }
}
