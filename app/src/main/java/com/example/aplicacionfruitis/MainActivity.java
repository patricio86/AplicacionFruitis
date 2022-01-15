package com.example.aplicacionfruitis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botoninsertar = findViewById(R.id.insertar);
        botoninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertar = new Intent(getApplicationContext(),InsertarDatos.class);
                startActivity(insertar);
            }
        });

        Button botonlistar = findViewById(R.id.listado1);
        botonlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listar = new Intent(getApplicationContext(),ListarDatos.class);
                startActivity(listar);
            }
        });

        Button botoneliminar = findViewById(R.id.eliminar);
        botoneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eliminar = new Intent(getApplicationContext(),Eliminar.class);
                startActivity(eliminar);
            }
        });

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