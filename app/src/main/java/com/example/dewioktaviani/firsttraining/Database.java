package com.example.dewioktaviani.firsttraining;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;

/**
 * Created by dewi.oktaviani on 16/03/2019.
 */

public class Database {
    String txtPathApp = "data" + File.separator + "data" + File.separator + "com.example.dewioktaviani.firsttraining" + File.separator + "databases" + File.separator;
    public SQLiteDatabase getDb(){
        SQLiteDatabase db;
        String txtDatabaseName = txtPathApp + "dewi";
        db = SQLiteDatabase.openOrCreateDatabase(txtDatabaseName, null);
        return db;
    }

    public void createFolderApp(){
        File appDir=new File(txtPathApp);

        if(!appDir.exists() && !appDir.isDirectory())
        {
            // create empty directory
            if (appDir.mkdirs())
            {
                Log.i("CreateDir","App dir created");
            }
            else
            {
                Log.w("CreateDir","Unable to create app dir!");
            }
        }
        else
        {
            Log.i("CreateDir","App dir already exists");
        }
    }
}

