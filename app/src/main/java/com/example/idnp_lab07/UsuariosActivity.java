package com.example.idnp_lab07;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.idnp_lab07.db.DbUsuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuariosActivity extends AppCompatActivity {
    Spinner ids;
    EditText correo;
    EditText nombre;
    EditText contraseña;
    List<String> listaIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        ids = findViewById(R.id.spId);
        correo = findViewById(R.id.inputCorreoElectronico);
        nombre = findViewById(R.id.inputNombre);
        contraseña = findViewById(R.id.inputContraseña);
        listaIds = new ArrayList<String>();
        llenarSpinner();

        ids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = ids.getSelectedItem().toString();
                if(seleccionado.compareTo("Ninguno")!=0){
                    llenarDatos(seleccionado);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void insertar(View view){
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        long id = dbUsuarios.insertarUsuario(nombre.getText().toString(),contraseña.getText().toString(),correo.getText().toString());
        if(id>0){
            Toast.makeText(UsuariosActivity.this, "Usuario " + id + " creado", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(UsuariosActivity.this, "No se pudo crear Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void actualizar(View view){
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        boolean actualizado = dbUsuarios.actualizarUsuario(ids.getSelectedItem().toString(),nombre.getText().toString(),contraseña.getText().toString(),correo.getText().toString());
        if(actualizado){
            Toast.makeText(UsuariosActivity.this, "Usuario actualizado", Toast.LENGTH_LONG).show();
            limpiarInputs();
            ids.setSelection(0);
        }
        else{
            Toast.makeText(UsuariosActivity.this, "No se pudo actualizar Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminar(View view){
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        boolean eliminado = dbUsuarios.eliminarUsuario(ids.getSelectedItem().toString());
        if(eliminado){
            Toast.makeText(UsuariosActivity.this, "Usuario eliminado", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(UsuariosActivity.this, "No se pudo eliminar Usuario", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelar(View view){
        limpiarInputs();
        ids.setSelection(0);
    }

    public void llenarSpinner(){
        listaIds.clear();
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        Cursor c = dbUsuarios.obtenerIds();
        listaIds.add("Ninguno");
        c.moveToPosition(-1);
        while(c.moveToNext()){
            listaIds.add(c.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaIds);
        ids.setAdapter(adapter);
    }

    public void llenarDatos(String seleccionado){
        DbUsuarios dbUsuarios = new DbUsuarios(UsuariosActivity.this);
        Cursor c = dbUsuarios.obtenerUsuario(seleccionado);
        c.moveToFirst();
        nombre.setText(c.getString(1));
        contraseña.setText(c.getString(2));
        correo.setText(c.getString(3));
    }

    public void limpiarInputs(){
        correo.setText("");
        nombre.setText("");
        contraseña.setText("");
    }
}