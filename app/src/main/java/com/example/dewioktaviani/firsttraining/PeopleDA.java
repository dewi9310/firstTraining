package com.example.dewioktaviani.firsttraining;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dewi.oktaviani on 16/03/2019.
 */

public class PeopleDA {
    private static final String TABLE_CONTACTS = "People";
    public PeopleDA(SQLiteDatabase db){
        People dt = new People();
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
                +TABLE_CONTACTS + "( " +dt.Property_ID + " TEXT PRIMARY KEY," + dt.Property_Name + " TEXT NULL, " + dt.Property_Gender + " TEXT NULL, " + dt.Property_Address + " TEXT NULL)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    public void DropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    public void SaveDataPeople(SQLiteDatabase db, People data){
        People dt = new People();
        ContentValues cv = new ContentValues();
        cv.put(dt.Property_ID, String.valueOf(data.getIntId()));
        cv.put(dt.Property_Name, String.valueOf(data.getTxtName()));
        cv.put(dt.Property_Gender, String.valueOf(data.getTxtGender()));
        cv.put(dt.Property_Address, String.valueOf(data.getTxtAddress()));
        if (data.getIntId() == 0){
            db.insert(TABLE_CONTACTS, null, cv);
        } else {
            db.replace(TABLE_CONTACTS, null, cv);
        }
    }
    public void DeleteDataPeople(SQLiteDatabase db, int intId){
        People dt = new People();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_ID + "='" + intId + "'");
    }
    public List<People> GetAllData(SQLiteDatabase db){
        List<People> contactList = new ArrayList<People>();
        People dt = new People();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " ORDER BY intId ASC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                People contact = new People();
                contact.setIntId(cursor.getInt(0));
                contact.setTxtName(cursor.getString(1));
                contact.setTxtGender(cursor.getString(2));
                contact.setTxtAddress(cursor.getString(3));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    public People GetPeopleById (SQLiteDatabase db, int intId){
        People dt = new People();
        String selectQuery = "Select " + dt.Property_All + " FROM " + TABLE_CONTACTS + " WHERE " + dt.Property_ID + "='" + intId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        People contact = null;
        if (cursor.moveToFirst()){
            do {
                contact = new People();
                contact.setIntId(cursor.getInt(0));
                contact.setTxtName(cursor.getString(1));
                contact.setTxtGender(cursor.getString(2));
                contact.setTxtAddress(cursor.getString(3));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }
}
