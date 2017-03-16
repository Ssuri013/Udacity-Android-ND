package com.shubh.android.ghoomoghumao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ArrayList<Tour> tourItems = new ArrayList<Tour>();
        tourItems.add(new Tour(R.drawable.jwmar, getString(R.string.hotel_one), getString(R.string.loc_one_hotel)));
        tourItems.add(new Tour(R.drawable.lalit, getString(R.string.hotel_two), getString(R.string.loc_two_hotel)));
        tourItems.add(new Tour(R.drawable.hayatt, getString(R.string.hotel_three), getString(R.string.loc_three_hotel)));
        tourItems.add(new Tour(R.drawable.toy, getString(R.string.hotel_four), getString(R.string.loc_four_hotel)));
        tourItems.add(new Tour(R.drawable.turquoise, getString(R.string.hotel_five), getString(R.string.loc_five_hotel)));

        TourAdapter adapter = new TourAdapter (this, tourItems);
        ListView listView = (ListView)findViewById(R.id.list_id);
        listView.setAdapter(adapter);
    }
}
