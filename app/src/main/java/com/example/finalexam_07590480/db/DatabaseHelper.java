package com.example.finalexam_07590480.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD ="password";

    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + TABLE_USER + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NAME + " TEXT, "
                    + COL_USERNAME + " TEXT, "
                    + COL_PASSWORD + " TEXT )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
