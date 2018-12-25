package com.alamedapps.br.ihs_app.models;

public class TaxasEmolumentos {

    private int id;
    private String nome;
    private Double valor;


    public TaxasEmolumentos() {
    }

    public TaxasEmolumentos(int id, String nome, Double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
