package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class ComunidadeViewHolder extends RecyclerView.ViewHolder {

    public final TextView nomeComunidade;

    public final LinearLayout enderecoLayout;
    public final TextView endereco;

    public final LinearLayout celebracaoLayout;
    public final TextView celebracao;

    public final LinearLayout batismoLayout;

    public final LinearLayout horaBatismoLayout;
    public final TextView horaBatismo;

    public final LinearLayout reuniaoBatismoLayout;
    public final TextView reuniaoBatismo;

    public final TextView festaPadroeiroBtn;
    public final TextView verHorariosBtn;

    public ComunidadeViewHolder(View itemView) {
        super(itemView);

        nomeComunidade = itemView.findViewById(R.id.nome_comunidade);

        endereco = itemView.findViewById(R.id.endereco);
        enderecoLayout = itemView.findViewById(R.id.layout_endereco);

        celebracaoLayout = itemView.findViewById(R.id.layout_celebracao);
        celebracao = itemView.findViewById(R.id.celebracao_decricao);

        batismoLayout = itemView.findViewById(R.id.layout_batismo);

        horaBatismoLayout = itemView.findViewById(R.id.hora_batismo_layout);
        horaBatismo = itemView.findViewById(R.id.horario_batismo);

        reuniaoBatismoLayout = itemView.findViewById(R.id.reuniao_batismo_layout);
        reuniaoBatismo = itemView.findViewById(R.id.reuniao_batismo);

        festaPadroeiroBtn = itemView.findViewById(R.id.ver_festa_tv_button);
        verHorariosBtn = itemView.findViewById(R.id.ver_hora_tv_button);
    }
}
