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
import com.alamedapps.br.ihs_app.models.igrejaemacao.CategoriaGrupo;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.FormatterUtils;
import com.alamedapps.br.ihs_app.viewholders.GrupoViewHolder;
import com.alamedapps.br.ihs_app.viewholders.SecretariaViewHolder;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter {

    private static final int GROUP_TYPE = 1;
    private static final int HEADER_TYPE = 2;

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
        View v;

        switch (viewType) {

            case GROUP_TYPE:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);
            case HEADER_TYPE:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo_header, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);
            default:
                v = LayoutInflater.from(context).inflate(R.layout.layout_grupo, parent, false);
                //GrupoViewHolder grupoViewHolder = new GrupoViewHolder(v);
                return new GrupoViewHolder(v);


        }


        //return grupoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GrupoViewHolder grupoViewHolder = (GrupoViewHolder) holder;
        final Grupo grupo = grupoList.get(position);

//        grupoViewHolder.nome.setText(grupo.getNome());
//        grupoViewHolder.categoria.setText(grupo.getCategoriaGrupo().toString());

        int itemViewType = getItemViewType(position);

        if (itemViewType == GROUP_TYPE) {
            grupoViewHolder.nome.setText(grupo.getNome());
        } else {
            grupoViewHolder.nome.setText(grupo.getNome());
            String categFormatted = FormatterUtils.firstLetterUppercase(grupo.getCategoriaGrupo().toString());
            grupoViewHolder.categoria.setText(categFormatted);

        }


    }

    @Override
    public int getItemCount() {
        return grupoList != null ? grupoList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        int type = 0;

        if (position < grupoList.size() - 1) {
            if (position == 0 || grupoList.get(position).getCategoriaGrupo() != grupoList.get(position - 1).getCategoriaGrupo()) {
                type = HEADER_TYPE;
            } else if (grupoList.get(position).getCategoriaGrupo() == grupoList.get(position - 1).getCategoriaGrupo()) {
                type = GROUP_TYPE;
            } else {
                type = HEADER_TYPE;

            }
        } else {
            if (grupoList.get(position).getCategoriaGrupo() == grupoList.get(position - 1).getCategoriaGrupo())
                type = GROUP_TYPE;
            else
                type = HEADER_TYPE;
        }


        return type;
    }

}
