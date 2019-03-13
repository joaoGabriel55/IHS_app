package com.alamedapps.br.ihs_app.fragment.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alamedapps.br.ihs_app.MainActivity;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.GalleryAdapter;
import com.alamedapps.br.ihs_app.dao.GalleryDAOImpl;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentGallery extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private GalleryAdapter galleryAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private ImageView imageView;

    public FragmentGallery() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.galeria);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_clero, container, false);

        setHasOptionsMenu(true);

        imageView = v.findViewById(R.id.image_clero);

        mDatabaseReference = mFirebaseDatabase.getReference().child("galleries");

        recyclerView = v.findViewById(R.id.recyclerviewClero);

        galleryAdapter = new GalleryAdapter(null, getContext(), getActivity());

        GalleryDAOImpl galleryDAO = new GalleryDAOImpl();
        galleryDAO.getAll(mChildEventListener, mDatabaseReference, galleryAdapter);

        recyclerView.setAdapter(galleryAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.galeria));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}

