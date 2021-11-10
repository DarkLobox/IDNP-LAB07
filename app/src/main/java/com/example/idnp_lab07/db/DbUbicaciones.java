package com.example.idnp_lab07.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUbicaciones extends DbHelper{

    Context context;

    public DbUbicaciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUbicacion(String x, String y, String z){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("X",x);
            values.put("Y",y);
            values.put("Z",z);

            id = db.insert(TABLE_UBICACIONES, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public boolean actualizarUbicacion(String id, String x, String y, String z){
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("X",x);
            values.put("Y",y);
            values.put("Z",z);
            String[] args = new String []{id};
            db.update(TABLE_UBICACIONES, values, "id=?", args);
            return true;
        }catch (Exception e){
            e.toString();
        }
        return false;
    }

    public boolean eliminarUbicacion(String id){
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String[] args = new String []{id};
            db.delete(TABLE_UBICACIONES, "id=?", args);
            return true;
        }catch (Exception e){
            e.toString();
        }
        return false;
    }

    public Cursor obtenerIds(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT id FROM t_ubicaciones",null);
    }

    public Cursor obtenerUbicaciones(String id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM t_ubicaciones WHERE id = " + id,null);
    }
}
