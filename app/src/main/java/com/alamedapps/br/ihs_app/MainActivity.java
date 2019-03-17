package com.alamedapps.br.ihs_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamedapps.br.ihs_app.fragment.FragmentClero;
import com.alamedapps.br.ihs_app.fragment.FragmentEvento;
import com.alamedapps.br.ihs_app.fragment.FragmentMap;
import com.alamedapps.br.ihs_app.fragment.FragmentSecretaria;
import com.alamedapps.br.ihs_app.fragment.FragmentTaxas;
import com.alamedapps.br.ihs_app.fragment.about.FragmentFullScreenDialogAbout;
import com.alamedapps.br.ihs_app.fragment.comunidade.FragmentReligiosidade;
import com.alamedapps.br.ihs_app.fragment.gallery.FragmentGallery;
import com.alamedapps.br.ihs_app.fragment.grupo.FragmentBottomNav;
import com.alamedapps.br.ihs_app.fragment.grupo.FragmentGrupo;
import com.alamedapps.br.ihs_app.fragment.news.FragmentNews;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.DatabaseReference;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private AdView adView;

    private DatabaseReference databaseReference;
    private Fragment fragment;
    private Toolbar toolbar;

    private SearchView searchView;
    private MenuItem item;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //preencheDados();

//
//        MobileAds.initialize(this, getString(R.string.admob_key));
//
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("3834CE844F0394CC224E3FD3596D094B").build(); //addTestDevice("3834CE844F0394CC224E3FD3596D094B")
//        adView.loadAd(adRequest);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButton = findViewById(R.id.fab_to_top);
        floatingActionButton.setVisibility(View.GONE);

        item = findViewById(R.id.action_search);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_news, null);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (fragment instanceof FragmentNews)
                super.onBackPressed();
            else
                showHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean admin = true;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        item = menu.findItem(R.id.action_search);

        searchView = (SearchView) item.getActionView();
        searchView.setVisibility(View.GONE);
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

        if (item.getItemId() == R.id.nav_news) {
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }

        displaySelectedScreen(item.getItemId(), item);
        return true;
    }


    private void displaySelectedScreen(int itemId, MenuItem item) {

        //creating fragment object
        fragment = null;
//        Class fragmentClass = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_news:
                fragment = new FragmentNews();
                break;
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
                fragment = new FragmentBottomNav();
                break;
            case R.id.nav_religiosidade:
                fragment = new FragmentReligiosidade();
                break;
            case R.id.nav_galeria:
                fragment = new FragmentGallery();
                break;
            case R.id.nav_taxas:
                fragment = new FragmentTaxas();
                break;
            case R.id.nav_map:
                fragment = new FragmentMap();
                if (item != null)
                    item.setCheckable(true);
                break;
            case R.id.facebook_link:
                sendToRedeSociais(getString(R.string.facebook_link_nav));
                break;
            case R.id.instagram_link:
                sendToRedeSociais(getString(R.string.insta_link_nav));
                break;
            case R.id.site_link:
                sendToRedeSociais(getString(R.string.site_link_nav));
                break;
            case R.id.nav_info:
                DialogFragment dialog = FragmentFullScreenDialogAbout.newInstance();
                dialog.show(getSupportFragmentManager(), "tag2");
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            loadFragment(fragment);
        }
//        item.setChecked(true);
        if (item != null)
            setTitle(item.getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private void sendToRedeSociais(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentLayout, fragment);
        ft.commit();
    }

    private void showHome() {
        //replacing the fragment
        fragment = new FragmentNews();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayout, fragment);
            ft.commit();
        }
    }

    private void preencheDados() {

        databaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");


        News news = new News("Real confirma a volta de Zidane; Solari pode ter um novo cargo",
                "Real confirma a volta de Zidane; Solari pode ter um novo cargo",
                "news_1.jpg", new Date());

        for (int i = 0; i < 20; i++)
            databaseReference.push().setValue(news);
    }

    private void testeGet() {

        databaseReference = IHSUtil.getDatabase().getReference(IHSUtil.DATABASE + "/news");

    }
}
