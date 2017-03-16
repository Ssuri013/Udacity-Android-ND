package com.shubh.android.ghoomoghumao;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Smily on 2/24/2017.
 */

public class TourAdapter extends ArrayAdapter<Tour> {

    public TourAdapter(Activity context, ArrayList<Tour> array){
        super(context, 0, array);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Tour current = getItem(position);

        ImageView img = (ImageView) listItemView.findViewById(R.id.view_img);
        img.setImageResource(current.getImage());

        TextView text = (TextView) listItemView.findViewById(R.id.view_text);
        text.setText(current.getTextOne().toString());

        TextView add = (TextView) listItemView.findViewById(R.id.view_number);
        add.setText(current.getLocation().toString());


        return  listItemView;
    }
}
