package com.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

public class LoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        String name = getIntent().getStringExtra("NAME");
        TextView tv = (TextView) findViewById(R.id.text);
        tv.setText("Welcome,");
        SpannableString ss1=  new SpannableString(name);

        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        tv.append(ss1);


    }

}
