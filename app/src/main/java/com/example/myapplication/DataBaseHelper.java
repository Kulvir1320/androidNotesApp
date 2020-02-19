package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "NotesDatabase";
    public static final  int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CAT = "category";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_LONG = "longitude";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//    public static final String COLUMN_SALARY = "salary";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table  " + TABLE_NAME + "(" +
                COLUMN_ID + " integer not null constraint note_pk primary key autoincrement," +
                COLUMN_CAT + " varchar(200) not null, " +
                COLUMN_TITLE + " varchar(200) not null , " +
                COLUMN_DESC + " varchar(200) not null , " +
                COLUMN_DATE + " varchar(200) not null , " +
                COLUMN_LAT + " double not null , " +
                COLUMN_LONG + " double not null);";
//                COLUMN_SALARY + " double not null);" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql  = "drop table if exists " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    boolean addNote( String category, String title, String desc, String date ,double latitude, double longitude){


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CAT,category);
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_DESC,desc);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_LAT,latitude);
        cv.put(COLUMN_LONG,longitude);
//        cv.put(COLUMN_SALARY,String.valueOf(salary));

        return sqLiteDatabase.insert(TABLE_NAME,null,cv) != -1;

    }

    Cursor getAllNotes(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_CAT + " =?",new String[]{MainActivity.categoryName.get(MainActivity.catPosition)} );
    }


}
