package com.alamedapps.br.ihs_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class ReligiosidadeViewHolder extends RecyclerView.ViewHolder {

    public final TextView nomeOracao;

    public ReligiosidadeViewHolder(View itemView) {
        super(itemView);

        nomeOracao = itemView.findViewById(R.id.nome_oracao);
    }
}
