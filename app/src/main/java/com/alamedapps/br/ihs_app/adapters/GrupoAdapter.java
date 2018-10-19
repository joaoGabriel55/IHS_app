package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.viewholders.GrupoViewHolder;
import com.alamedapps.br.ihs_app.viewholders.SecretariaViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter {

    private List<Grupo> grupoList;
    private Context context;

    public GrupoAdapter(List<Grupo> grupoList, Context context) {
        if (grupoList != null) {
            this.grupoList = grupoList;
        } else {
            this.grupoList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Grupo grupo) {
        grupoList.add(grupo);
        notifyItemInserted(grupoList.size() + 1);
    }

    public void clear() {
        grupoList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_grupo, parent, false);
        GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
        return grupoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GrupoViewHolder grupoViewHolder = (GrupoViewHolder) holder;
        final Grupo grupo = grupoList.get(position);

        grupoViewHolder.nome.setText(grupo.getNome());


    }

    @Override
    public int getItemCount() {
        return grupoList != null ? grupoList.size() : 0;
    }
}
