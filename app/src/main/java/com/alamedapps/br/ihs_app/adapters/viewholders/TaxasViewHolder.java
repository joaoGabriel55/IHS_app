package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class TaxasViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    public final TextView valor;
    public final LinearLayout valorField;

    public TaxasViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_taxa);
        valor = itemView.findViewById(R.id.valor_taxa);
        valorField = itemView.findViewById(R.id.linearLayout_Valor);

    }
}
