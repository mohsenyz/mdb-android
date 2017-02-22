package com.phoenix.mdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dahlia on 10/1/16.
 */


/**
 *     CREATED BY MOHSEN YAZDANI
 */


public class MDBRecord<T extends MDBRecord> {
    public Long id;
    public MDBRecord(){
        if (MDB.DB == null){
            MDB.DB = new MDBHelper(MDB.instance);
        }
    }


    public Long update(){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        Field[] f = this.getClass().getDeclaredFields();
        for (Field fs : f){
            Class type = fs.getType();
            String name = fs.getName();
            try {
                if (type == String.class){
                    contentValues.put(name, (String) fs.get(this));
                }
                if (type == int.class){
                    contentValues.put(name, (int) fs.get(this));
                }
                if (type == long.class){
                    contentValues.put(name, (long) fs.get(this));
                }
                if (type == double.class){
                    contentValues.put(name, (double) fs.get(this));
                }

                if (type == float.class){
                    contentValues.put(name, (float) fs.get(this));
                }

                if (type == boolean.class){
                    contentValues.put(name, (boolean) fs.get(this));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        db.update(this.getClass().getSimpleName(), contentValues, "_id = " + this.id, null);
        db.close();
        return this.id;
    }
    public Long save(){
        if (this.id != null && this.id != 0)
            return update();
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        Field[] f = this.getClass().getDeclaredFields();
        for (Field fs : f){
            Class type = fs.getType();
            String name = fs.getName();
            try {
                if (type == String.class){
                    contentValues.put(name, (String) fs.get(this));
                }
                if (type == int.class){
                    contentValues.put(name, (int) fs.get(this));
                }
                if (type == long.class){
                    contentValues.put(name, (long) fs.get(this));
                }
                if (type == double.class){
                    contentValues.put(name, (double) fs.get(this));
                }

                if (type == float.class){
                    contentValues.put(name, (float) fs.get(this));
                }

                if (type == boolean.class){
                    contentValues.put(name, (boolean) fs.get(this));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        long res = db.insert(this.getClass().getSimpleName(), null, contentValues);
        this.id = res;
        db.close();
        return res;
    }



    public List<T> listAll(){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + this.getClass().getSimpleName(), null);
        List a = new ArrayList();
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Object ab = null;
            try {
                ab = this.getClass().getConstructors()[0].newInstance();
                Field[] f = this.getClass().getDeclaredFields();
                Field fas = this.getClass().getField("id");

                fas.set(ab, c.getLong(c.getColumnIndex("_id")));
                for (Field fa : f){
                    if (fa.getType() == String.class)
                        fa.set(ab, c.getString(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == int.class)
                        fa.set(ab, c.getInt(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == double.class)
                        fa.set(ab, c.getDouble(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == float.class)
                        fa.set(ab, c.getFloat(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == boolean.class){
                        if (c.getInt(c.getColumnIndex(fa.getName())) == 0)
                            fa.set(ab, false);
                        else
                            fa.set(ab, true);
                    }
                    if (fa.getType() == long.class)
                        fa.set(ab, c.getLong(c.getColumnIndex(fa.getName())));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            a.add(this.getClass().cast(ab));
            c.moveToNext();
        }


        db.close();
        return a;
    }


    public List<T> list(String where, String... cal){

        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + this.getClass().getSimpleName() + " where " + where, cal);
        List a = new ArrayList();
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Object ab = null;
            try {
                ab = this.getClass().getConstructors()[0].newInstance();
                Field[] f = this.getClass().getDeclaredFields();
                Field fas = this.getClass().getField("id");
                fas.set(ab, c.getLong(c.getColumnIndex("_id")));
                for (Field fa : f){
                    if (fa.getType() == String.class)
                        fa.set(ab, c.getString(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == int.class)
                        fa.set(ab, c.getInt(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == double.class)
                        fa.set(ab, c.getDouble(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == float.class)
                        fa.set(ab, c.getFloat(c.getColumnIndex(fa.getName())));
                    if (fa.getType() == boolean.class){
                        if (c.getInt(c.getColumnIndex(fa.getName())) == 0)
                            fa.set(ab, false);
                        else
                            fa.set(ab, true);
                    }
                    if (fa.getType() == long.class)
                        fa.set(ab, c.getLong(c.getColumnIndex(fa.getName())));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            a.add(this.getClass().cast(ab));
            c.moveToNext();
        }

        db.close();
        return a;
    }


    public void delete(){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        db.delete(this.getClass().getSimpleName(), "_id = ?", new String[]{"" + this.id});
        db.close();
    }


    public void delete(String where, String... cal){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        db.delete(this.getClass().getSimpleName(), where, cal);
        db.close();
    }

    public Cursor rawQuery(String query){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c;
    }


    public void exec(String sql){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }


    public T findById(long id){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + this.getClass().getSimpleName() + " where _id = " + id , null);
        if (c.getCount() == 0)return null;
        c.moveToFirst();
        Object ab = null;
        try {
            ab = this.getClass().getConstructors()[0].newInstance();
            Field[] f = this.getClass().getDeclaredFields();
            Field fas = this.getClass().getField("id");
            fas.set(ab, c.getLong(c.getColumnIndex("_id")));
            for (Field fa : f){
                if (fa.getType() == String.class)
                    fa.set(ab, c.getString(c.getColumnIndex(fa.getName())));
                if (fa.getType() == int.class)
                    fa.set(ab, c.getInt(c.getColumnIndex(fa.getName())));
                if (fa.getType() == double.class)
                    fa.set(ab, c.getDouble(c.getColumnIndex(fa.getName())));
                if (fa.getType() == float.class)
                    fa.set(ab, c.getFloat(c.getColumnIndex(fa.getName())));
                if (fa.getType() == boolean.class){
                    if (c.getInt(c.getColumnIndex(fa.getName())) == 0)
                        fa.set(ab, false);
                    else
                        fa.set(ab, true);
                }
                if (fa.getType() == long.class)
                    fa.set(ab, c.getLong(c.getColumnIndex(fa.getName())));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return (T)this.getClass().cast(ab);
    }

    public long count(){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + this.getClass().getSimpleName(), null);
        db.close();
        return (long)c.getCount();
    }

    public long count(String where, String... cal){
        SQLiteDatabase db = MDB.DB.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + this.getClass().getSimpleName() + " where " + where, cal);
        db.close();
        return (long)c.getCount();
    }

}
