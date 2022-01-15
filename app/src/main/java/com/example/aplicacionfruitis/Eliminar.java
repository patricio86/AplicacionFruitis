package com.example.aplicacionfruitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {
    EditText eeliminar;
    TextView lfrutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getReadableDatabase();

        lfrutas = (TextView)findViewById(R.id.listadofrutas);
        eeliminar = (EditText)findViewById(R.id.edittexteliminar);



        Cursor cursor = db.query("fruitis",null,null,null,null,null,null,null);
        String nombre, peso, type, rotten;

        cursor.moveToFirst();
        lfrutas.append("\n                              Datos tabla                            \n");
        lfrutas.append("\n nombre             peso              type               rotten \n");
        for(int i=0;i<cursor.getCount();i++){
            nombre = cursor.getString(1);
            peso =  cursor.getString(2);
            type = cursor.getString(3);
            rotten = cursor.getString(4);
            lfrutas.append("\n -------- \n");
            lfrutas.append("\n " + nombre + "                   " + peso + "                    " + type + "                   " + rotten + "\n");
            cursor.moveToNext();
        }

        db.close();

        Button botonatras = findViewById(R.id.atras);
        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonatras = new Intent(getApplicationContext(),InsertarDatos.class);
                startActivity(botonatras);
            }
        });

        Button eliminarpornombre = findViewById(R.id.eliminarf);
        eliminarpornombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminarpornombre();

            }
        });





    }

    public void eliminarpornombre(){
        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getReadableDatabase();

        String nombre = eeliminar.getText().toString();
        db.execSQL("delete from fruitis where nombre = " + nombre,null);
        db.close();
        eeliminar.setText("");
    }

}