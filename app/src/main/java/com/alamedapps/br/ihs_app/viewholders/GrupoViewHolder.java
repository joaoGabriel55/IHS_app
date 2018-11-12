package com.alamedapps.br.ihs_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class GrupoViewHolder extends RecyclerView.ViewHolder{

    public final TextView nome;
    public final TextView categoria;

    public GrupoViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_grupo);
        categoria = itemView.findViewById(R.id.grupo_categoria_header);

    }

}
