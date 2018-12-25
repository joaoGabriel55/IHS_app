package com.alamedapps.br.ihs_app.models.igrejaemacao;

import java.util.HashMap;
import java.util.List;

public class Grupo {

    private int id;
    private String nome;
    private String descricao;
    private List<String> documentos;
    private HashMap<String, String> coordenadores;
    private CategoriaGrupo categoriaGrupo;
    private String reuniao;

    public Grupo() {
    }

    public Grupo(int id, String nome, String descricao,
                 List<String> documentos, HashMap<String, String> coordenadores,
                 CategoriaGrupo categoriaGrupo, String reuniao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.documentos = documentos;
        this.coordenadores = coordenadores;
        this.categoriaGrupo = categoriaGrupo;
        this.reuniao = reuniao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getDocumentos() {
        return documentos;
    }

    public CategoriaGrupo getCategoriaGrupo() {
        return categoriaGrupo;
    }

    public HashMap<String, String> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(HashMap<String, String> coordenadores) {
        this.coordenadores = coordenadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReuniao() {
        return reuniao;
    }

}
