package com.phoenix.mdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dahlia on 10/1/16.
 */
public class MDBHelper extends SQLiteOpenHelper {
    MDBHelper(Context c){
        super(c, "ali.db", null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }
}
