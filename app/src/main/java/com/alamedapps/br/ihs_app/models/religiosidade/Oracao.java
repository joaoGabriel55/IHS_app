package com.alamedapps.br.ihs_app.models.religiosidade;

public class Oracao {

    private int id;
    private String nome;
    private String letra;

    public Oracao() {
    }

    public Oracao(int id, String nome, String letra) {
        this.id = id;
        this.nome = nome;
        this.letra = letra;
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

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
