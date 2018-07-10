package com.alamedapps.br.ihs_app.models;

import java.util.ArrayList;

/**
 * Eventos Relativos a Igreja, seja a missa ou terço
 */
public class Evento {

    private int id;
    private ArrayList<String> horarios;
    private boolean isMissa;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<String> horarios) {
        this.horarios = horarios;
    }

    public boolean isMissa() {
        return isMissa;
    }

    public void setMissa(boolean missa) {
        isMissa = missa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
