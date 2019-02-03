package com.alamedapps.br.ihs_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.models.gallery.Gallery;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private AdView adView;

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

        //preencheDados();

//
//        MobileAds.initialize(this, getString(R.string.admob_key));
//
//        adView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("3834CE844F0394CC224E3FD3596D094B").build(); //addTestDevice("3834CE844F0394CC224E3FD3596D094B")
//        adView.loadAd(adRequest);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item = findViewById(R.id.action_search);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        fragment = new FragmentEvento();
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayout, fragment);
            ft.commit();
        }
    }

    private void preencheDados() {

        databaseReference = IHSUtil.getDatabase().getReference().child("galleries");

        Gallery gallery1 = new Gallery(1, "img_ihs (8).jpeg", "1° noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/eqqhEFZuYvKC6AKy6");
        Gallery gallery2 = new Gallery(2, "img_ihs (1).jpeg", "2ª noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/fS97F5RFbg94aD8S6");
        Gallery gallery3 = new Gallery(3, "img_ihs (2).jpeg", "Missa dos enfermos com unção", "https://photos.app.goo.gl/5fpeHX447qUtYNi69");
        Gallery gallery4 = new Gallery(4, "img_ihs (3).jpeg", "3ª noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/H6nLZf84eyRr3ttKA");
        Gallery gallery5 = new Gallery(5, "img_ihs (4).jpeg", "4ª noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/2NwxgTLw3mqvWmNd6");
        Gallery gallery6 = new Gallery(6, "img_ihs (5).jpeg", "5ª noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/PT4UoBvNof5wTLzg7");
        Gallery gallery7 = new Gallery(7, "img_ihs (6).jpeg", "6ª noite de novena de Nossa Senhora da Esperança", "https://photos.app.goo.gl/239G3fBhhXjGBjXS6");
        Gallery gallery8 = new Gallery(8, "img_ihs (7).jpeg", "8ª Noite da festa de Nossa Senhora da Esperança 2019", "https://photos.app.goo.gl/kZFdc3koRZdJviPK8");

        databaseReference.push().setValue(gallery1);
        databaseReference.push().setValue(gallery2);
        databaseReference.push().setValue(gallery3);
        databaseReference.push().setValue(gallery4);
        databaseReference.push().setValue(gallery5);
        databaseReference.push().setValue(gallery6);
        databaseReference.push().setValue(gallery7);
        databaseReference.push().setValue(gallery8);
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
