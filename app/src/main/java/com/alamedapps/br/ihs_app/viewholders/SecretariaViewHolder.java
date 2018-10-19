package com.alamedapps.br.ihs_app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class SecretariaViewHolder extends RecyclerView.ViewHolder {

    public final LinearLayout linearLayoutSecretaria;
    public final LinearLayout linearLayoutCaller;
    public final TextView nomeSecretaria;
    public final TextView contato;
    public final TextView expediente;

    public SecretariaViewHolder(View itemView) {
        super(itemView);

        nomeSecretaria = itemView.findViewById(R.id.nome_secretaria);
        contato = itemView.findViewById(R.id.contato);
        expediente = itemView.findViewById(R.id.expediente);
        linearLayoutSecretaria = itemView.findViewById(R.id.linearLayoutSecretaria);
        linearLayoutCaller = itemView.findViewById(R.id.caller);


    }


}
