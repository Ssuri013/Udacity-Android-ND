package com.example.shubham.musixstructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        String songName = getIntent().getStringExtra("Song_name");
        TextView tv = (TextView) findViewById(R.id.test);
        tv.setText(songName);
    }
}
