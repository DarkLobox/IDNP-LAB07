package com.example.idnp_lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.idnp_lab07.db.DbHelper;
import com.example.idnp_lab07.db.DbUsuarios;

public class UsuariosActivity extends AppCompatActivity {

    EditText correo;
    EditText nombre;
    EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        correo = findViewById(R.id.inputCorreoElectronico);
        nombre = findViewById(R.id.inputNombre);
        contraseña = findViewById(R.id.inputContraseña);
    }

    public void insertar(View view){
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        long id = dbUsuarios.insertarUsuario(nombre.getText().toString(),contraseña.getText().toString(),correo.getText().toString());
        if(id>0){
            Toast.makeText(UsuariosActivity.this, "Usuario " + id + " creado", Toast.LENGTH_LONG).show();
            limpiarInputs();
        }
        else{
            Toast.makeText(UsuariosActivity.this, "No se pudo crear Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void actualizar(View view){

    }

    public void eliminar(View view){

    }

    public void cancelar(View view){

    }

    public void limpiarInputs(){
        correo.setText("");
        nombre.setText("");
        contraseña.setText("");
    }
}