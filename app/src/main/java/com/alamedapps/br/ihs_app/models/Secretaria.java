package com.alamedapps.br.ihs_app.models;

public class Secretaria {

    private int id;
    private String expediente;
    private String nomeSecretaria;
    private String contato;

    public Secretaria(String expediente, String nomeSecretaria, String contato) {
        this.expediente = expediente;
        this.nomeSecretaria = nomeSecretaria;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getNomeSecretaria() {
        return nomeSecretaria;
    }

    public void setNomeSecretaria(String nomeSecretaria) {
        this.nomeSecretaria = nomeSecretaria;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
