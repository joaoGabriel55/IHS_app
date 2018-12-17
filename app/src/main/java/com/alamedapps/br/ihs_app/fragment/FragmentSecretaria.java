package com.alamedapps.br.ihs_app.fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.EventoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.dao.EventoDAOImpl;
import com.alamedapps.br.ihs_app.dao.SecretariaDAOImpl;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.alamedapps.br.ihs_app.viewholders.SecretariaViewHolder;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentSecretaria extends Fragment {
    private View v;
    private RecyclerView recyclerView;

    private SecretariaAdapter secretariaAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private Secretaria secretaria;

    public FragmentSecretaria() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_secretaria, container, false);

        setHasOptionsMenu(true);

        mDatabaseReference = mFirebaseDatabase.getReference().child("secretaria");

        recyclerView = v.findViewById(R.id.recyclerviewSecretaria);

        secretariaAdapter = new SecretariaAdapter(null, getContext());
        recyclerView.setAdapter(secretariaAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        SecretariaDAOImpl secretariaDAO = new SecretariaDAOImpl();
        secretaria = new Secretaria();
        secretariaDAO.getAllEventos(mChildEventListener, secretaria, mDatabaseReference, secretariaAdapter);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.secretaria);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
