package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.viewholders.ReligiosidadeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReligiosidadeAdapter extends RecyclerView.Adapter {
    private List<Oracao> oracaoList;
    private Context context;

    public ReligiosidadeAdapter(List<Oracao> oracaoList, Context context) {
        if (oracaoList != null) {
            this.oracaoList = oracaoList;
        } else {
            this.oracaoList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Oracao oracao) {
        oracaoList.add(oracao);
        notifyItemInserted(oracaoList.size() + 1);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_religiosidade, parent, false);
        ReligiosidadeViewHolder religiosidadeViewHolder = new ReligiosidadeViewHolder(v);
        return religiosidadeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ReligiosidadeViewHolder religiosidadeViewHolder = (ReligiosidadeViewHolder) holder;
        final Oracao oracao = oracaoList.get(position);

        religiosidadeViewHolder.nomeOracao.setText(oracao.getNome());
    }

    @Override
    public int getItemCount() {
        return oracaoList != null ? oracaoList.size() : 0;
    }

}
