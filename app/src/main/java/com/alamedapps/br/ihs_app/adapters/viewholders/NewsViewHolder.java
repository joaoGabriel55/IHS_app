package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public final TextView title;
    public final TextView dateNew;
    public final ImageView image;
    public final CardView newsCard;

    public NewsViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_news);
        image = itemView.findViewById(R.id.image_news);
        dateNew = itemView.findViewById(R.id.date_new);
        newsCard = itemView.findViewById(R.id.cardView_news);
    }

}

