package com.alamedapps.br.ihs_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class TaxasViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;

    public TaxasViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_taxa);

    }
}
