package com.alamedapps.br.ihs_app.models;

/**
 * Corpo religioso da igreja
 */
public class Clero {

    private int id;
    private String nome;
    private String cargoTitulo;
    private String dataNascimento;
    private String dataOrdenacao;
    private String image;

    public Clero() {
    }

    public Clero(int id, String nome, String cargoTitulo, String dataNascimento, String dataOrdenacao, String image) {
        this.id = id;
        this.nome = nome;
        this.cargoTitulo = cargoTitulo;
        this.dataNascimento = dataNascimento;
        this.dataOrdenacao = dataOrdenacao;
        this.image = image;
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

    public String getDataOrdenacao() {
        return dataOrdenacao;
    }

    public void setDataOrdenacao(String dataOrdenacao) {
        this.dataOrdenacao = dataOrdenacao;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
