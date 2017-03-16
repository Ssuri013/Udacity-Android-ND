package com.example.shubham.habittracker;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shubham on 2/8/2017.
 */

import com.example.shubham.habittracker.HabitTrackerContract.HabitContract;
public class HabitTrackerProvider extends ContentProvider {

    HabitDBHelper helper;
    @Override
    public boolean onCreate() {
        helper = new HabitDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cr = db.query(HabitContract.TABLE_NAME,null,null,null,null,null,null);//queryAll
        return cr;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e("insertHabit","reached");
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(HabitContract.TABLE_NAME, null, values);
        Log.e("id of entry is",id + " ");
        if(id == -1){
            Toast.makeText(getContext(), "Not Inserted", Toast.LENGTH_SHORT);
            return null;
        }
        Log.e("insertOrder","ended");
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();


        return database.delete(HabitContract.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
