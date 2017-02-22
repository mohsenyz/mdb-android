package com.phoenix.mdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dahlia on 10/1/16.
 */
public class MDB {
    public static Context instance;
    public static MDBHelper DB;
    public static void init(Context c, Class...s){
        instance = c;
        if (DB == null){
            DB = new MDBHelper(c);
        }
        SQLiteDatabase db = DB.getWritableDatabase();
        for (Class a : s){
            String table = "create table if not exists " + a.getSimpleName() + " (" + getObjectFields(a) + ")";
            db.execSQL(table);
        }
        db.close();
    }


    private static String getObjectFields(Class o){
        Field[] fs = o.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field f : fs){
            String name = f.getName();
            Class type = f.getType();
            if (type == String.class){
                list.add(name + " text");
            }
            if (type == int.class){
                list.add(name + " integer");
            }
            if (type == long.class){
                list.add(name + " long");
            }
            if (type == double.class){
                list.add(name + " double");
            }

            if (type == float.class){
                list.add(name + " float");
            }

            if (type == boolean.class){
                list.add(name + " boolean");
            }
        }
        list.add("_id" + " integer primary key autoincrement");
        String res = "";
        for (String ab : list){
            res += ab + " , ";
        }
        return res.substring(0, res.length() - 2);
    }
}
