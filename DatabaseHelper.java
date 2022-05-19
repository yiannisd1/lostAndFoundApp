package com.example.lostandfoundappfinal.data;

import static com.example.lostandfoundappfinal.util.Util.TYPEOFADVERT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lostandfoundappfinal.model.LostAndFound;
import com.example.lostandfoundappfinal.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ("
                + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.TYPEOFADVERT + " TEXT, "
                + Util.NAME + " TEXT, " + Util.PHONE + " TEXT, "
                + Util.DESCRIPTION + " TEXT, " + Util.DATE + " TEXT, "
                + Util.LOCATION + " TEXT );";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);

    }

    public boolean insertLostAndFound(LostAndFound lostAndFound) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TYPEOFADVERT, lostAndFound.getTypeOfAdvert());
        contentValues.put(Util.NAME, lostAndFound.getName());
        contentValues.put(Util.PHONE, lostAndFound.getPhone());
        contentValues.put(Util.DESCRIPTION, lostAndFound.getDescription());
        contentValues.put(Util.DATE, lostAndFound.getDate());
        contentValues.put(Util.LOCATION, lostAndFound.getLocation());

        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();

        if(newRowId==-1){
            return false;
        }else{
            return true;
        }


    }

    public boolean fetchLostAndFound(String typeOfAdvert, String name, String phone, String description, String date, String location) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.USER_ID}, TYPEOFADVERT + "=? and " + Util.NAME + "=? and " +
                        Util.PHONE + "=? and " + Util.DESCRIPTION + "=? and " + Util.DATE + "=? and " + Util.LOCATION + "=?",
                new String[]{typeOfAdvert, name, phone, description, date, location}, null, null, null);

        int numberOfRows = (cursor).getCount();
        db.close();

        if (numberOfRows > 0)
            return true;
        else
            return false;

    }


    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        return cursor;
    }

//    public ArrayList<String> fetchAllNames()

    public List<LostAndFound> fetchAllLostAndFound() {
        List<LostAndFound> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                LostAndFound lostAndFound = new LostAndFound();


                lostAndFound.setTypeOfAdvert((cursor.getString(1)));
                lostAndFound.setName(cursor.getString(2));
                lostAndFound.setPhone(cursor.getString(3));
                lostAndFound.setDescription(cursor.getString(4));
                lostAndFound.setDate(cursor.getString(5));
                lostAndFound.setLocation(cursor.getString(6));

                userList.add(lostAndFound);

            } while (cursor.moveToNext());
        }
        return userList;
    }

    public void deleteData (String typeOfAdvert, String name, String phone, String description, String date, String location)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL( " DELETE FROM " + Util.TABLE_NAME + " WHERE " + Util.NAME + " = '" + name + "' AND " + Util.PHONE
                + " = '" + phone + "' AND " + Util.DATE + " = '" + date + "'");
    }

}

