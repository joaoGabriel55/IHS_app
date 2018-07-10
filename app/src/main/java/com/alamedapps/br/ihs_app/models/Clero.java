package com.alamedapps.br.ihs_app.models;

/**
 * Corpo religioso da igreja
 */
public class Clero {

    private int id;
    private String nome;
    private String cargoTitulo;
    private String dataNascimento;

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

    public String getCargoTitulo() {
        return cargoTitulo;
    }

    public void setCargoTitulo(String cargoTitulo) {
        this.cargoTitulo = cargoTitulo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
