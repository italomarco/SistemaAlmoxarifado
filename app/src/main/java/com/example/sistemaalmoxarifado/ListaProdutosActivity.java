package com.example.sistemaalmoxarifado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {

    private ListView listView;
    private ProdutoDAO dao;
    private List<Produto> produtos;
    private List <Produto> produtosfiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        listView = findViewById(R.id.lista_produtos);
        dao = new ProdutoDAO(this);
        produtos = dao.obterTodos();
        produtosfiltrados.addAll(produtos);
        ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1,produtosfiltrados);
        listView.setAdapter(adaptador);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        return  true;
    }
    public  void cadastrar(MenuItem item){
        Intent it = new Intent(this,CadastroProdutoActivity.class);
        startActivity(it);

    }
    @Override
    public  void onResume(){
        super.onResume();
        produtos = dao.obterTodos();
        produtosfiltrados.clear();
        produtosfiltrados.addAll(produtos);
        listView.invalidateViews();
    }
}