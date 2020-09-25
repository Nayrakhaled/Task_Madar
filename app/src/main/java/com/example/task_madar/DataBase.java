package com.example.task_madar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    private static final String databaseName = "MyDB";
    private static final int databaseVersionNumber = 1;


    public DataBase(Context context) {
        super(context, databaseName, null, databaseVersionNumber);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + UserDetail.TABLE_NAME + " (" +
                UserDetail.TEXT_ID + " INTEGER, " +
                UserDetail.TEXT_NAME + " TEXT, " +
                UserDetail.TEXT_TITLE + " TEXT, " +
                UserDetail.TEXT_GENDER + " TEXT, " +
                UserDetail.TEXT_AGE + " INTEGER " + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserDetail.TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(UserDetail detail) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserDetail.TEXT_NAME, detail.getName());
        values.put(UserDetail.TEXT_AGE, detail.getAge());
        values.put(UserDetail.TEXT_TITLE, detail.getTitle());
        values.put(UserDetail.TEXT_GENDER, detail.getGender());

        Log.d("aage", String.valueOf(detail.getAge()));
        Log.d("aage", String.valueOf(detail.getTitle()));
        Log.d("aage", String.valueOf(detail.getGender()));

        long result = database.insert(UserDetail.TABLE_NAME, null, values);

        Log.d("id", String.valueOf(result));
        Log.d("id", detail.getTitle());
        Log.d("id", String.valueOf(detail.getAge()));
        Log.d("id", detail.getGender());
        database.close();
        return result != -1;
    }

    public UserDetail getAllData(String name) {
        Log.d("mmmmm",name);
        SQLiteDatabase db = this.getReadableDatabase();
        UserDetail detail = new UserDetail();
        String query = "SELECT * FROM "+UserDetail.TABLE_NAME+" WHERE "+UserDetail.TEXT_NAME+" = '"+name+"'";
        Cursor cursor =  db.rawQuery(query, null);
        Log.d("num", query);
        Log.d("num", String.valueOf(cursor.getCount()));
        if (cursor!=null&& cursor.getCount()!=0) {
            cursor.moveToFirst();
                detail.setName(cursor.getString(cursor.getColumnIndex(UserDetail.TEXT_NAME)));
                detail.setTitle(cursor.getString(cursor.getColumnIndex(UserDetail.TEXT_TITLE)));
                detail.setGender(cursor.getString(cursor.getColumnIndex(UserDetail.TEXT_GENDER)));
                detail.setAge(cursor.getInt(cursor.getColumnIndex(UserDetail.TEXT_AGE)));

        }else {Log.d("ss","aaas");}
        Log.d("age", String.valueOf(detail.getAge()));
        db.close();
        return detail;
    }

        public int getProfilesCount() {
            String countQuery = "SELECT  * FROM " + UserDetail.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;
        }
}

