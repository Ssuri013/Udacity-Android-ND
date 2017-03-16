package com.example.shubham.inventoryapp3.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shubham on 2/26/2017.
 */
import com.example.shubham.inventoryapp3.data.OrderContract.OrderEntry;
public class OrderProvider extends ContentProvider {

    private static final String LOG_NAME = OrderProvider.class.getSimpleName();
    private static final int ORDERS = 100;
    private static final int ORDERS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        sUriMatcher.addURI(OrderContract.CONTENT_AUTHORITY,OrderContract.PATH_ORDERS,ORDERS);
        sUriMatcher.addURI(OrderContract.CONTENT_AUTHORITY,OrderContract.PATH_ORDERS + "/#", ORDERS_ID);
    }

    OrderDbHelper mDbHelper ;

    @Override
    public boolean onCreate() {
        mDbHelper = new OrderDbHelper(getContext());
        return false;
    }

    @Nullable   //MAYBE ERROR   SOMETHING WAS IMPORTED
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Get readable database
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor ;

        // Figure out if the URI matcher can match the URI to a specific code
        switch (sUriMatcher.match(uri)) {
            case ORDERS:
                cursor= db.query(OrderEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case ORDERS_ID:

                selection = OrderEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = db.query(OrderEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.e("Provider query", "count is:"+ cursor.getCount());
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                return insertOrder(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertOrder(Uri uri, ContentValues values) {
        Log.e("insertOrder","reached");
        mDbHelper = new OrderDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        long id = db.insert(OrderEntry.TABLE_NAME, null, values);
        Log.e("id of entry is",id + " ");
        if(id == -1){
            Toast.makeText(getContext(), "Not Inserted", Toast.LENGTH_SHORT);
            return null;
        }
        Log.e("insertOrder","ended");
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                // Delete all rows that match the selection and selection args
                getContext().getContentResolver().notifyChange(uri,null);
                return database.delete(OrderEntry.TABLE_NAME, selection, selectionArgs);
            case ORDERS_ID:
                // Delete a single row given by the ID in the URI
                selection = OrderEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                getContext().getContentResolver().notifyChange(uri,null);
                return database.delete(OrderEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                return updateOrder(uri, contentValues, selection, selectionArgs);
            case ORDERS_ID:
                selection = OrderEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateOrder(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateOrder(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int x = db.update(OrderEntry.TABLE_NAME,values,selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri,null);
        return x;
    }


}
