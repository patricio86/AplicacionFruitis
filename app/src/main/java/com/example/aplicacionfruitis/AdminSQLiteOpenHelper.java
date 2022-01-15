package com.example.aplicacionfruitis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BDFruitis.db";
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS fruitis(_ID  integer PRIMARY KEY, nombre text, peso integer, type text, rotten boolean);";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS fruitis";

    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la versión anterior de la tabla
        db.execSQL(SQL_DELETE_ENTRIES);
        //Se crea la nueva versión de la tabla
        db.execSQL(SQL_CREATE_ENTRIES);
    }


}
