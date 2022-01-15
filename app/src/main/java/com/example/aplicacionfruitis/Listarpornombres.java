package com.example.aplicacionfruitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Listarpornombres extends AppCompatActivity {

    private ArrayList<String>names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarpornombres);

        ListView listadonombres = (ListView)findViewById(R.id.listviewnombres);

        Bundle bundle = getIntent().getExtras();
        String datosnombre = bundle.getString("nombre");
        String datospeso = bundle.getString("peso");
        String datoslista = bundle.getString("lista");
        String datosestado = bundle.getString("estado");

        names = new ArrayList<String>();
        names.add(datosnombre);
        names.add(datospeso);
        names.add(datoslista);
        names.add(datosestado);

        ArrayAdapter<String> datosselectnombre = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);


        listadonombres.setAdapter(datosselectnombre);


        Button botonatras = findViewById(R.id.atras2);
        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonatras = new Intent(getApplicationContext(),InsertarDatos.class);
                startActivity(botonatras);
            }
        });


    }
}