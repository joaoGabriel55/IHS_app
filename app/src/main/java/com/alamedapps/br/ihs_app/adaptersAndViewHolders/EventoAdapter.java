package com.alamedapps.br.ihs_app.adaptersAndViewHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter{

    private List<Evento> eventoList;
    /**
     * Contexto da aplicação
     */
    private Context context;

    public EventoAdapter(List<Evento> eventoList, Context context) {
        if (eventoList != null) {
            this.eventoList = eventoList;
        } else {
            this.eventoList = new ArrayList<>();
        }
        this.context = context;
    }

    public void add(Evento evento) {
        eventoList.add(evento);
        notifyItemInserted(eventoList.size() + 1);
    }

    public void clear() {
        eventoList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_eventos, parent, false);
        EventoViewHolder localViewHolder = new EventoViewHolder(v);
        return localViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final EventoViewHolder eventoViewHolder = (EventoViewHolder) holder;
        Evento evento = eventoList.get(position);
        eventoViewHolder.nomeEvento.setText(evento.getDescricao());
        eventoViewHolder.horarios.setText(evento.getHorarios().toString());
    }

    @Override
    public int getItemCount() {
        return eventoList != null ? eventoList.size() : 0;
    }
}
