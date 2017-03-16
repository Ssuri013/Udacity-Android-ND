package com.example.shubham.inventoryapp3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shubham on 2/26/2017.
 */
import com.example.shubham.inventoryapp3.data.OrderContract.OrderEntry;
public class OrderDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "inventory.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = OrderDbHelper.class.getSimpleName();

    public OrderDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " +
                OrderEntry.TABLE_NAME + "(" +
                OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OrderEntry.COLUMN_NAME + " TEXT ," +
                OrderEntry.COLUMN_PRICE + " TEXT ," +
                OrderEntry.COLUMN_QUANTITY + " INTEGER DEFAULT 0," +
                OrderEntry.COLUMN_SUPPLIER_NAME + " TEXT," +
                OrderEntry.COLUMN_SUPPLIER_PHONE + " TEXT," +
                OrderEntry.COLUMN_SUPPLIER_EMAIL + " TEXT," +
                OrderEntry.COLUMN_IMAGE + " BLOB" +
                ");";

        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
