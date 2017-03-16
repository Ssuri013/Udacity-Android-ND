package com.shubh.android.ghoomoghumao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TravelGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ArrayList<Tour> tourItems = new ArrayList<Tour>();
        tourItems.add(new Tour(R.drawable.lake, getString(R.string.tg_one), getString(R.string.loc_one_tg)));
        tourItems.add(new Tour(R.drawable.rock_garden, getString(R.string.tg_two), getString(R.string.loc_two_tg)));
        tourItems.add(new Tour(R.drawable.rosegarden, getString(R.string.tg_three), getString(R.string.loc_three_tg)));
        tourItems.add(new Tour(R.drawable.zoo, getString(R.string.tg_four), getString(R.string.loc_four_tg)));
        tourItems.add(new Tour(R.drawable.japanesegarden, getString(R.string.tg_five), getString(R.string.loc_five_tg)));

        TourAdapter adapter = new TourAdapter (this, tourItems);
        ListView listView = (ListView)findViewById(R.id.list_id);
        listView.setAdapter(adapter);
    }
}
