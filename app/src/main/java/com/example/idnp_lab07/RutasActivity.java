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

import com.example.idnp_lab07.db.DbRutas;

import java.util.ArrayList;
import java.util.List;

public class RutasActivity extends AppCompatActivity {
    Spinner ids;
    EditText lat;
    EditText log;
    List<String> listaIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        ids = findViewById(R.id.spId2);
        lat = findViewById(R.id.inputLatitud);
        log = findViewById(R.id.inputLongitud);
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
        DbRutas dbrutas = new DbRutas(RutasActivity.this);
        long id = dbrutas.insertarRutas (lat.getText().toString(),log.getText().toString());
        if(id>0){
            Toast.makeText(RutasActivity.this, "Ruta" + id + " creada", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(RutasActivity.this, "No se pudo crear Ruta", Toast.LENGTH_LONG).show();
        }
    }
    public void actualizar(View view){
        DbRutas dbrutas = new DbRutas(RutasActivity.this);
        boolean actualizado = dbrutas.actualizarRutas(ids.getSelectedItem().toString(),lat.getText().toString(),log.getText().toString());
        if(actualizado){
            Toast.makeText(RutasActivity.this, "Usuario actualizado", Toast.LENGTH_LONG).show();
            limpiarInputs();
            ids.setSelection(0);
        }
        else{
            Toast.makeText(RutasActivity.this, "No se pudo actualizar Usuario", Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar(View view){
        DbRutas dbrutas = new DbRutas(RutasActivity.this);
        boolean eliminado = dbrutas.eliminarRutas(ids.getSelectedItem().toString());
        if(eliminado){
            Toast.makeText(RutasActivity.this, "Ruta eliminada", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(RutasActivity.this, "No se pudo eliminar Ruta", Toast.LENGTH_LONG).show();
        }
    }
    public void cancelar(View view){
        limpiarInputs();
        ids.setSelection(0);
    }
    public void llenarSpinner(){
        listaIds.clear();
        DbRutas dbrutas = new DbRutas(RutasActivity.this);
        Cursor c = dbrutas.obtenerIds();
        listaIds.add("Ninguno");
        c.moveToPosition(-1);
        while(c.moveToNext()){
            listaIds.add(c.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaIds);
        ids.setAdapter(adapter);
    }
    public void limpiarInputs(){
        lat.setText("");
        log.setText("");

    }
    public void llenarDatos(String seleccionado){
        DbRutas dbRutas = new DbRutas(RutasActivity.this);
        Cursor c = dbRutas.obtenerRutas(seleccionado);
        c.moveToFirst();
        lat.setText(c.getString(1));
        log.setText(c.getString(2));

    }
}
