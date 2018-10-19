package com.alamedapps.br.ihs_app.fragment;

import android.content.ClipData;
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

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.dao.GrupoDAOImpl;
import com.alamedapps.br.ihs_app.dao.SecretariaDAOImpl;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentGrupo extends Fragment {
    private View v;
    private RecyclerView recyclerView;

    private GrupoAdapter grupoAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private Grupo grupo;

    public FragmentGrupo() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_grupo, container, false);

        mDatabaseReference = mFirebaseDatabase.getReference().child("grupo");

        recyclerView = v.findViewById(R.id.recyclerviewGrupo);

        grupoAdapter = new GrupoAdapter(null, getContext());

        IHSUtil.defineRecycler(recyclerView, grupoAdapter, getContext(), LinearLayout.VERTICAL, false);

        GrupoDAOImpl grupoDAO = new GrupoDAOImpl();
        grupo = new Grupo();
        grupoDAO.getAll(mChildEventListener, mDatabaseReference, grupoAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.pastorais_movimentos);
    }
}
