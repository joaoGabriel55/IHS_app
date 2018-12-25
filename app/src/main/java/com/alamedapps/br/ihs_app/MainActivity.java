package com.alamedapps.br.ihs_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alamedapps.br.ihs_app.fragment.FragmentClero;
import com.alamedapps.br.ihs_app.fragment.FragmentComunidade;
import com.alamedapps.br.ihs_app.fragment.FragmentEvento;
import com.alamedapps.br.ihs_app.fragment.FragmentGrupo;
import com.alamedapps.br.ihs_app.fragment.FragmentMap;
import com.alamedapps.br.ihs_app.fragment.FragmentReligiosidade;
import com.alamedapps.br.ihs_app.fragment.FragmentSecretaria;
import com.alamedapps.br.ihs_app.fragment.FragmentTaxas;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference;
    private Fragment fragment;
    private Toolbar toolbar;

    private FragmentGrupo fragmentGrupo;
    private SearchView searchView;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item = findViewById(R.id.action_search);

        //fragmentGrupo = new FragmentGrupo();
        //preencheDados();
        //testeSet();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_home, null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (fragment instanceof FragmentEvento)
                super.onBackPressed();
            else
                showHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

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
                if (fragment instanceof FragmentGrupo) {
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

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.nav_igreja_acao) {
            searchView.setVisibility(View.VISIBLE);
        } else {
            searchView.setVisibility(View.GONE);
        }

        displaySelectedScreen(item.getItemId(), item);
        return true;
    }


    private void displaySelectedScreen(int itemId, MenuItem item) {

        //creating fragment object
        fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new FragmentEvento();
                break;
            case R.id.nav_secretaria:
                fragment = new FragmentSecretaria();
                break;
            case R.id.nav_clero:
                fragment = new FragmentClero();
                break;
            case R.id.nav_igreja_acao:
                fragment = new FragmentGrupo();
                break;
            case R.id.nav_comunidade:
                fragment = new FragmentComunidade();
                break;
            case R.id.nav_religiosidade:
                fragment = new FragmentReligiosidade();
                break;
            case R.id.nav_taxas:
                fragment = new FragmentTaxas();
                break;
            case R.id.nav_map:
                //startActivity(new Intent(this, MapActivity.class));
                fragment = new FragmentMap();
                if (item != null)
                    item.setCheckable(true);
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            loadFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentLayout, fragment);
        ft.commit();
    }

    private void showHome() {
        //replacing the fragment
        fragment = new FragmentEvento();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayout, fragment);
            ft.commit();
        }
    }

    private void preencheDados() {

        databaseReference = IHSUtil.getDatabase().getReference().child("religiosidade");

        Oracao oracao1 = new Oracao(1, "Oração a Nossa Senhora da Esperança (Padroeira)", "Senhora da Esperança, tua alegria era fazer a vontade do Pai.\n" +
                "Tua vida era estar atenta às necessidades dos outros.\n" +
                "Intercede por nós!\n" +
                "Quando nossa fé vacilar, quando somos tentados a desesperar.\n" +
                "Senhora da Esperança, intercede por nós!\n" +
                "Quando fechamos o coração, quando consentimos à injustiça.\n" +
                "Senhora da Esperança, intercede por nós!\n" +
                "Quando parece ser difícil seguir teu filho, quando nos cansamos de fazer o bem.\n" +
                " Senhora da Esperança, intercede por nós!\n" +
                "Quando o não se antecipa ao nosso sim.\n" +
                " Senhora da Esperança, leva-nos a Jesus Cristo, nossa esperança.\n" +
                "Amém.");

        Oracao oracao2 = new Oracao(2, "Oração de Santo Inácio de Loyola (Co-padroeiro)", "Oração de Santo Inácio de Loyola\n" +
                "Tomai, Senhor, e recebei\n" +
                "Toda a minha liberdade,\n" +
                "A minha memória,\n" +
                "O meu entendimento,\n" +
                "E toda a minha vontade.\n" +
                "Tudo o que tenho e possuo, de vós, Senhor, o recebi.\n" +
                "A Vós, Senhor, o entrego e restituo.\n" +
                "Para que disponhais de tudo\n" +
                "Segundo a vossa Santíssima vontade.\n" +
                "Dai-me o vosso amor e vossa graça e isto me basta. \n" +
                "Nenhuma outra coisa desejo,\n" +
                "de Vossa misericórdia infinita,\n" +
                "Amém.");

        databaseReference.push().setValue(oracao1);
        databaseReference.push().setValue(oracao2);
    }

    private void testeSet() {

        TaxasEmolumentos t1 = new TaxasEmolumentos(1, "Intenções Gerais", 2.0);

        IHSUtil.getDatabase().getReference("taxasEmolumentos").child("-LIqma6OBSFGzEpgw6J4").setValue(t1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });
    }
}
