package com.alamedapps.br.ihs_app.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.EventoAdapter;
import com.alamedapps.br.ihs_app.dao.EventoDAOImpl;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentEvento extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private EventoAdapter adapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private Evento evento;

    private View v;

    private ImageView imageView;

    public FragmentEvento(){
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_eventos, container, false);

        mDatabaseReference = mFirebaseDatabase.getReference().child("evento");

        recyclerView = v.findViewById(R.id.recyclerviewEventos);

        adapter = new EventoAdapter(null, getContext());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        EventoDAOImpl eventoDAO = new EventoDAOImpl();
        Evento evento = new Evento();
        eventoDAO.getAllEventos(mChildEventListener, evento, mDatabaseReference, adapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.eventos);
    }
}
