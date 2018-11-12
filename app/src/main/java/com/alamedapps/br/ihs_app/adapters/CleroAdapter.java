package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.alamedapps.br.ihs_app.viewholders.CleroViewHolder;
import com.alamedapps.br.ihs_app.viewholders.SecretariaViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CleroAdapter extends RecyclerView.Adapter {

    private List<Clero> cleroList;
    private Context context;

    public CleroAdapter(List<Clero> cleroList, Context context) {
        if (cleroList != null) {
            this.cleroList = cleroList;
        } else {
            this.cleroList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Clero clero) {
        cleroList.add(clero);
        notifyItemInserted(cleroList.size() + 1);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_clero, parent, false);
        CleroViewHolder cleroViewHolder = new CleroViewHolder(v);
        return cleroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CleroViewHolder cleroViewHolder = (CleroViewHolder) holder;
        final Clero clero = cleroList.get(position);

        cleroViewHolder.nome.setText(clero.getNome());
        IHSUtil.handleImage(cleroViewHolder.image, clero);

    }

    public List<Clero> getCleroList() {
        return cleroList;
    }

    @Override
    public int getItemCount() {
        return cleroList != null ? cleroList.size() : 0;
    }
}
