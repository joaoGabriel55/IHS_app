package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.adapters.viewholders.TaxasViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TaxasAdapter extends RecyclerView.Adapter {
    private List<TaxasEmolumentos> taxasEmolumentosList;
    private Context context;

    public TaxasAdapter(List<TaxasEmolumentos> oracaoList, Context context) {
        if (oracaoList != null) {
            this.taxasEmolumentosList = oracaoList;
        } else {
            this.taxasEmolumentosList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(TaxasEmolumentos taxasEmolumentos) {
        taxasEmolumentosList.add(taxasEmolumentos);
        notifyItemInserted(taxasEmolumentosList.size() + 1);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_taxas, parent, false);
        TaxasViewHolder taxasViewHolder = new TaxasViewHolder(v);
        return taxasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final TaxasViewHolder taxasViewHolder = (TaxasViewHolder) holder;
        final TaxasEmolumentos taxasEmolumentos = taxasEmolumentosList.get(position);

        taxasViewHolder.nome.setText(taxasEmolumentos.getNome());
        if (taxasEmolumentos.getValor() <= 0.0) {
            taxasViewHolder.valorField.setVisibility(View.GONE);
        } else {
            taxasViewHolder.valorField.setVisibility(View.VISIBLE);
            taxasViewHolder.valor.setText(taxasEmolumentos.getValor().toString() + "0");
        }

    }

    @Override
    public int getItemCount() {
        return taxasEmolumentosList != null ? taxasEmolumentosList.size() : 0;
    }
}


