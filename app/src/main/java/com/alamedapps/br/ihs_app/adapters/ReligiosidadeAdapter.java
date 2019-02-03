package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.fragment.comunidade.FragmentFullScreenDialog;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.adapters.viewholders.ReligiosidadeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ReligiosidadeAdapter extends RecyclerView.Adapter {

    public static final String NOME_ORACAO_KEY = "nome";
    public static final String LETRA_ORACAO_KEY = "letraOracao";

    private List<Oracao> oracaoList;
    private Context context;
    private FragmentActivity fragmentActivity;

    public ReligiosidadeAdapter(List<Oracao> oracaoList, Context context, FragmentActivity fragmentActivity) {
        if (oracaoList != null) {
            this.oracaoList = oracaoList;
        } else {
            this.oracaoList = new ArrayList<>();
        }
        this.fragmentActivity = fragmentActivity;
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final ReligiosidadeViewHolder religiosidadeViewHolder = (ReligiosidadeViewHolder) holder;
        final Oracao oracao = oracaoList.get(position);

        religiosidadeViewHolder.nomeOracao.setText(oracao.getNome());
        religiosidadeViewHolder.nomeOracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = FragmentFullScreenDialog.newInstance();
                Bundle b = new Bundle();
                b.putString(NOME_ORACAO_KEY, oracao.getNome());
                b.putString(LETRA_ORACAO_KEY, oracao.getLetra());
                dialog.setArguments(b);
                dialog.show(fragmentActivity.getSupportFragmentManager(), "tag");
            }
        });

    }

    @Override
    public int getItemCount() {
        return oracaoList != null ? oracaoList.size() : 0;
    }

}
