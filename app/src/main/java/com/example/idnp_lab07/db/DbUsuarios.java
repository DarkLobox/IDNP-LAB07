package com.example.idnp_lab07.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuarios extends DbHelper{

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String contraseña, String correo_electronico){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("contraseña",contraseña);
            values.put("correo_electronico",correo_electronico);

            id = db.insert(TABLE_USUARIOS, null, values);
        }catch (Exception e){
            e.toString();
        }
        return id;
    }
}
