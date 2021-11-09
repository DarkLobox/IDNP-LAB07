package com.example.idnp_lab07.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbRutas extends DbHelper{

    Context context;

    public DbRutas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarRutas(String lati, String log){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("latitud",lati);
            values.put("longitud",log);

            id = db.insert(TABLE_RUTAS, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }

    public boolean actualizarRutas(String id, String nombre, String contraseña){
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("latitud",nombre);
            values.put("longitud",contraseña);
            String[] args = new String []{id};
            db.update(TABLE_RUTAS, values, "id=?", args);
            return true;
        }catch (Exception e){
            e.toString();
        }
        return false;
    }

    public boolean eliminarRutas(String id){
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            String[] args = new String []{id};
            db.delete(TABLE_RUTAS, "id=?", args);
            return true;
        }catch (Exception e){
            e.toString();
        }
        return false;
    }

    public Cursor obtenerIds(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT id FROM t_rutas",null);
    }

    public Cursor obtenerRutas(String id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM t_rutas WHERE id = " + id,null);
    }
}
