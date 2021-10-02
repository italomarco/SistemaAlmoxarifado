package com.example.sistemaalmoxarifado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroProdutoActivity extends AppCompatActivity {

    private EditText nome_produto;
    private EditText codigo_produto;
    private EditText quantidade_produto;
    private ProdutoDAO dao;
    private Produto produto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome_produto = findViewById(R.id.editNome);
        codigo_produto = findViewById(R.id.editCodigo);
        quantidade_produto = findViewById(R.id.editQuantidade);
        dao = new ProdutoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("produto")) {
            produto = (Produto) it.getSerializableExtra("produto");
            nome_produto.setText(produto.getNome_produto());
            //codigo_produto.setText(produto.getCodigo_produto());
            //quantidade_produto.setText(produto.getQuantidade_produto());
        }
    }

    public void Cadastrar(View view) {
        if (produto == null) {
            produto = new Produto();
            produto.setNome_produto(nome_produto.getText().toString());
            produto.setCodigo_produto(Integer.parseInt(codigo_produto.getText().toString()));
            produto.setQuantidade_produto(Integer.parseInt(quantidade_produto.getText().toString()));
            long id = dao.inserir(produto);
            Toast.makeText(this, "Produto inserido com id: " + id, Toast.LENGTH_SHORT).show();

        }else
        {
            produto.setNome_produto(nome_produto.getText().toString());
            produto.setCodigo_produto(Integer.parseInt(codigo_produto.getText().toString()));
            produto.setQuantidade_produto(Integer.parseInt(quantidade_produto.getText().toString()));
            dao.atualizar(produto);
            Toast.makeText(this, "Produto Atualizado com Sucesso" , Toast.LENGTH_SHORT).show();
        }
    }
}