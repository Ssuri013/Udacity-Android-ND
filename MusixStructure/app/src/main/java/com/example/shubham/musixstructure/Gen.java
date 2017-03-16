package com.example.shubham.musixstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Gen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        String genre = getIntent().getStringExtra("genre");

        final TextView tv1 = (TextView) findViewById(R.id.song1);
        final TextView tv2 = (TextView) findViewById(R.id.song2);
        TextView tv3 = (TextView) findViewById(R.id.type);
        tv3.setText("Song for Genre " + genre);
        if(genre.equals("Pop")){
            tv1.setText("Photograph");
            tv2.setText("Be the 1");
        }
        else{
            tv1.setText("Under Pressure");
            tv2.setText("Raised me up");
        }
        final Intent in = new Intent(this, PlayScreen.class);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("Song_name", tv1.getText());
                startActivity(in);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("Song_name", tv2.getText());
                startActivity(in);

            }
        });

    }
}
