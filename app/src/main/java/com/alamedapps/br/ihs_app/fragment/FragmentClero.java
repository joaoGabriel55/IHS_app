package com.alamedapps.br.ihs_app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.CleroAdapter;
import com.alamedapps.br.ihs_app.dao.CleroDAOImpl;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentClero extends Fragment {


    private View v;
    private RecyclerView recyclerView;

    private CleroAdapter cleroAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private Clero clero;

    private ImageView imageView;

    public FragmentClero() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_clero, container, false);

        imageView = v.findViewById(R.id.profile_clero);

        mDatabaseReference = mFirebaseDatabase.getReference().child("clero");

        recyclerView = v.findViewById(R.id.recyclerviewClero);

        cleroAdapter = new CleroAdapter(null, getContext());
        recyclerView.setAdapter(cleroAdapter);
       // RecyclerView.LayoutManager layout = new GridLayoutManager(getContext(), 2);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        CleroDAOImpl cleroDAO = new CleroDAOImpl();
        clero = new Clero();
        cleroDAO.getAll(mChildEventListener, mDatabaseReference, cleroAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.clero);
    }
}
