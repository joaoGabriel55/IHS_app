package com.alamedapps.br.ihs_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alamedapps.br.ihs_app.activity.MapActivity;
import com.alamedapps.br.ihs_app.fragment.FragmentClero;
import com.alamedapps.br.ihs_app.fragment.FragmentEvento;
import com.alamedapps.br.ihs_app.fragment.FragmentGrupo;
import com.alamedapps.br.ihs_app.fragment.FragmentMap;
import com.alamedapps.br.ihs_app.fragment.FragmentSecretaria;
import com.alamedapps.br.ihs_app.fragment.FragmentTaxas;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.models.igrejaemacao.CategoriaGrupo;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference databaseReference;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //preencheLocal();

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

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
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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

    public void preencheLocal() {

        databaseReference = IHSUtil.getDatabase().getReference().child("taxasEmolumentos");

        TaxasEmolumentos t1 = new TaxasEmolumentos(1, "Intenções Gerais", 5.00);
        TaxasEmolumentos t2 = new TaxasEmolumentos(2, "Batizado", 35.00);
        TaxasEmolumentos t3 = new TaxasEmolumentos(3, "Crisma", 20.00);
        TaxasEmolumentos t4 = new TaxasEmolumentos(4, "Casamento Religioso", 100.00);
        TaxasEmolumentos t5 = new TaxasEmolumentos(5, "Casamento Religioso com Efeito Civil", 120.00);
        TaxasEmolumentos t6 = new TaxasEmolumentos(6, "Certidão de Batismo e Casamento", 40.00);
        TaxasEmolumentos t7 = new TaxasEmolumentos(7, "Taxa do dízimo paroquial mensal é igual a 10% do salário.", 0.0);

        databaseReference.push().setValue(t1);
        databaseReference.push().setValue(t2);
        databaseReference.push().setValue(t3);
        databaseReference.push().setValue(t4);
        databaseReference.push().setValue(t5);
        databaseReference.push().setValue(t6);
        databaseReference.push().setValue(t7);
    }
}
