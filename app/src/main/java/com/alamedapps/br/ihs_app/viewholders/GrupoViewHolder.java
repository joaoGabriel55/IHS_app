package com.alamedapps.br.ihs_app.viewholders;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.igrejaemacao.CategoriaGrupo;

public class GrupoViewHolder extends RecyclerView.ViewHolder {

    public TextView nome;
    public TextView categoria;
    //public final TextView descricao;
    public TextView reuniao;
    public ConstraintLayout layoutNotFound;
    public RecyclerView recyclerView;


    public GrupoViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_grupo);
        categoria = itemView.findViewById(R.id.grupo_categoria_header);
        //descricao = itemView.findViewById(R.id.grupo_categoria_header);
        reuniao = itemView.findViewById(R.id.reuniao_grupo);

    }

}
