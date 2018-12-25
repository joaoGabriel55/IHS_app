package com.alamedapps.br.ihs_app.fragment;

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

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.ReligiosidadeAdapter;
import com.alamedapps.br.ihs_app.dao.ReligiosidadeDAOImpl;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentReligiosidade extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private ReligiosidadeAdapter religiosidadeAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private Oracao oracao;

    public FragmentReligiosidade() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_religiosidade, container, false);

        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.recyclerviewReligiosidade);

        religiosidadeAdapter = new ReligiosidadeAdapter(null, getContext());

        IHSUtil.defineRecycler(recyclerView, religiosidadeAdapter, getContext(), LinearLayout.VERTICAL, false);

        ReligiosidadeDAOImpl religiosidadeDAO = new ReligiosidadeDAOImpl();
        religiosidadeDAO.getAll(mChildEventListener, religiosidadeAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.religiosidade_title));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
