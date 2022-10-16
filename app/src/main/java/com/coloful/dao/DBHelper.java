package com.coloful.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DB_QUIZ";
    private static final int version = 1;

    public DBHelper(Context context){
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlAccount = "CREATE TABLE IF NOT EXISTS account (id integer primary key, username TEXT not null, password TEXT not null, email TEXT not null)";
        sqLiteDatabase.execSQL(sqlAccount);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
