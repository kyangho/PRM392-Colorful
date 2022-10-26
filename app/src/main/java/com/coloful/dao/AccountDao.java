package com.coloful.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coloful.activity.ChangeEmailActivity;
import com.coloful.activity.ChangePasswordActivity;
import com.coloful.constant.Constant;
import com.coloful.model.Account;

public class AccountDao {
    DBHelper db;
    SQLiteDatabase sqLiteDatabase;

    public Account checkAccount(Context context, Account account) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where username = ? and password = ?",
                new String[]{account.getUsername(), account.getPassword()});
        Account acc = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            acc = new Account();
            acc.setId(cursor.getInt(0));
            acc.setUsername(cursor.getString(1));
            acc.setEmail(cursor.getString(3));
            acc.setDob(cursor.getString(4));
        }
        cursor.close();
        sqLiteDatabase.close();
        return acc;
    }

    public boolean checkUsernameAndEmail(Context context, Account account) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where username = ? or email = ?",
                new String[]{account.getUsername(), account.getEmail()});
        if (cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return true;
        } else {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
    }

    public boolean checkEmailExist(Context context, String email) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where email = ?",
                new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return true;
        } else {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
    }

    public boolean checkUsernameExisted(Context context, String username) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from account where username = ?",
                new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.close();
            sqLiteDatabase.close();
            return true;
        } else {
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
    }

    // Action with table account
    public boolean insertAccount(Context context, Account account) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.Account.USERNAME.getValue(), account.getUsername());
        contentValues.put(Constant.Account.PASSWORD.getValue(), account.getPassword());
        contentValues.put(Constant.Account.EMAIL.getValue(), account.getEmail());
        contentValues.put(Constant.Account.DOB.getValue(), account.getDob());
        long result = sqLiteDatabase.insert(Constant.Account.TABLE_NAME.getValue(), null, contentValues);
        sqLiteDatabase.close();
        if (result <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateEmail(Context context, String newEmail, Integer id) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.Account.EMAIL.getValue(), newEmail);
        int row = sqLiteDatabase.update(Constant.Account.TABLE_NAME.getValue(), values, "id=?",
                new String[]{id.toString()});
        sqLiteDatabase.close();
        return (row > 0);
    }

    public boolean updateUsername(Context context, String newUsername, Integer id) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.Account.USERNAME.getValue(), newUsername);
        int row = sqLiteDatabase.update(Constant.Account.TABLE_NAME.getValue(), values, "id=?",
                new String[]{id.toString()});
        sqLiteDatabase.close();
        return (row > 0);
    }

    public boolean updatePassword(Context context, String newPass, Integer id) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.Account.PASSWORD.getValue(), newPass);
        int row = sqLiteDatabase.update(Constant.Account.TABLE_NAME.getValue(), values, "id=?",
                new String[]{id.toString()});
        sqLiteDatabase.close();
        return (row > 0);
    }

    public Account getAccountForQuiz(Context context, int accountId) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM account WHERE id=?",
                new String[]{String.valueOf(accountId)});
        Account acc = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            acc = new Account();
            acc.setId(cursor.getInt(0));
            acc.setUsername(cursor.getString(1));
            acc.setEmail(cursor.getString(3));
            acc.setDob(cursor.getString(4));
        }
        cursor.close();
        sqLiteDatabase.close();
        return acc;
    }
}
