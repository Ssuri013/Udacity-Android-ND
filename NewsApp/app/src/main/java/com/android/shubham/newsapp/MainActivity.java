package com.android.shubham.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsData>>
        ,SwipeRefreshLayout.OnRefreshListener{

    NewsAdapter adapter;
    final int LOADER_ID =  13;
    SwipeRefreshLayout swipe;
    ArrayList<NewsData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        ListView lv = (ListView) findViewById(R.id.list_view);
        adapter = new NewsAdapter(this);
        lv.setAdapter(adapter);
        LoaderManager lm = getSupportLoaderManager();
        Bundle query = new Bundle();
        query.putString("QUERY", "android");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(data.get(position).getUrl()) );
                startActivity(in);
            }
        });

        lm.initLoader(LOADER_ID, query, this);
    }

    @Override
    public Loader<ArrayList<NewsData>> onCreateLoader(int id, Bundle args) {

        Log.e("Loader", "started");

        //TODO: progress bar visible

        AsyncTaskLoader<ArrayList<NewsData>> atl =  new AsyncTaskLoader<ArrayList<NewsData>>(this) {

            @Override
            protected void onStartLoading() {
                forceLoad();

            }

            @Override
            public ArrayList<NewsData> loadInBackground() {
                Log.e("reached", "load in backgroud");
                return Utils.fetchData("android");
            }

            @Override
            public void deliverResult(ArrayList<NewsData> data2) {

                super.deliverResult(data2);
            }
        };
        return atl;

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsData>> loader, ArrayList<NewsData> data2) {
        //TODO: adapter
        data =data2;
        swipe.setRefreshing(false);
        adapter.clear();
        if(data!=null) {
            adapter.addAll(data);
        }
        else{
            Log.e("loader", "empty data");
        }

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsData>> loader) {

    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
    }
}
