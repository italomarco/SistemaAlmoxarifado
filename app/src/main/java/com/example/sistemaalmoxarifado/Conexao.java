package com.example.sistemaalmoxarifado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final  String name ="banco.db";
    private static final int version = 1;

    public Conexao( Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table produto (id integer primary key autoincrement, " +
                "nome_produto varchar (50), codigo_produto varchar (50), quantidade_produto varchar (50))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
