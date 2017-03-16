package com.shubh.android.ghoomoghumao;

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

         /*
        An intent to switch to TravelGuide Class on clicking the view.
         */
        TextView travelGuide = (TextView) findViewById(R.id.travel_guides);
        travelGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(MainActivity.this, TravelGuide.class);
                startActivity(in1);
            }
        });

        /*
        An intent to switch to FoodDrink class on clicking the view.
         */
        TextView food = (TextView) findViewById(R.id.food_n_drink);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(MainActivity.this, FoodDrink.class);
                startActivity(in2);
            }
        });

        /*
        An intent to switch to Hotels class on clicking the view.
         */
        TextView hotels = (TextView) findViewById(R.id.hotels);
        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(MainActivity.this, Hotels.class);
                startActivity(in3);
            }
        });

        /*
        An intent to switch to WeekendGateways class on clicking the view.
         */
        TextView weekendGateways = (TextView) findViewById(R.id.weekend_gateways);
        weekendGateways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in4 = new Intent(MainActivity.this, WeekendGateways.class);
                startActivity(in4);
            }
        });
    }
}
