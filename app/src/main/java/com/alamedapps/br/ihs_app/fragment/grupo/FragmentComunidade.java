package com.alamedapps.br.ihs_app.fragment.grupo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alamedapps.br.ihs_app.MainActivity;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.ComunidadeAdapter;
import com.alamedapps.br.ihs_app.adapters.ReligiosidadeAdapter;
import com.alamedapps.br.ihs_app.dao.ComunidadeDAOImpl;
import com.alamedapps.br.ihs_app.dao.ReligiosidadeDAOImpl;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentComunidade extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private ComunidadeAdapter comunidadeAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    public FragmentComunidade() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.comunidade_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_comunidade, container, false);

        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.recyclerviewComunidade);

        comunidadeAdapter = new ComunidadeAdapter(null, getContext());

        IHSUtil.defineRecycler(recyclerView, comunidadeAdapter, getContext(), LinearLayout.VERTICAL, false);

        ComunidadeDAOImpl comunidadeDAO = new ComunidadeDAOImpl();
        comunidadeDAO.getAll(mChildEventListener, comunidadeAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.pastorais_movimentos);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
