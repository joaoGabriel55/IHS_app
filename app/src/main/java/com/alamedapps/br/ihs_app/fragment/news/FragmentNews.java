package com.alamedapps.br.ihs_app.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.dao.NewsDAOImpl;
import com.alamedapps.br.ihs_app.fragment.comunidade.FragmentFullScreenDialog;
import com.alamedapps.br.ihs_app.utils.IHSRecyclerView;

public class FragmentNews extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_clero, container, false);

        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.recyclerviewClero);

        newsAdapter = new NewsAdapter(null, getContext(), getActivity());

        NewsDAOImpl newsDAO = new NewsDAOImpl();
        newsDAO.getAll(newsAdapter);

        recyclerView.setAdapter(newsAdapter);
//        RecyclerView.LayoutManager layout = new GridLayoutManager(getContext(), 2);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.noticias));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
