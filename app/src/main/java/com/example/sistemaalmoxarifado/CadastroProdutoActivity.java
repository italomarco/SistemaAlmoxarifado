package com.example.sistemaalmoxarifado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroProdutoActivity extends AppCompatActivity {

    private EditText nome_produto;
    private EditText codigo_produto;
    private  EditText quantidade_produto;
    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome_produto = findViewById(R.id.editNome);
        codigo_produto = findViewById(R.id.editCodigo);
        quantidade_produto = findViewById(R.id.editQuantidade);
        dao = new ProdutoDAO(this);
    }
public void Cadastrar (View view){
        Produto p = new Produto();
        p.setNome_produto(nome_produto.getText().toString());
        p.setCodigo_produto(Integer.parseInt(codigo_produto.getText().toString()));
        p.setQuantidade_produto(Integer.parseInt(quantidade_produto.getText().toString()));
        long id = dao.inserir(p);
        Toast.makeText(this, "Produto inserido com id: "+id, Toast.LENGTH_SHORT).show();

    }
}