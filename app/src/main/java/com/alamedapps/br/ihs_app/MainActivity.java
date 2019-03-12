package com.alamedapps.br.ihs_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    private FragmentGrupo fragmentGrupo;
    private SearchView searchView;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        preencheDados();

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

        displaySelectedScreen(R.id.nav_news, null);
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

        databaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");


        News news = new News("Real confirma a volta de Zidane; Solari pode ter um novo cargo", "Sociedade Esportiva Palmeiras (pronúncia em português: /sosiedˈadʒi ispoɾtʃˈivə pawmˈejɾəs/[3]), conhecida popularmente como o Palmeiras, é um clube poliesportivo brasileiro sediado em São Paulo que tem como modalidade esportiva principal o futebol, com um dos times mais vencedores e que está entre as equipes com maior torcida do País. As cores do clube, presentes no escudo e bandeira oficial, são o verde e branco.[4] O vermelho, presente desde sua fundação em 1914, foi excluído durante a Segunda Guerra Mundial, por pressão do governo nacional, na mesma reunião que formalizou a mudança de nome de Palestra Itália para Palmeiras.[5]\n" +
                "\n" +
                "Seus títulos mais importantes conquistados no futebol são a Copa Libertadores da América de 1999[6] e a Copa Rio de 1951, considerado na época como um Mundial de Clubes de futebol[7] e reconhecido como tal pela FIFA, por meio do presidente da entidade, Joseph Blatter, em agosto de 2014[8][9] e por meio de documento encaminhado ao Ministério do Esporte do Brasil em novembro do mesmo ano.[10][11] A entidade, no entanto, não reconhece a competição como um torneio oficial FIFA e reforçou este posicionamento em outubro de 2017, quando reconheceu os vencedores da Copa Intercontinental como campeões mundiais,[12] sem, também, promover a unificação da Copa Intercontinental com a sua atual competição.[13][14]\n" +
                "\n" +
                "O Palmeiras é a equipe brasileira com o maior número de títulos de abrangência nacional conquistados, sendo o único a vencer todas as competições oficiais que disputou criadas no País, inicialmente pela Confederação Brasileira de Desportos (CBD) e, a partir de 1980, pela Confederação Brasileira de Futebol (CBF).[15] O alviverde possui 14 conquistas deste porte,[16] com destaque maior para seus dez títulos do Campeonato Brasileiro (atual recordista):[17] 1960, 1967(1), 1967(2), 1969, 1972, 1973, 1993, 1994, 2016 e 2018. Além destes campeonatos, o Palmeiras já venceu no país as Copas do Brasil de 1998, 2012 e de 2015 e a Copa dos Campeões de 2000, competições também organizadas pela entidade máxima do futebol brasileiro.\n" +
                "\n" +
                "No Estado de São Paulo, o Palmeiras também é um dos principais vencedores, com 22 conquistas do Campeonato Paulista de Futebol e mais dois títulos extra da mesma competição. Em 1996, o alviverde conquistou o estadual daquele ano com a melhor campanha de uma equipe na era profissional neste campeonato.[18] Na ocasião, foi campeão com 83 pontos ganhos em 90 possíveis, com um índice de aproveitamento de 92,2% dos pontos disputados e 102 gols marcados em 30 jogos realizados. Desde então, esta marca jamais foi alcançada por qualquer outra equipe na competição.\n" +
                "\n" +
                "No mais recente Ranking Nacional de Clubes da CBF, que leva em conta o comportamento das equipes nas últimas cinco temporadas e que foi divulgado em dezembro de 2018, o Palmeiras é o primeiro colocado, com 16 914 pontos.[19] No último ranking da confederação que levou em conta um período histórico mais abrangente do futebol brasileiro e que foi divulgado em 2011, o Palmeiras foi o líder, com 2 366 pontos.[20]\n" +
                "\n" +
                "Em 2005, no dia 11 de outubro, foi sancionada na cidade de São Paulo a Lei n.º 14.060, que definiu o dia 20 de setembro como o \"Dia da Sociedade Esportiva Palmeiras\", que passou a ser lembrado anualmente na capital paulista, já que passou a integrar o Calendário Oficial do Município.[21]",
                "news_1.jpg", new Date().toString());

        databaseReference.push().setValue(news);
    }

    private void testeGet() {

        databaseReference = IHSUtil.getDatabase().getReference(IHSUtil.DATABASE + "/news");

    }
}
