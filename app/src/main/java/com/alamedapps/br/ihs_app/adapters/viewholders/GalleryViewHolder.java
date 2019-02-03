package com.alamedapps.br.ihs_app.adapters.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {


    public final TextView title;
    public final ImageView image;
    public final CardView cardView;

    public GalleryViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title_galery);
        image = itemView.findViewById(R.id.image_galery);
        cardView = itemView.findViewById(R.id.cardView_gallery);

    }
}