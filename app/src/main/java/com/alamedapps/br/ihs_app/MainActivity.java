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
import com.alamedapps.br.ihs_app.fragment.grupo.FragmentBottomNav;
import com.alamedapps.br.ihs_app.fragment.grupo.FragmentGrupo;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
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

        databaseReference = IHSUtil.getDatabase().getReference().child("religiosidade");

        Oracao oracao1 = new Oracao(3, "História de Nª Sra da Esperança", "Paróquia de Nossa Senhora da Esperança – Cidade da Esperança – Natal\n" +
                "As senhoras católicas de natal, estão promovendo festividades, reuniões familiares nos clubes da cidade, afim de conseguirem arrecadar auxílios suficientes para edificação de uma capela que terá o nome de Nossa Senhora da Esperança.\n" +
                "É uma Idéia feliz essa das senhoras católicas de natal, que deve ser visto, por todos com um movimento importante, pois é uma homenagem mais correta e mais do coração que se vai prestar a Nossa Senhora, dedicando-lhe mais um templo, mais uma casa de oração.\n" +
                "Natal conta com um bom número de Igrejas e capelas em que Nossa Senhora tem nela seu nome e algumas com mais de século, porém nunca é demais quando aquele que se deseja homenagear é possuído de grandes méritos.\n" +
                "Nossa Senhora da Esperança! É um titulo sugestivo, grande, elevado. Para as criaturas atribuladas, cercadas de dificuldades, às vezes, quando, desamparados, elevam seus pensamentos aos céu, e invoca aquela que é Mãe da Esperança.\n" +
                "A devoção à Nossa Senhora é universal. Para manifestação dessa verdade aí estão os vários títulos pelos quais, ela é invocada.\n" +
                "Todos os Títulos, ou invocações que se dá à Nossa Senhora que são conhecidas até hoje, vieram com o correr dos tempos e foram aparecendo na razão das necessidades e dos acontecimentos e todos receberam da Igreja a plena aprovação.\n" +
                "“….. No Brasil, a devoção à Nossa Senhora da Esperança nasceu na manhã de seu descobrimento, veio de Portugal, trazida pelas mãos de Pedro Álvares Cabral. No século XVI, quando os padres jesuítas deram início em 1549 ao aldeamento e catequizarão, dos índios, já encontraram com os colonizadores, bem firmados à devoção e ao culto à Nossa Senhora. Em muitos lugares, era conhecida com os nomes da Ajuda, da Conceição, do Carmo, Do Rosário e da Esperança.\n" +
                "É bom, sim, que se multipliquem os templos dedicados à Nossa Senhora, para que desse modo se manifestem com intensidade no coração do povo, mais fé e mais confiança naquela que tudo pode.”");
        Oracao oracao2 = new Oracao(4, "História de Santo Inácio de Loyola", "Seu nome de batismo era Iñigo Lopez de Loyola. Nasceu em 1491, numa família rica, nobre e cristã, na cidade de Azpeitia, pertencente à província basca de Guipuzcoa, Espanha. Era o caçula de treze irmãos. Sua educação foi toda voltada para fazer dele um aristocrata. Por isso, ele cresceu no meio do luxo da corte. Era praticante de esportes, dedicando-se mais aos equestres. \n" +
                "\n" +
                "Poder e esportes\n" + "\n" +
                "No ano 1506, sua família Lopez de Loyola prestava serviço ao tesoureiro do reino de Castela chamado João Velásquez de Cuellar, de quem Iñigo era parente. Um ano depois, Iñigo foi feito cortesão e pagem no grande castelo desse tesoureiro. Lá, estudou e adquiriu grande cultura. Tornou-se excelente cavaleiro e passou a apreciar as aventuras militares. Por seu temperamento, valorizava mais o orgulho que os prazeres da luxúria. \n" +
                "\n" +
                "Militar\n" + "\n" +
                "Dez anos mais, em 1517, aos 26 anos, Iñigo abraçou a carreira militar. Como tal, foi prestar seus serviços a outro parente muito importante: conhecido como duque de Najera. Este era nada menos que o vice-rei de Navarra. Iñigo defendeu seu parente real em inúmeras batalhas, tanto militares quanto diplomáticas. \n" +
                "\n" +
                "Uma bala de canhão muda sua história\n" + "\n" +
                "Aconteceu, porém, que no dia 20 de maio de 1521, a bala de um canhão mudou sua história. Esta causou-lhe um grave ferimento na tíbia da perna esquerda, quando ele lutava defendendo a cidade de Pamplona. Por causa desse ferimento, Iñigo teve que ficar um longo tempo em recuperação. Nesse período, por acaso, deixou de ler romances de guerra e infantaria e começou a ler livros sobre a vida de vários santos e sobre a Paixão de Cristo. E assim, a graça de deus o tocou. Incentivado e poiado por irmã de sangue que cuidava dele, Iñigo abandonou de vez os livros que antes amava e passou a ler apenas e tão somente livros religiosos. Uma vez curado, decidiu trocar a vida militar pela dedicação a Deus.\n" +
                "\n" +
                "Uma espada ficou pendurada\n" + "\n" +
                "Um gesto marcou a decisão de Iñigo. Ele foi à capela do santuário de Nossa Senhora de Montserrat e, lá, deixou sua espada pendurada no altar. Tendo feito isso, deu as costas ao mundo, à corte e às aparências. De 1522 a 1523, retirou-se passando a vier numa caverna em Manresa. Vivia vide de eremita e mendigava para sobreviver. Passou esse tempo em penitência e solidão. Passou por sérias necessidades. Por outro lado, esse período foi bastante fértil. Durante esse tempo ele preparou toda a base de sua obra mais importante: o livro intitulado \"Exercícios espirituais\". Do campo de batalhas Iñigo assumiu a grande batalha espiritual, indo, depois, estudar filosofia e teologia nas cidades de Paris e Veneza.\n" +
                "\n" +
                "Nasce a Companhia de Jesus\n" + "\n" +
                "Em Paris, Iñigo conheceu seis amigos. Juntos, eles fundaram a Companhia de Jesus em 15 de agosto de 1534. Entre esses amigos estava São Francisco Xavier, um dos maiores missionários da Ordem, grande evangelizador da Ásia e do Japão. Este grupo de irmãos na fé só receberam a ordenação sacerdotal em 1537, ao concluírem os estudos. Na ordenação, Iñigo assumiu o nome de Inácio. Depois de três anos, o papa Paulo III deu aprovação oficial à nova Ordem. Inácio de Loyola foi eleito para assumir o posto de superior-geral. \n" +
                "\n" +
                "Missionário enviando missionários\n" + "\n" +
                "Santo Inácio de Loyola formou e enviou missionários jesuítas a várias partes do mundo. Eles tinham a missão de implantarem a fé cristã, especialmente entre povos nativos pagãos das terras mais longínquas das Américas e da Ásia. Seus missionários jesuítas levaram o Evangelho de Jesus Cristo de maneira heroica e poderosa aos lugares mais improváveis e desconhecidos. Muitos morreram martirizados por causa da fé em Cristo, deixando maravilhosos testemunhos de coragem, fé e amor a Deus.\n" +
                "\n" +
                "Morte\n" + "\n" +
                "Por outro lado, desde que Santo Inácio assumiu o cargo de superior geral da Ordem, sua saúde só piorou. Muito debilitado, ele veio a falecer em 31 de julho de 1556, em Roma. Tinha, então, 65 anos. Sua canonização foi celebrada pelo papa Gregório XV no ano 1622. Em 1922 o Papa Pio XI o declarou Padroeiro dos Retiros Espirituais. \n" +
                "\n" +
                "Fonte: https://cruzterrasanta.com.br");

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
