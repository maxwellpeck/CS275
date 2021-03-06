package com.example.cs275;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Records Database variables
    public static final String DATABASE_NAME = "Records.db";
    public static final String TABLE_NAME = "Trip_Tracker_DB";

    //Fields for database
//    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String SURVEYTIME = "SURVEYTIME";
    public static final String HOMELOCALITY = "HOMELOCALITY";
    public static final String HOMELATITUDE = "HOMELATITUDE";
    public static final String HOMELONGITUDE = "HOMELONGITUDE";

//    public static final String SURVEYGRAB = "SURVEYGRAB";
//    public static final String GENRE = "GENRE";
//    public static final String DESCRIPTION = "DESCRIPTION";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, SURVEYTIME TEXT, HOMELOCALITY TEXT, HOMELATITUDE TEXT, HOMELONGITUDE TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String email, String surveyTime, String homeLocality, String homeLatitude, String homeLongitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(SURVEYTIME, surveyTime);
        contentValues.put(HOMELOCALITY, homeLocality);
        contentValues.put(HOMELATITUDE, homeLatitude);
        contentValues.put(HOMELONGITUDE, homeLongitude);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Integer deleteData(String row) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{row});
    }

    public Integer deleteRecord(String row, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        do{
            if(id == cursor.getInt(0)){
                return db.delete(TABLE_NAME, "id=?", new String[]{row});
            }
        } while(cursor.moveToNext());
        return -1;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

}
