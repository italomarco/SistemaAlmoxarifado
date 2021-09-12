package com.example.sistemaalmoxarifado;

import java.io.Serializable;

public class Produto implements Serializable {

    private Integer id;
    private String nome_produto;
    private int codigo_produto;
    private int quantidade_produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }
    @Override
    public String toString(){
        return nome_produto;
    }
}
