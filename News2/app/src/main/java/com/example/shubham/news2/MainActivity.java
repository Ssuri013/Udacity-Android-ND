package com.example.shubham.news2;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> newsList = new ArrayList<String>();
    private ArrayList<String> newsUrllList = new ArrayList<String>();
    private ListView newsListView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        newsListView = (ListView)findViewById(R.id.newsListView);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position " + newsUrllList.get(position));

                String url = newsUrllList.get(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newsList);

        fetchNews();

        Timer timer = new Timer();

        //Run every hour to fetch news
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                fetchNews();
            }
        };

        timer.schedule (hourlyTask, 0l, 1000*60*60);



        if (savedInstanceState != null) {
            newsList = savedInstanceState.getStringArrayList("newHeaderArray");
            newsUrllList = savedInstanceState.getStringArrayList("newUrlArray");
            System.out.println(("saved state " + newsList));
            System.out.println("saved state " + newsUrllList);

            newsListView.setAdapter(arrayAdapter);
        }
    }

    private void fetchNews(){
        newsList.clear();
        newsUrllList.clear();
        DownloadTask task = new DownloadTask();
        final  String NEWS_URL = "https://content.guardianapis.com/search?q=" + "USA";//attach search query
        final  String API_KEY = "&api-key=c4d0d595-6d9c-4297-b817-3bae33fbd001";
        String urlEndPoint = NEWS_URL + API_KEY;
        urlEndPoint = urlEndPoint.replaceAll("\\s+","");
        task.execute(urlEndPoint);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject responseData = jsonObject.getJSONObject("response");


                    JSONArray arr = responseData.getJSONArray("results");

                    System.out.println("array " + arr);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String newsTitle = jsonPart.getString("webTitle");
                        String newsLink = jsonPart.getString("webUrl");

                        System.out.println("title " + newsTitle);
                        System.out.println(newsLink);

                        newsList.add(newsTitle);
                        newsUrllList.add(newsLink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                newsListView.setAdapter(arrayAdapter);

            } else {
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putSerializable("newHeaderArray", newsList);
        outState.putSerializable("newUrlArray", newsUrllList);
    }
}
