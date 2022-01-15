package com.example.aplicacionfruitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListarDatos2 extends AppCompatActivity {

    private TextView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos2);

        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getReadableDatabase();

        listado = (TextView)findViewById(R.id.listado1);

        Cursor cursor = db.query("fruitis",null,null,null,null,null,"nombre desc","1");
        String nombre, peso, type, rotten;

        cursor.moveToFirst();
        listado.append("\n                              Datos tabla                            \n");
        listado.append("\n nombre             peso              type               rotten \n");
        for(int i=0;i<cursor.getCount();i++){
            nombre = cursor.getString(1);
            peso =  cursor.getString(2);
            type = cursor.getString(3);
            rotten = cursor.getString(4);
            listado.append("\n -------- \n");
            listado.append("\n " + nombre + "                   " + peso + "                    " + type + "                   " + rotten + "\n");
            cursor.moveToNext();
        }

        db.close();

        Button botonatras = findViewById(R.id.atrasl1);
        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonatras = new Intent(getApplicationContext(),InsertarDatos.class);
                startActivity(botonatras);
            }
        });
    }
}