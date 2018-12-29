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
import com.alamedapps.br.ihs_app.adapters.TaxasAdapter;
import com.alamedapps.br.ihs_app.dao.TaxasDAOImpl;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentTaxas extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private TaxasAdapter taxasAdapter;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private TaxasEmolumentos taxasEmolumentos;

    public FragmentTaxas() {
        mFirebaseDatabase = IHSUtil.getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_taxas, container, false);

        setHasOptionsMenu(true);

        mDatabaseReference = mFirebaseDatabase.getReference().child("taxasEmolumentos");

        recyclerView = v.findViewById(R.id.recyclerviewTaxas);

        taxasAdapter = new TaxasAdapter(null, getContext());

        IHSUtil.defineRecycler(recyclerView, taxasAdapter, getContext(), LinearLayout.VERTICAL, false);

        TaxasDAOImpl taxasDAO = new TaxasDAOImpl();
        TaxasEmolumentos taxasEmolumentos = new TaxasEmolumentos();
        taxasDAO.getAll(mChildEventListener, mDatabaseReference, taxasAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.taxas));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
