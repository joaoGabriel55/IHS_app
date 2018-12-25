package com.alamedapps.br.ihs_app.models.comunidade;

import java.util.List;

public class Capela {

    private int id;
    private String nome;
    private String dataFestaPadroeiro;
    private List<String> horarios;
    private String batismoReunicoes;
    private String batizadosHorario;
    private String celebracao;
    private String endereco;

    public Capela() {
    }

    public Capela(int id, String nome, String dataFestaPadroeiro, List<String> horarios, String batismoReunicoes, String batizadosHorario, String celebracao, String endereco) {
        this.id = id;
        this.nome = nome;
        this.dataFestaPadroeiro = dataFestaPadroeiro;
        this.horarios = horarios;
        this.batismoReunicoes = batismoReunicoes;
        this.batizadosHorario = batizadosHorario;
        this.celebracao = celebracao;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataFestaPadroeiro() {
        return dataFestaPadroeiro;
    }

    public void setDataFestaPadroeiro(String dataFestaPadroeiro) {
        this.dataFestaPadroeiro = dataFestaPadroeiro;
    }

    public String getBatismoReunicoes() {
        return batismoReunicoes;
    }

    public void setBatismoReunicoes(String batismoReunicoes) {
        this.batismoReunicoes = batismoReunicoes;
    }

    public String getBatizadosHorario() {
        return batizadosHorario;
    }

    public void setBatizadosHorario(String batizadosHorario) {
        this.batizadosHorario = batizadosHorario;
    }

    public String getCelebracao() {
        return celebracao;
    }

    public void setCelebracao(String celebracao) {
        this.celebracao = celebracao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
