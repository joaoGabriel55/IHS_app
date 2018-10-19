package com.alamedapps.br.ihs_app.utils;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.models.Clero;
import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class IHSUtil {

    private static FirebaseDatabase database;
    private static String generatedFilePath;

    public static FirebaseDatabase getDatabase() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        return database;
    }


    public static void defineRecycler(RecyclerView recyclerView, RecyclerView.Adapter adapter, Context context, int orientation, boolean reverseLayout) {
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(context, orientation, reverseLayout);
        recyclerView.setLayoutManager(layout);
    }

    public static void handleImage(ImageView imageView, Clero clero, Context context) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference().child(clero.getImage());



        Glide.with(context).load(generatedFilePath).into(imageView);

    }
}
