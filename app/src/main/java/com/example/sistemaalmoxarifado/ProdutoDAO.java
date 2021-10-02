package com.example.sistemaalmoxarifado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    public List<Produto> obterTodos(){
        List <Produto> produtos = new ArrayList<>();
        Cursor cursor =  banco.query("produto",new String[]{"id", "nome_produto", "codigo_produto","quantidade_produto"},
        null,null,null,null,null);

        while (cursor.moveToNext()){

            Produto p = new Produto();
            p.setId(cursor.getInt(0));
            p.setNome_produto(cursor.getString(1));
            p.setQuantidade_produto(cursor.getInt(2));
            p.setQuantidade_produto(cursor.getInt(3));
            produtos.add(p);
        }
        return produtos;
    }
    public void  excluir (Produto p){
             banco.delete("produto", "id = ?",new  String[]{p.getId().toString()});

    }

    public void atualizar(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("nome_produto",produto.getNome_produto());
        values.put("codigo_produto",produto.getCodigo_produto());
        values.put("quantidade_produto",produto.getQuantidade_produto());
        banco.update("produto", values ,
                "id = ?", new  String[]{produto.getId().toString()});

    }
}
