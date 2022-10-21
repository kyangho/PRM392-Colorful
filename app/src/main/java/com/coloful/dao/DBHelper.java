package com.coloful.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coloful.constant.Constant;
import com.coloful.model.Account;

import java.util.UUID;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "DB_QUIZ";
    private static final int version = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlAccount = "CREATE TABLE IF NOT EXISTS account (id TEXT primary key, username TEXT not null, password TEXT not null, email TEXT not null, dob TEXT not null)";
        sqLiteDatabase.execSQL(sqlAccount);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // Action with table account
    public Boolean insertAccount(Account account) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.Account.ID.getValue(), account.getId());
        contentValues.put(Constant.Account.USERNAME.getValue(), account.getUsername());
        contentValues.put(Constant.Account.PASSWORD.getValue(), account.getPassword());
        contentValues.put(Constant.Account.EMAIL.getValue(), account.getEmail());
        contentValues.put(Constant.Account.DOB.getValue(), account.getDob());
        long result = sqLiteDatabase.insert(Constant.Account.TABLE_NAME.getValue(), null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkUsernameAndEmail(Account account) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where username = ? or email = ?",
                new String[]{account.getUsername(), account.getEmail()});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkAccount(Account account) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where username = ? and password = ?",
                new String[]{account.getUsername(), account.getPassword()});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
