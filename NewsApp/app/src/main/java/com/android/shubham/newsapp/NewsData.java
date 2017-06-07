package com.android.shubham.newsapp;

/**
 * Created by shubham on 06-Jun-17.
 */

public class NewsData {
//once created , object is not modifiable
    private String title;
    private String section;
    private String url;

    NewsData(String a, String b, String c){
        title =a;
        section =b;
        url=c;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return section;
    }

    public String getTitle() {
        return title;
    }
}
