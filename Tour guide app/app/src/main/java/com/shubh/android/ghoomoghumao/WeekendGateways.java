package com.shubh.android.ghoomoghumao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class WeekendGateways extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        ArrayList<Tour> tourItems = new ArrayList<Tour>();
        tourItems.add(new Tour(R.drawable.morni, getString(R.string.wg_one), getString(R.string.loc_one_wg)));
        tourItems.add(new Tour(R.drawable.timbertrail, getString(R.string.wg_two), getString(R.string.loc_two_wg)));
        tourItems.add(new Tour(R.drawable.barog,  getString(R.string.wg_three), getString(R.string.loc_three_wg)));
        tourItems.add(new Tour(R.drawable.solan,  getString(R.string.wg_four), getString(R.string.loc_four_wg)));
        tourItems.add(new Tour(R.drawable.funcity,  getString(R.string.wg_five), getString(R.string.loc_five_wg)));
        TourAdapter adapter = new TourAdapter (this, tourItems);
        ListView listView = (ListView)findViewById(R.id.list_id);
        listView.setAdapter(adapter);
    }
}
