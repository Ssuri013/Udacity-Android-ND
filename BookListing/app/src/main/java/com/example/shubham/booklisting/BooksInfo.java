package com.example.shubham.booklisting;


public class BooksInfo {
    private String title;
    private String author;
    BooksInfo(String x, String y){
        title = x;
        author = y;
    }

    String getTitle(){return title;}
    String getAuthor(){return author;}
}
