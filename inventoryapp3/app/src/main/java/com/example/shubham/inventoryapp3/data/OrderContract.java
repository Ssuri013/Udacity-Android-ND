package com.example.shubham.inventoryapp3.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shubham on 2/26/2017.
 */

public class OrderContract {

    public static final String CONTENT_AUTHORITY = "com.example.shubham.inventoryapp3";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ORDERS = "orders";


    public static final class OrderEntry implements BaseColumns {

        public static final Uri ORDERS_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ORDERS);
        public static final String TABLE_NAME = "orders";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

    }

}
