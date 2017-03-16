package com.example.shubham.inventoryapp3;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shubham.inventoryapp3.data.OrderContract.OrderEntry;
import com.example.shubham.inventoryapp3.data.OrderCursorAdapter;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    OrderCursorAdapter mCursorAdapter;
    private static final String LOG_NAME = MainActivity.class.getSimpleName();
    int ORDER_LOADER =13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listview and adapter
        ListView lv = (ListView) findViewById(R.id.list_view);

        View emptyView = findViewById(R.id.empty_view);
        lv.setEmptyView(emptyView);


        mCursorAdapter = new OrderCursorAdapter(this,null);

        Log.e("on itemClick","created");

        lv.setAdapter(mCursorAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("on itemClick","reached");
                 Intent in = new Intent(MainActivity.this, EditorActivity.class);
                Uri uri = ContentUris.withAppendedId(OrderEntry.ORDERS_URI,id);
                in.setData(uri);
                startActivity(in);
            }
        });

        getSupportLoaderManager().initLoader(ORDER_LOADER,null,this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, OrderEntry.ORDERS_URI,
                null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummy();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                getContentResolver().delete(OrderEntry.ORDERS_URI,null, null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void insertDummy() {

        ContentValues values = new ContentValues();
        values.put(OrderEntry.COLUMN_NAME, "condoms");
        values.put(OrderEntry.COLUMN_PRICE, "45");
        values.put(OrderEntry.COLUMN_QUANTITY, "23");
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pro1);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
//        byte[] imageByteArray = bos.toByteArray();
   //     values.put(OrderEntry.COLUMN_IMAGE, imageByteArray);

        values.put(OrderEntry.COLUMN_IMAGE, R.drawable.waffles);
        values.put(OrderEntry.COLUMN_SUPPLIER_EMAIL, "shubham013@live.com");
        values.put(OrderEntry.COLUMN_SUPPLIER_NAME, "shubham");
        values.put(OrderEntry.COLUMN_SUPPLIER_PHONE, "9041908421");
        getContentResolver().insert(OrderEntry.ORDERS_URI, values);

        values = new ContentValues();
        values.put(OrderEntry.COLUMN_NAME, "waffles");
        values.put(OrderEntry.COLUMN_PRICE, "4");
        values.put(OrderEntry.COLUMN_QUANTITY, "223");
       // values.put(OrderEntry.COLUMN_IMAGE, R.drawable.waffles);
        values.put(OrderEntry.COLUMN_SUPPLIER_EMAIL, "shubham013@live.com");
        values.put(OrderEntry.COLUMN_SUPPLIER_NAME, "shubham");
        values.put(OrderEntry.COLUMN_SUPPLIER_PHONE, "9041908421");
        getContentResolver().insert(OrderEntry.ORDERS_URI, values);

        values = new ContentValues();
        values.put(OrderEntry.COLUMN_NAME, "ice-cream");
        values.put(OrderEntry.COLUMN_PRICE, "5");
        values.put(OrderEntry.COLUMN_QUANTITY, "2");
        //values.put(OrderEntry.COLUMN_IMAGE, R.drawable.ice);
        values.put(OrderEntry.COLUMN_SUPPLIER_EMAIL, "shubham013@live.com");
        values.put(OrderEntry.COLUMN_SUPPLIER_NAME, "shubham");
        values.put(OrderEntry.COLUMN_SUPPLIER_PHONE, "9041908421");
        getContentResolver().insert(OrderEntry.ORDERS_URI, values);

    }

}
