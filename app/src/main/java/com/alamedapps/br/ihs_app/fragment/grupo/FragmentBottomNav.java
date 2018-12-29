package com.alamedapps.br.ihs_app.fragment.grupo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;

public class FragmentBottomNav extends Fragment {

    private View view;
    private Fragment fragment;

    private FragmentGrupo fragmentGrupo;
    private SearchView searchView;
    private MenuItem item;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);

        setHasOptionsMenu(true);
        fragment = new FragmentGrupo();
        loadFragment(fragment);

        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movimento:
                    fragment = new FragmentGrupo();
                    break;

                case R.id.navigation_comunidade:
                    fragment = new FragmentComunidade();
                    break;
            }

            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }
            return false;
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        item = menu.findItem(R.id.action_search);

        searchView = (SearchView) item.getActionView();
        searchView.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (fragment != null) {
                    fragmentGrupo = (FragmentGrupo) fragment;
                    fragmentGrupo.getGrupoAdapter().getFilter().filter(newText);

                    if (newText.length() == 0) {
                        fragment = new FragmentGrupo();
                        loadFragment(fragment);
                    }

                    if (fragmentGrupo.getGrupoAdapter().getGrupoList() == null) {
                        fragmentGrupo.checkFiltedListSize(fragmentGrupo.getGrupoAdapter().getGrupoList());
                    } else {
                        fragmentGrupo.checkFiltedListSize(fragmentGrupo.getGrupoAdapter().getGrupoList());
                    }
                }
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (fragment instanceof FragmentGrupo) {
                    fragment = new FragmentGrupo();
                    loadFragment(fragment);
                }
                return false;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }
}
