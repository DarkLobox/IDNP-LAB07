package com.example.idnp_lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.idnp_lab07.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crearBase(View view){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db!=null){
            Toast.makeText(MainActivity.this, "Base de Datos Creada", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "No se pudo crear la base de datos", Toast.LENGTH_LONG).show();
        }
    }

    public void vistaUsuarios(View view){
        Intent intent= new Intent (this, UsuariosActivity.class);
        startActivity(intent);
    }

    public void vistaRutas(View view){
        Intent intent= new Intent (this, RutasActivity.class);
        startActivity(intent);
    }

    public void vistaCalendario(View view){
        Intent intent= new Intent (this, CalendarioActivity.class);
        startActivity(intent);
    }

    public void vistaUbicaciones(View view){
        Intent intent= new Intent (this, UbicacionesActivity.class);
        startActivity(intent);
    }
}