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

import com.afollestad.materialdialogs.MaterialDialog;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.dao.GrupoDAOImpl;
import com.alamedapps.br.ihs_app.dao.SecretariaDAOImpl;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSRecyclerView;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FragmentGrupo extends Fragment {
    private View v;

    private RecyclerView recyclerViewGrupo;

    private GrupoAdapter grupoAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;


    public FragmentGrupo() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_grupo, container, false);

        mDatabaseReference = mFirebaseDatabase.getReference().child("grupo");

        Query query = mFirebaseDatabase.getReference().child("grupo").orderByChild("categoriaGrupo");


        recyclerViewGrupo = v.findViewById(R.id.recyclerviewGrupo);

        grupoAdapter = new GrupoAdapter(null, getContext());

        IHSUtil.defineRecycler(recyclerViewGrupo, grupoAdapter, getContext(), LinearLayout.VERTICAL, false);

        GrupoDAOImpl grupoDAO = new GrupoDAOImpl();
        grupoDAO.getAll(mChildEventListener, query, grupoAdapter);

//        recyclerViewGrupo.addOnItemTouchListener(new IHSRecyclerView(getActivity(), recyclerView, new IHSRecyclerView.ItemTouch() {
//            @Override
//            public void clickSimples(View view, int position) {
//                new MaterialDialog.Builder(getContext())
//                        .title(cleroAdapter.getCleroList().get(position).getNome())
//                        .content(
//                                getString(R.string.data_nascimento_modal) + " " + cleroAdapter.getCleroList().get(position).getDataNascimento() + "\n" +
//                                        getString(R.string.data_ordenacao_modal) + " " + cleroAdapter.getCleroList().get(position).getDataOrdenacao() + "\n" +
//                                        getString(R.string.cargo_titulo_modal) + " " + cleroAdapter.getCleroList().get(position).getCargoTitulo() + "\n"
//                        )
//                        .positiveText(R.string.fechar)
//                        .show();
//            }
//        }));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.pastorais_movimentos);
    }
}
