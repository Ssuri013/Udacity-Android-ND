package com.example.shubham.musixstructure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.pop);
        final Intent in = new Intent(this, Gen.class);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("genre","Pop");
                startActivity(in);
            }
        });

        TextView tv2 = (TextView) findViewById(R.id.retro);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in.putExtra("genre","Retro");
                startActivity(in);
            }
        });

    }
}
