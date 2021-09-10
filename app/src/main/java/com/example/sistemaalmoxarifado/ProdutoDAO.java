package com.example.sistemaalmoxarifado;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProdutoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO (Context context){
     conexao = new Conexao (context) ;
     banco = conexao.getWritableDatabase();

    }

    public  long inserir (Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome_produto",produto.getNome_produto());
        values.put("codigo_produto",produto.getCodigo_produto());
        values.put("quantidade_produto",produto.getQuantidade_produto());
        return banco.insert("produto",null,values);

    }
}
