package com.example.shubham.booklisting;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<BooksInfo> {

    private static final String LOG_TAG = BooksAdapter.class.getSimpleName();


    public BooksAdapter(Activity context, ArrayList<BooksInfo> eq) {

        super(context, 0, eq);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listofbooks, parent, false);
        }

        BooksInfo currentBook = getItem(position);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.title);
        String str = currentBook.getTitle() + " ";
        nameTextView.setText(str);

        TextView numberTextView1 = (TextView) listItemView.findViewById(R.id.author);
        numberTextView1.setText(currentBook.getAuthor());

            ImageView img = (ImageView) listItemView.findViewById(R.id.imageB);
            img.setImageBitmap(currentBook.getImg());
        return listItemView;

    }




}
