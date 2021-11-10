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
import com.example.idnp_lab07.db.DbUbicaciones;

import java.util.ArrayList;
import java.util.List;

public class UbicacionesActivity extends AppCompatActivity {
    Spinner ids;
    EditText x;
    EditText y;
    EditText z;
    List<String> listaIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);

        ids = findViewById(R.id.spId3);
        x = findViewById(R.id.inputX);
        y = findViewById(R.id.inputY);
        z = findViewById(R.id.inputZ);
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
        DbUbicaciones dbubicacion = new DbUbicaciones(UbicacionesActivity.this);
        long id = dbubicacion.insertarUbicacion(x.getText().toString(),y.getText().toString(),z.getText().toString());
        if(id>0){
            Toast.makeText(UbicacionesActivity.this, "Ubicacion" + id + " creada", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(UbicacionesActivity.this, "No se pudo crear la Ubicacion", Toast.LENGTH_LONG).show();
        }
    }
    public void actualizar(View view){
        DbUbicaciones dbubicacion = new DbUbicaciones(UbicacionesActivity.this);
        boolean actualizado = dbubicacion.actualizarUbicacion(ids.getSelectedItem().toString(),x.getText().toString(),y.getText().toString(),z.getText().toString());
        if(actualizado){
            Toast.makeText(UbicacionesActivity.this, "Ubicacion actualizado", Toast.LENGTH_LONG).show();
            limpiarInputs();
            ids.setSelection(0);
        }
        else{
            Toast.makeText(UbicacionesActivity.this, "No se pudo actualizar Ubicacion", Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar(View view){
        DbUbicaciones dbubicacion = new DbUbicaciones(UbicacionesActivity.this);
        boolean eliminado = dbubicacion.eliminarUbicacion(ids.getSelectedItem().toString());
        if(eliminado){
            Toast.makeText(UbicacionesActivity.this, "Ubicacion eliminada", Toast.LENGTH_LONG).show();
            limpiarInputs();
            llenarSpinner();
        }
        else{
            Toast.makeText(UbicacionesActivity.this, "No se pudo eliminar Ubicacion", Toast.LENGTH_LONG).show();
        }
    }
    public void llenarSpinner(){
        listaIds.clear();
        DbUbicaciones dbubicacion = new DbUbicaciones(UbicacionesActivity.this);
        Cursor c = dbubicacion.obtenerIds();
        listaIds.add("Ninguno");
        c.moveToPosition(-1);
        while(c.moveToNext()){
            listaIds.add(c.getString(0));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaIds);
        ids.setAdapter(adapter);
    }
    public void limpiarInputs(){
        x.setText("");
        y.setText("");
        z.setText("");

    }
    public void llenarDatos(String seleccionado){
        DbUbicaciones dbubicacion = new DbUbicaciones(UbicacionesActivity.this);
        Cursor c = dbubicacion.obtenerUbicaciones(seleccionado);
        c.moveToFirst();
        x.setText(c.getString(1));
        y.setText(c.getString(2));
        z.setText(c.getString(3));

    }
}
