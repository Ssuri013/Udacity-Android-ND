package com.example.android.pets.data;

import android.content.Context;
        import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.R;
import com.example.android.pets.data.PetContract.PetEntry;
public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODo: Fill out this method and return the list item view (instead of null)

        return LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO: Fill out this method
        TextView tv1 = (TextView) view.findViewById(R.id.name);
        TextView tv2 = (TextView) view.findViewById(R.id.summary);

        String name = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_NAME));
        String summ = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_BREED)) + "  " + cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_GENDER));
        tv1.setText(name);
        tv2.setText(summ);
    }
}