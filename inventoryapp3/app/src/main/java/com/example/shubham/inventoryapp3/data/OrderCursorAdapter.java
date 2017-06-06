package com.example.shubham.inventoryapp3.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shubham on 2/26/2017.
 */

 import com.example.shubham.inventoryapp3.R;

import static android.content.ContentValues.TAG;

public class OrderCursorAdapter extends CursorAdapter {


    public OrderCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }


    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView tv1 = (TextView) view.findViewById(R.id.name);
        TextView tv2 = (TextView) view.findViewById(R.id.price);
        TextView tv3 = (TextView) view.findViewById(R.id.avail);
        ImageView iv = (ImageView) view.findViewById(R.id.img);

        String name = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME));
        String price = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE));
        String avail =  cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY));
       // String path = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_IMAGE));
        final int productId = cursor.getInt(cursor.getColumnIndex(OrderContract.OrderEntry._ID));
        final int count = cursor.getInt(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY));
        //Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);


        Button btnBuy = (Button) view.findViewById(R.id.buy_button);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri itemUri = ContentUris.withAppendedId(OrderContract.OrderEntry.ORDERS_URI, productId);
                buyProduct(context, itemUri, count);
            }
        });


       // iv.setImageBitmap(bmp);
        tv1.setText(name);
        tv2.setText("Price: Rs " + price + "");
        tv3.setText("Available units:" + avail);
    }
    // Decrease product count by 1
    private void buyProduct(Context context, Uri itemUri, int currentCount) {
        int newCount = (currentCount >= 1) ? currentCount - 1 : 0;
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, newCount );
        context.getContentResolver().update(itemUri, values, null, null);
    }
}