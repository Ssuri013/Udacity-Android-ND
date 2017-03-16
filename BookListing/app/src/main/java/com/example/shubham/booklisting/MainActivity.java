package com.example.shubham.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = Utils.class.getSimpleName();
    final String BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=" ;
    private ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.emptyList);
                if( cm.getActiveNetworkInfo() == null ) {
                    tv.setText("No Internet Connection");
                    tv.setVisibility(View.VISIBLE);
              }
                else {
                    tv.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                    EditText et = (EditText) findViewById(R.id.editText);
                    //sewing url from sub strings
                    String nameOfBook = et.getText().toString();
                    String url = BOOKS_URL + nameOfBook + "&maxResults=10"; //reduce time by limiting no. of responses
                    //async retrival of data and display
                    backgroundWork bw = new backgroundWork();
                    bw.execute(url);
                }
            }
        });
    }


    void updateUI(String str){

        ArrayList<BooksInfo> books = new ArrayList<BooksInfo>();

        try {

            JSONObject root = new JSONObject(str);
            JSONArray arr = root.optJSONArray("items");
            if(arr == null){
                TextView tv = (TextView) findViewById(R.id.emptyList);
                tv.setText("No Results");
                tv.setVisibility(View.VISIBLE);
                return;
            }
            for (int  i = 0; i < arr.length(); i++) {
                JSONObject tem = arr.getJSONObject(i);
                JSONObject temp = tem.getJSONObject("volumeInfo");
                JSONArray aut = temp.optJSONArray("authors");
               String authors="";
                if(aut ==null){
                    authors =" ";
                }
                else{
                    for(int j = 0; j<aut.length(); j++){
                        authors = authors + " " + aut.getString(j);
                    }
                }
                books.add(new BooksInfo(temp.getString("title"), authors ));

            }
        } catch (final JSONException e) {
            Log.e(LOG_TAG, "error in JSON parsing");
        }
        final BooksAdapter adapter = new BooksAdapter(this, books);

        ListView BooksListView = (ListView) findViewById(R.id.list);

        BooksListView.setAdapter(adapter);
    }

    class backgroundWork extends AsyncTask<String, Void , String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = com.example.shubham.booklisting.Utils.fetchEarthquakeData(strings[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String str) {
            updateUI(str);
        }
    }
}
