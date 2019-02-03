package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class CleroViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    public final ImageView image;
/*    public final TextView cargoTitulo;
    public final TextView dataNascimento;
    public final TextView dataOrdenacao;*/


    public CleroViewHolder(View itemView) {
        super(itemView);

        nome = itemView.findViewById(R.id.nome_clero);
        image = itemView.findViewById(R.id.image_clero);

    }


}
