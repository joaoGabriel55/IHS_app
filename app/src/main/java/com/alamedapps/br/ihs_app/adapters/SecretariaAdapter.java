package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.viewholders.SecretariaViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SecretariaAdapter extends RecyclerView.Adapter {

    private List<Secretaria> secretariaList;
    private Context context;

    public SecretariaAdapter(List<Secretaria> secretariaList, Context context) {
        if (secretariaList != null) {
            this.secretariaList = secretariaList;
        } else {
            this.secretariaList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Secretaria secretaria) {
        if (secretariaList.size() < 1) {
            secretariaList.add(secretaria);
            notifyItemInserted(secretariaList.size() + 1);
        }
    }

    public void clear() {
        secretariaList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_secretaria, parent, false);
        SecretariaViewHolder localViewHolder = new SecretariaViewHolder(v);
        return localViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final SecretariaViewHolder secretariaViewHolder = (SecretariaViewHolder) holder;
        final Secretaria secretaria = secretariaList.get(position);

        secretariaViewHolder.nomeSecretaria.setText(secretaria.getNomeSecretaria());
        secretariaViewHolder.contato.setText(secretaria.getContato());
        secretariaViewHolder.expediente.setText(secretaria.getExpediente());
        secretariaViewHolder.linearLayoutCaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + secretaria.getContato()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return secretariaList != null ? secretariaList.size() : 0;
    }
}
