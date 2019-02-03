package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.comunidade.Capela;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.alamedapps.br.ihs_app.adapters.viewholders.ComunidadeViewHolder;

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
        handleDataComunidade(comunidadeViewHolder, capela);
        setActionButtonsCard(comunidadeViewHolder, capela);
    }

    private void handleDataComunidade(ComunidadeViewHolder comunidadeViewHolder, Capela capela) {
        if (capela.getEndereco() != null) {
            comunidadeViewHolder.enderecoLayout.setVisibility(View.VISIBLE);
            comunidadeViewHolder.endereco.setText(capela.getEndereco());
        } else {
            comunidadeViewHolder.enderecoLayout.setVisibility(View.GONE);
        }

        if (capela.getCelebracao() != null) {
            comunidadeViewHolder.celebracaoLayout.setVisibility(View.VISIBLE);
            comunidadeViewHolder.celebracao.setText(capela.getCelebracao());
        } else {
            comunidadeViewHolder.celebracaoLayout.setVisibility(View.GONE);
        }

        if (capela.getBatismoReunicoes() != null || capela.getBatizadosHorario() != null) {
            comunidadeViewHolder.batismoLayout.setVisibility(View.VISIBLE);
            if (capela.getBatismoReunicoes() != null) {
                comunidadeViewHolder.reuniaoBatismoLayout.setVisibility(View.VISIBLE);
                comunidadeViewHolder.reuniaoBatismo.setText(capela.getBatismoReunicoes());
            } else {
                comunidadeViewHolder.reuniaoBatismoLayout.setVisibility(View.GONE);
            }
            if (capela.getBatizadosHorario() != null) {
                comunidadeViewHolder.horaBatismoLayout.setVisibility(View.VISIBLE);
                comunidadeViewHolder.horaBatismo.setText(capela.getBatizadosHorario());
            } else {
                comunidadeViewHolder.horaBatismo.setVisibility(View.GONE);
            }
        } else {
            comunidadeViewHolder.batismoLayout.setVisibility(View.GONE);
        }

        if (capela.getHorarios() == null) {
            comunidadeViewHolder.verHorariosBtn.setVisibility(View.GONE);
        } else {
            comunidadeViewHolder.verHorariosBtn.setVisibility(View.VISIBLE);
        }

        if (capela.getDataFestaPadroeiro() == null) {
            comunidadeViewHolder.festaPadroeiroBtn.setVisibility(View.GONE);
        } else {
            comunidadeViewHolder.festaPadroeiroBtn.setVisibility(View.VISIBLE);
        }
    }

    private void setActionButtonsCard(ComunidadeViewHolder comunidadeViewHolder, final Capela capela) {
        comunidadeViewHolder.verHorariosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IHSUtil.generateModalInfoListItems(
                        context.getString(R.string.horarios_label_modal) + " " + capela.getNome(),
                        capela.getHorarios(),
                        context
                );
            }
        });

        comunidadeViewHolder.festaPadroeiroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IHSUtil.generateModalInfo(context.getString(R.string.modal_title_comunidade), capela.getDataFestaPadroeiro(), context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return capelaList != null ? capelaList.size() : 0;
    }
}
