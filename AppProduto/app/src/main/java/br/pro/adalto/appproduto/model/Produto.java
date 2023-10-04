package br.pro.adalto.appproduto.model;

import androidx.annotation.NonNull;

public class Produto {

    public int id;
    public String nome;
    public double preco;

    public Produto() {

    }

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + preco;
    }
}
