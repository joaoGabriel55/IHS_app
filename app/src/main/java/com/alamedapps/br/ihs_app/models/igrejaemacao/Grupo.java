package com.alamedapps.br.ihs_app.models.igrejaemacao;

import java.util.HashMap;
import java.util.List;

public class Grupo {

    private int id;
    private String nome;
    private String descricao;
    private List<String> documentos;
    private List<String> coordenadores;
    private CategoriaGrupo categoriaGrupo;
    private String reuniao;


    public Grupo(int id, String nome, String descricao,
                 List<String> documentos, List<String> coordenadores,
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<String> documentos) {
        this.documentos = documentos;
    }

    public void setCoordenadores(List<String> coordenadores) {
        this.coordenadores = coordenadores;
    }

    public CategoriaGrupo getCategoriaGrupo() {
        return categoriaGrupo;
    }

    public void setCategoriaGrupo(CategoriaGrupo categoriaGrupo) {
        this.categoriaGrupo = categoriaGrupo;
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

    public void setReuniao(String reuniao) {
        this.reuniao = reuniao;
    }
}
