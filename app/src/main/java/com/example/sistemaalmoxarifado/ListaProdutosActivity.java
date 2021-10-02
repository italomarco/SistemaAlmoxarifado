package com.example.sistemaalmoxarifado;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);

        SearchView sv = (SearchView)  menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarProduto(s);
                return false;
            }
        });

        return  true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
         super.onCreateContextMenu(menu,v , menuInfo);
         MenuInflater i = getMenuInflater();
         i.inflate(R.menu.menu_contexto, menu);

    }

    public  void procurarProduto(String nome){

        produtosfiltrados.clear();
        for (Produto p : produtos){
            if(p.getNome_produto().toLowerCase().contains(nome.toLowerCase())){
             produtosfiltrados.add(p);
            }
        }

        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

       final  Produto produtoExcluir = produtosfiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o  produto?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        produtosfiltrados.remove(produtoExcluir);
                        produtos.remove(produtoExcluir);
                        dao.excluir(produtoExcluir);
                        listView.invalidateViews();

                    }
                }).create();
            dialog.show();



    }
    public  void cadastrar(MenuItem item){
        Intent it = new Intent(this,CadastroProdutoActivity.class);
        startActivity(it);
    }
    public  void atualizar (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final  Produto produtoAtualizar = produtosfiltrados.get(menuInfo.position);
        Intent it =  new Intent(this, CadastroProdutoActivity.class);
        it.putExtra("produto", produtoAtualizar);
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