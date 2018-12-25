package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.comunidade.Capela;
import com.alamedapps.br.ihs_app.viewholders.ComunidadeViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ComunidadeAdapter extends RecyclerView.Adapter {
    private List<Capela> capelaList;
    private Context context;

    public ComunidadeAdapter(List<Capela> capelaList, Context context) {
        if (capelaList != null) {
            this.capelaList = capelaList;
        } else {
            this.capelaList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Capela capela) {
        capelaList.add(capela);
        notifyItemInserted(capelaList.size() + 1);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_comunidade, parent, false);
        ComunidadeViewHolder comunidadeViewHolder = new ComunidadeViewHolder(v);
        return comunidadeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ComunidadeViewHolder comunidadeViewHolder = (ComunidadeViewHolder) holder;
        final Capela capela = capelaList.get(position);

        comunidadeViewHolder.nomeComunidade.setText(capela.getNome());
    }

    @Override
    public int getItemCount() {
        return capelaList != null ? capelaList.size() : 0;
    }
}
