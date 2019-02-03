package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class EventoViewHolder extends RecyclerView.ViewHolder {

    public final LinearLayout linearLayout;
    public final TextView nomeEvento;
    public final ImageView imgMissa;
    public final ImageView imgNotMissa;
    public final TextView horarios;

    public EventoViewHolder(View itemView) {
        super(itemView);
        nomeEvento = itemView.findViewById(R.id.descricao_evento);
        imgMissa = itemView.findViewById(R.id.is_missa);
        imgNotMissa = itemView.findViewById(R.id.is_not_missa);
        horarios = itemView.findViewById(R.id.horarios);
        linearLayout = itemView.findViewById(R.id.linearLayoutLocal);
    }

}
