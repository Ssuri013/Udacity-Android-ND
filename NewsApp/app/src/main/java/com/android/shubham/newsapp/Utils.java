package com.android.shubham.newsapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shubham on 06-Jun-17.
 */

public class Utils {
    final static String BASE_URL = " https://content.guardianapis.com/search?";
    //search is type here , we also have tag and section
    //section is used to get news of particular type
    final static String API_ATT = "api-key";
    final static String API_KEY = "c1ee32bb-2d69-44af-9237-918315105e31";
    final static String QUERY_ATT = "q";



    public static ArrayList<NewsData> fetchData(String query){
        //build url
        Uri uri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(API_ATT,API_KEY).
                appendQueryParameter(QUERY_ATT, query).build();

        ArrayList<NewsData> data = new ArrayList<NewsData>();//store final data
        HttpURLConnection conn = null;
        try{
            //create URL
            URL url = new URL(uri.toString());
            Log.e("Url", uri.toString() );
            //open connection
            conn = (HttpURLConnection) url.openConnection();
            //get inputStream and convert to string
            Scanner sc = new Scanner(conn.getInputStream()).useDelimiter("//A");
            String jsonResponse = sc.hasNext()? sc.next() : null;
           // Log.e("jsonResponse", jsonResponse);
            data = jsonParseer(jsonResponse);

        }
        catch (MalformedURLException e){
            Log.e("utils", "url malformed");
        }
        catch(IOException io){
            Log.e("utils", "IO exception");
        }
        finally {
        conn.disconnect();
        }
        return data;
    }


    static ArrayList<NewsData> jsonParseer(String str)  {
        ArrayList<NewsData> data = new ArrayList<>();
        try {
            Log.e("0","0");
            JSONObject jo = new JSONObject(str);
            jo = jo.getJSONObject("response");
            JSONArray ja = jo.getJSONArray("results");
            Log.e("1","1");
            for(int i=0; i< ja.length(); i++){
                jo = ja.getJSONObject(i);
                Log.e("2","2");
                String title = jo.getString("webTitle");
                String url = jo.getString("webUrl");
                String sec = jo.getString("sectionName");
                data.add( new NewsData(title, sec, url) );
            }
            return data;
        }
        catch(JSONException je){
            Log.e("utils", "jsonerror");

        }
        return null;
    }


}
