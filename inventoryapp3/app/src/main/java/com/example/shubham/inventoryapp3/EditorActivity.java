package com.example.shubham.inventoryapp3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shubham.inventoryapp3.data.OrderContract;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class EditorActivity extends AppCompatActivity {
    private static final int FILE_SELECT_CODE = 2;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 0;
    boolean infoItemHasChanged = true;
    EditText name;
    EditText price;
    EditText quantity;
    EditText supplierName;
    EditText supplierEmail;
    EditText supplierPhone;
    ImageView productLooks;
    Button selectImg;
    Button asc;
    Button des;
    boolean whichMethodToCall =false;//add
    Uri currentOrder;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        selectImg = (Button) findViewById(R.id.select_image);

        name = (EditText) findViewById(R.id.editor_name);
        price = (EditText) findViewById(R.id.editor_price);
        quantity = (EditText) findViewById(R.id.editor_quantity);
        supplierName = (EditText) findViewById(R.id.editor_name);
        supplierEmail = (EditText) findViewById(R.id.editor_supplier_email);
        supplierPhone = (EditText) findViewById(R.id.editor_supplier_phone);
        productLooks = (ImageView) findViewById(R.id.product_looks);
        asc = (Button) findViewById(R.id.inc);
        des = (Button) findViewById(R.id.dec);
        currentOrder = getIntent().getData();
        if(currentOrder==null){
            setTitle("Add pet");
        }
        else{
            whichMethodToCall = true; //update

            setTitle("Edit Pet");
            Cursor cursor = getContentResolver().query(currentOrder,null,null,null,null);
            cursor.moveToFirst();



            String cName = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME));
            String cPrice = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE));
            String cAvail =  cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY));
            String sName = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME));
            String sEmail = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE));
            String sPhone =  cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY));
            byte[] imageByteArray = cursor.getBlob(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_IMAGE));
            name.setText(cName);
            price.setText(cPrice);
            quantity.setText(cAvail);
            supplierName.setText(sName);
            supplierEmail.setText(sEmail);
            supplierPhone.setText(sPhone);
//            Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
//            productLooks.setImageBitmap(bmp);
            //images were the most difficult part
//            path = cursor.getString(cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_IMAGE));
//            try {
//                ContentResolver cr = getContentResolver();
//                Uri uri = Uri.parse(path);
// //               Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, Uri.parse(path));
// //               InputStream is = getContentResolver().openInputStream(uri);
////                Bitmap bitmap = BitmapFactory.decodeStream(is);
//                Bitmap _bit = BitmapFactory.decodeStream(
//                        getContentResolver().openInputStream(uri),null,null);
//                //productLooks.setImageBitmap(bitmap);
//            }
//            catch (Exception e){
//                Log.e("Image", path);
//                e.printStackTrace();
//            }
        }
        asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(quantity.getText().toString().trim());
                x++;
                quantity.setText(x + " ");
            }
        });
        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(quantity.getText().toString().trim());
                if(x<1){return;}
                x--;
                quantity.setText(x + " ");
            }
        });
        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonImageClick();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!infoItemHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("insaved changes");
        builder.setPositiveButton("Discard", discardButtonClickListener);
        builder.setNegativeButton("keep editing", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_save:
                saveData();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_item:
                deleteItem();
                return true;
            case R.id.action_order_more:
                contactSupplier();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void saveProduct() {
//        String nameString = mNameEditText.getText().toString().trim();
//        String countString = mCountEditText.getText().toString().trim();
//        String priceString = mPriceEditText.getText().toString().trim();
//
//        if (TextUtils.isEmpty(nameString) || TextUtils.isEmpty(countString)
//                || TextUtils.isEmpty(priceString)) {
//            Toast.makeText(this,"Please fill out all values", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int count = Integer.valueOf(countString);
//        double price = Double.valueOf(priceString);
//
//        if(count < 0) {
//            Toast.makeText(this,"You must input a real number for the count field.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if(price < 0.0) {
//            Toast.makeText(this,"You must input a real price.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if(mImageView.getDrawable() == null) {
//            Toast.makeText(this,"You must upload an image.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Bitmap imageBitMap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        imageBitMap.compress(Bitmap.CompressFormat.PNG, 100, bos);
//        byte[] imageByteArray = bos.toByteArray();
//
//
//        // Create a ContentValues object where column names are the keys,
//        // and pet attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//        values.put(OrderEntry.COLUMN_PRODUCT_NAME, nameString);
//        values.put(ProductEntry.COLUMN_PRODUCT_COUNT, count);
//        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);
//        values.put(ProductEntry.COLUMN_PRODUCT_IMAGE, imageByteArray);
//
//        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
//
//        // Show a toast message depending on whether or not the insertion was successful
//        if (newUri == null) {
//            // If the row ID is -1, then there was an error with insertion.
//            Toast.makeText(this, getString(R.string.editor_insert_failed), Toast.LENGTH_SHORT).show();
//        } else {
//            // Otherwise, the insertion was successful and we can display a toast with the row ID.
//            Toast.makeText(this, getString(R.string.editor_insert_successful), Toast.LENGTH_SHORT).show();
//            finish();
//        }
//
//    }

    void saveData(){

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_SUPPLIER_EMAIL, supplierEmail.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_SUPPLIER_NAME, supplierName.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_SUPPLIER_PHONE, supplierPhone.getText().toString());
        values.put(OrderContract.OrderEntry.COLUMN_IMAGE , path );
        if(productLooks.getDrawable() == null) {
            Toast.makeText(this,"You must upload an image.", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap imageBitMap = ((BitmapDrawable)productLooks.getDrawable()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imageBitMap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] imageByteArray = bos.toByteArray();

        values.put(OrderContract.OrderEntry.COLUMN_IMAGE, imageByteArray);
        if(!whichMethodToCall) { //add

            Uri uri = getContentResolver().insert(OrderContract.OrderEntry.ORDERS_URI, values);
        }
        else{ //update
            getContentResolver().update(currentOrder,values,null,null);
        }
        finish();
    }

    void contactSupplier(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditorActivity.this);
        builder1.setMessage("Contact dealer via");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Phone",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + supplierPhone.getText().toString().trim()));
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "E-mail",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",supplierEmail.getText().toString().trim(), null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    void deleteItem(){
        if(whichMethodToCall){
            getContentResolver().delete(currentOrder,null,null);
        }
        finish();
    }

    private void buttonImageClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), FILE_SELECT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {

                try {
                    Uri imageUri = data.getData();

                   // path = imageUri.toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    productLooks.setImageBitmap(bitmap);
                    Log.e("hey", " " + path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
