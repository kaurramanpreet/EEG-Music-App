package com.example.youtbe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "songs.db";
    private SQLiteDatabase db;

    public databasehandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_HAPPY = "CREATE TABLE " + database.happy.TABLE_NAME + " ( " + database.happy.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                database.happy.COL_2 + " TEXT ); ";
        final String SQL_CREATE_SAD = "CREATE TABLE " + database.sad.TABLE_NAME + " ( " + database.sad.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                database.sad.COL_2 + " TEXT ); ";
        final String SQL_CREATE_USERS = "CREATE TABLE " + database.users.TABLE_NAME + " ( " + database.users.COL_1 + " TEXT PRIMARY KEY , " +
                database.users.COL_2 + " TEXT, "+database.users.COL_3+" TEXT ); ";

        db.execSQL(SQL_CREATE_HAPPY);
        db.execSQL(SQL_CREATE_SAD);
        db.execSQL(SQL_CREATE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData_happy(String url) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.example.youtbe.database.happy.COL_2, url);

        long success = database.insert(com.example.youtbe.database.happy.TABLE_NAME, null, contentValues);
        if (success == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertData_sad(String url) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.example.youtbe.database.happy.COL_2, url);

        long success = database.insert(com.example.youtbe.database.sad.TABLE_NAME, null, contentValues);
        if (success == -1) {
            return false;
        } else {
            return true;
        }
    }
    public String getData_happy(int id) {
        Cursor cursor =db.rawQuery("SELECT "+ database.happy.COL_2+" FROM "+ database.happy.TABLE_NAME+" WHERE "+ database.happy.COL_1+" =? ",new String[]{String.valueOf(id)});
        String url="";
        if(cursor.moveToFirst())
        {
            do {
                url = cursor.getString(cursor.getColumnIndex(database.happy.COL_2));
            } while (cursor.moveToNext()) ;

            cursor.close();
        }
        return url;

    }
    public String getData_sad(int id) {
        Cursor cursor =db.rawQuery("SELECT "+ database.sad.COL_2+" FROM "+ database.sad.TABLE_NAME+" WHERE "+ database.sad.COL_1+" =? ",new String[]{String.valueOf(id)});
        String url="";
        if(cursor.moveToFirst())
        {
            do {
                url = cursor.getString(cursor.getColumnIndex(database.sad.COL_2));
            } while (cursor.moveToNext()) ;

            cursor.close();
        }
        return url;
    }
    public boolean insertuser(String username, String name, String password)
    {
        SQLiteDatabase database1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(com.example.youtbe.database.users.COL_1,username);
        contentValues.put(com.example.youtbe.database.users.COL_2,name);
        contentValues.put(com.example.youtbe.database.users.COL_3,password);

        long success = database1.insert(database.users.TABLE_NAME,null,contentValues);
        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean logIn(String username,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ database.users.TABLE_NAME+" where "+ database.users.COL_1+" =? and "+database.users.COL_3+" =? " ,new String [] {username,password});
        if(cursor.getCount()>0)
        {
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

}
