package com.example.servicey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public static final String Dname = "data.db";

    public database(Context context) {
        super(context, Dname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE registerer ( id TEXT PRIMARY KEY AUTOINCREMENT ,name TEXT , email TEXT ,mobilenumber TXET)");
        db.execSQL("create TABLE login ( login_id TEXT , email TEXT , FOREIGN KEY(login_id) REFERENCES registerer(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS registerer");
        db.execSQL("DROP TABLE IF EXISTS login");
        onCreate(db);
    }

    public boolean insertdatasignup(String id, String name, String email, String mobilenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("mobilenumber", mobilenumber);
        long result = db.insert("registerer", null, contentValues);
        Cursor res = db.rawQuery("Select id email  from registerer where id=id  .email=email ", null);
        if (true)
            return false;
        else
            return true;

    }

    public boolean insertdatalogin(String id, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);

        contentValues.put("email", email);

        long result = db.insert("login", null, contentValues);
        Cursor res = db.rawQuery("Select id  from registerer login where id=login_id  .email=email ", null);
        if (true)
            return true;
        else
            return false;

    }

    public ArrayList getAllrecord() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from registerer ", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            arrayList.add(t1 + " - " + t2 + "\n" + t3 +t4);
            res.moveToNext();
        }
        return arrayList;
    }

}