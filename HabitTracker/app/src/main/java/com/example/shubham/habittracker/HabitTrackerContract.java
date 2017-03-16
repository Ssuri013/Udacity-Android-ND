package com.example.shubham.habittracker;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by shubham on 2/8/2017.
 */

public class HabitTrackerContract {

    public static final String CONTENT_AUTHORITY = "com.example.shubham.habittracker";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_HABIT = "habit";



        public static final class HabitContract implements BaseColumns{

         public static final Uri HABIT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HABIT);
         public static final String TABLE_NAME = "routine";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EXERCISE = "exercise";
        public static final String COLUMN_SLEPT_FOR = "sleepHours";

        //TO SAVE space in table and make it easier
        public static final int EXERCISE_TRUE = 1;
        public static final int EXERCISE_FALSE = 0;
    }

}