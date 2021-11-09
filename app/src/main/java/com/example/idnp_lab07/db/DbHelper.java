package com.example.idnp_lab07.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "lab07.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_RUTAS = "t_rutas";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "contraseña TEXT NOT NULL," +
                "correo_electronico TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_RUTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "latitud FLOAT NOT NULL," +
                "longitud FLOAT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USUARIOS);
        onCreate(db);
    }
}
