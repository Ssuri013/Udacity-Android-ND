package com.android.shubham.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shubham on 06-Jun-17.
 */

public class NewsAdapter extends ArrayAdapter<NewsData> {
    NewsAdapter(Context context)//, ArrayList<NewsData> al){
    {
        super(context, 0, new ArrayList<NewsData>());
        //super(context, 0, al);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final NewsData currentArticle = getItem(position);

        TextView tv1 = (TextView) listItemView.findViewById(R.id.title);
        TextView tv2 = (TextView) listItemView.findViewById(R.id.section);

        tv1.setText( currentArticle.getTitle());
        tv2.setText( currentArticle.getCategory());




        return listItemView;
    }
}

