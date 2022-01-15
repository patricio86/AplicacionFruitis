package com.example.aplicacionfruitis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class ListarDatos extends AppCompatActivity {

    private ListView lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);

        lv1 = (ListView)findViewById(R.id.lv1);

        ArrayList <String> ranking = new ArrayList<>();

        AdminSQLiteOpenHelper helperBBDD = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = helperBBDD.getReadableDatabase();

        Cursor fila = db.rawQuery("select * from fruitis", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1) + " - " +
                        fila.getString(2) + " - " + fila.getString(3) + " - " + fila.getString(4));
            }while(fila.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);

        db.close();

        Button botonatras = findViewById(R.id.atrasl);
        botonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent botonatras = new Intent(getApplicationContext(),InsertarDatos.class);
                startActivity(botonatras);
            }
        });
    }


}