package com.example.aplicacionfruitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertarDatos extends AppCompatActivity {

    private EditText nom, pe, snom;
    private Spinner list;
    private CheckBox estad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_datos);

        nom = (EditText)findViewById(R.id.editnombre);
        pe = (EditText)findViewById(R.id.editweight);
        list = (Spinner)findViewById(R.id.listaestado);
        estad = (CheckBox)findViewById(R.id.checkBox);
        snom = (EditText)findViewById(R.id.selectnombre);

        snom.setVisibility(View.INVISIBLE);

        String[]opciones = {"dulce","acida","amarga"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,opciones);
        list.setAdapter(adapter);

        SpinnerActivity listadosabores = new SpinnerActivity();
        list.setOnItemSelectedListener(listadosabores);


        Button botongetall = findViewById(R.id.getall);
        botongetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botongetall = new Intent(getApplicationContext(),ListarDatos.class);
                startActivity(botongetall);
            }
        });

        Button botongetlast = findViewById(R.id.getlast);
        botongetlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botongetlast = new Intent(getApplicationContext(),ListarDatos2.class);
                startActivity(botongetlast);
            }
        });

        Button botongetbynombre = findViewById(R.id.getnombre);
        botongetbynombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snom.setVisibility(View.VISIBLE);
                consultarnombres();
            }
        });

    }
    public void consultarnombres() {
        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getWritableDatabase();

        String selectnombre = snom.getText().toString();
        Cursor cursornombre = db.rawQuery("select * from fruitis where nombre = '" + selectnombre + "'", null);
        if(cursornombre.moveToFirst()){
            Intent datos = new Intent(getApplicationContext(),Listarpornombres.class);
            datos.putExtra("nombre",cursornombre.getString(1));
            datos.putExtra("peso",cursornombre.getString(2));
            datos.putExtra("lista",cursornombre.getString(3));
            datos.putExtra("estado",cursornombre.getString(4));
            startActivity(datos);
        }
    }

    public void insertar(View v) {
        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getWritableDatabase();

        if (nom.getText().toString().trim().equalsIgnoreCase("")
                || pe.getText().toString().trim().equalsIgnoreCase("")) {

            Toast mensaje = Toast.makeText(this, "Insertar no realizado", Toast.LENGTH_LONG);
            mensaje.show();

        } else {
            String datosnombre = nom.getText().toString();
            int datospeso = Integer.parseInt(pe.getText().toString());
            String sabor = list.getSelectedItem().toString();

            ContentValues values = new ContentValues();
            values.put("nombre", datosnombre);
            values.put("peso", datospeso);
            values.put("type", sabor);

            if(estad.isChecked()){
                values.put("rotten", "podrido");
            }else{
                values.put("rotten", "sano");
            }

            db.insert("fruitis",null,values);
            db.close();

            nom.setText("");
            pe.setText("");

            Toast mensaje = Toast.makeText(this, "Registro realizado", Toast.LENGTH_LONG);
            mensaje.show();


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){
            Intent siguientelistar = new Intent(getApplicationContext(),ListarDatos.class);
            startActivity(siguientelistar);
        }
        if(item.getItemId() == R.id.item2){
            Intent siguienteeliminar = new Intent(getApplicationContext(),Eliminar.class);
            startActivity(siguienteeliminar);
        }

        return super.onOptionsItemSelected(item);
    }
}