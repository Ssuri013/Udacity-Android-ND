package com.example.shubham.booklisting;


import android.graphics.Bitmap;

import java.net.URL;

public class BooksInfo {
    private String title;
    private String author;
    private Bitmap img;
    BooksInfo(String x, String y, Bitmap m){
        title = x;
        author = y;
        img = m;
    }

    String getTitle(){return title;}
    String getAuthor(){return author;}
    Bitmap getImg(){return img;}
}
