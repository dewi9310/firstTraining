package com.example.dewioktaviani.firsttraining;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by dewi.oktaviani on 16/03/2019.
 */

public class PeopleBL extends Database{
    public void SaveData(People dt){
        SQLiteDatabase _db = getDb();
        PeopleDA _PeopleDA = new PeopleDA(_db);
        _PeopleDA.SaveDataPeople(_db, dt);
        _db.close();
    }

    public List<People> GetAllData(){
        SQLiteDatabase _db = getDb();
        PeopleDA _PeopleDA = new PeopleDA(_db);
        List<People> dt = _PeopleDA.GetAllData(_db);
        _db.close();
        return dt;
    }

    public People GetPeopleById(int intId){
        SQLiteDatabase _db = getDb();
        PeopleDA _PeopleDA = new PeopleDA(_db);
        People dt = _PeopleDA.GetPeopleById(_db, intId);
        _db.close();
        return dt;
    }

    public void DeletePeople(int inId){
        SQLiteDatabase _db = getDb();
        PeopleDA _PeopleDA = new PeopleDA(_db);
        _PeopleDA.DeleteDataPeople(_db, inId); ;
        _db.close();
    }
}
