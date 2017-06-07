package com.android.shubham.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsData>>
        ,SwipeRefreshLayout.OnRefreshListener{

    RelativeLayout SomeError;
    NewsAdapter adapter;
    final int LOADER_ID =  13;
    SwipeRefreshLayout swipe;
    ArrayList<NewsData> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //blank lines for readability . differentiating
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SomeError = (RelativeLayout) findViewById(R.id.empty_layout);
        SomeError.setVisibility(View.GONE);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeResources(R.color.colorAccent , R.color.colorAccent2);

        ListView lv = (ListView) findViewById(R.id.list_view);
        adapter = new NewsAdapter(this);
        lv.setAdapter(adapter);

        final LoaderManager lm = getSupportLoaderManager();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(data.get(position).getUrl()) );
                startActivity(in);
            }
        });

        lm.initLoader(LOADER_ID, null, this);
    }
    @Override
    public Loader<ArrayList<NewsData>> onCreateLoader(int id, Bundle args) {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if( !isConnected){
            SomeError.setVisibility(View.VISIBLE);
            Log.e("no connetion", " ");
            return null;

        }
        else {
            AsyncTaskLoader<ArrayList<NewsData>> atl = new AsyncTaskLoader<ArrayList<NewsData>>(this) {

                @Override
                protected void onStartLoading() {
                    forceLoad();
                }

                @Override
                public ArrayList<NewsData> loadInBackground() {
                    return Utils.fetchData("android");
                }

                @Override
                public void deliverResult(ArrayList<NewsData> data2) {
                    super.deliverResult(data2);
                }
            };
            return atl;
        }
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsData>> loader, ArrayList<NewsData> data2) {
        data =data2;
        swipe.setRefreshing(false);
        adapter.clear();
        if(data!=null) {
            adapter.addAll(data);
            SomeError.setVisibility(View.GONE);
        }
        else{
                SomeError.setVisibility(View.VISIBLE);
            Log.e("some error", " ");
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
