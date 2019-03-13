package com.alamedapps.br.ihs_app.fragment.grupo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alamedapps.br.ihs_app.MainActivity;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.dao.GrupoDAOImpl;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

import java.util.List;

public class FragmentGrupo extends Fragment {
    private View v;

    private RecyclerView recyclerViewGrupo;

    private GrupoAdapter grupoAdapter;

    private ConstraintLayout layoutNotFound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.pastorais_movimentos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_grupo, container, false);

        layoutNotFound = v.findViewById(R.id.not_found_screen);

        loadData();

        return v;
    }

    private void loadData() {
        GrupoDAOImpl grupoDAO = new GrupoDAOImpl();
        recyclerViewGrupo = v.findViewById(R.id.recyclerviewGrupo);
        grupoAdapter = new GrupoAdapter(null, getContext());
        IHSUtil.defineRecycler(recyclerViewGrupo, grupoAdapter, getContext(), LinearLayout.VERTICAL, false);
        grupoDAO.getAll(grupoAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.pastorais_movimentos);
    }

    @Override
    public void onStart() {
        super.onStart();
        //loadData();
    }

    public GrupoAdapter getGrupoAdapter() {
        return this.grupoAdapter;
    }

    public void checkFiltedListSize(List<Grupo> grupoListFilted) {
        if (grupoListFilted == null || grupoListFilted.size() == 0) {
            layoutNotFound.setVisibility(View.VISIBLE);
            recyclerViewGrupo.setVisibility(View.GONE);
        } else {
            layoutNotFound.setVisibility(View.GONE);
            recyclerViewGrupo.setVisibility(View.VISIBLE);
        }
    }
}
