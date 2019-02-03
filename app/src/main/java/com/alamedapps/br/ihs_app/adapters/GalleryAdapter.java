package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.viewholders.GalleryViewHolder;
import com.alamedapps.br.ihs_app.models.gallery.Gallery;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter {

    private List<Gallery> galleryList;
    private Context context;
    private FragmentActivity fragmentActivity;


    public GalleryAdapter(List<Gallery> galleryList, Context context, FragmentActivity fragmentActivity) {
        if (galleryList != null) {
            this.galleryList = galleryList;
        } else {
            this.galleryList = new ArrayList<>();
        }
        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    public void add(Gallery clero) {
        galleryList.add(clero);
        notifyItemInserted(galleryList.size() + 1);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_galery, parent, false);
        GalleryViewHolder galleryViewHolder = new GalleryViewHolder(v);
        return galleryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final GalleryViewHolder galleryViewHolder = (GalleryViewHolder) holder;
        final Gallery gallery = galleryList.get(position);

        IHSUtil.handleImage(galleryViewHolder.image, "gallery/" + gallery.getImageName());

        galleryViewHolder.title.setText(gallery.getGaleryTitle());
        galleryViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(gallery.getLinkGalery());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                fragmentActivity.startActivity(intent);
            }
        });


    }

    public List<Gallery> getGalleryList() {
        return galleryList;
    }

    @Override
    public int getItemCount() {
        return galleryList != null ? galleryList.size() : 0;
    }
}
