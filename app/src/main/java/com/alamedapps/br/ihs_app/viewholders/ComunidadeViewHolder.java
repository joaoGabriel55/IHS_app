package com.alamedapps.br.ihs_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class ComunidadeViewHolder extends RecyclerView.ViewHolder {

    public final TextView nomeComunidade;

    public ComunidadeViewHolder(View itemView) {
        super(itemView);

        nomeComunidade = itemView.findViewById(R.id.nome_comunidade);
    }
}
