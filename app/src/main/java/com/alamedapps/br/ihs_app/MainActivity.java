package com.alamedapps.br.ihs_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.alamedapps.br.ihs_app.fragment.FragmentEvento;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.igrejaemacao.CategoriaGrupo;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentEvento fragmentEvento;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //preencheLocal();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentEvento = new FragmentEvento();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentEventoLayout, fragmentEvento);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentEventoLayout, fragmentEvento);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void preencheLocal() {

        databaseReference = IHSUtil.getDatabase().getReference().child("grupo");

        ArrayList<String> documentos = new ArrayList<>();
        documentos.add("Certidão de nascimento da criança");
        documentos.add("Comprovante de preparação de Batismo dos pais e padrinhos");

        ArrayList<String> coordenadores = new ArrayList<>();
        coordenadores.add("Ana Cristina Silva de Oliveira Santos - Whatsapp 99146-7646");
        coordenadores.add("Osvaldo Vieira dos Santos - Whatsapp 99111-4264");
        coordenadores.add("Francisco de Assis Dantas Filho - Whatsapp 99902-4738");
        coordenadores.add("Lúcia de Souza Ferreira Dantas - Whatsapp 98732-8345");

        Grupo g1 = new Grupo(1, "Pastoral do Batismo",
                "A Pastoral de preparação para o Sacramento do Batismo de crianças é formada por cristãos católicos – agentes da pastoral, em apoio ao trabalho do nosso Pároco, o Padre Jonerikson Gomes da Silva, tendo como Auxiliar Espiritual o Diácono Alexsanderson Marques Madruga. O Batismo é a fonte de todas as vocações, é a fonte de vida nova em Cristo, fonte na qual brota toda a vida cristã." +
                        "A pastoral do batismo tem como objetivo, apresentar, de modo sintético e com linguagem simples, os princípios fundamentais do Sacramento e motivar para uma espiritualidade batismal, pela qual os cristãos possam viver mais intensamente o Sacramento de Jesus Cristo e tornar-se seus discípulos e suas testemunhas, através do testemunho de vida de pais e padrinhos, para com seus filhos e afilhados; " +
                        "e este também é o papel de padrinhos e madrinhas, que devem ser cristãos firmes, capazes e prontos a ajudar o novo batizado, criança ou adulto, em sua caminhada na vida cristã.",
                documentos, coordenadores, CategoriaGrupo.PASTORAL,
                "A reunião de preparação para pais e padrinhos de crianças de até seis (06) anos de idade acontece toda segunda-feira, às 19:00hs, na Escola Municipal Professora Ivonete Maciel, situada na Avenida Adolfo Gordo, 658 – 672, na cidade da esperança." +
                        "A preparação para o batismo de adultos se faz de forma especial, em dia e local acordados pelos candidatos ao batismo e o casal responsável pelas formações, ou as quartas-feiras às 17;30hs na catequese.");
        databaseReference.push().setValue(g1);

        ArrayList<String> documentos2 = new ArrayList<>();
        documentos2.add("Certidão de nascimento da criança");
        documentos2.add("Comprovante de preparação de Batismo dos pais e padrinhos");

        ArrayList<String> coordenadores2 = new ArrayList<>();
        coordenadores2.add("Danilo e Lyanna - Email catequesedaesperanca@gmail.com.br");

        Grupo g2 = new Grupo(2, "Pastoral da Catequese",
                "A Pastoral da Catequese atua na Paróquia desde 1984, nesse tempo os encontros eram realizados na sede da Legião de Maria. Em 1985, as Servas do Coração Imaculado de Maria também denominadas Irmãs do Bom Pastor iniciaram um mutirão para a construção da sede própria da catequese juntamente com a comunidade.  Em abril de 1986, foi inaugurada a nossa sede que leva o nome de Centro de Catequese Nossa Senhora da Esperança." +
                        "Desde então a Pastoral da Catequese exerce sua missão de fazer ecoar a Palavra de Deus e os ensinamentos da Santa Igreja a todos aqueles que procuram. Durante anos, várias Irmãs como: Ir Claudete, Cristina, Verônica,  Iraci, Erineide, Aparecida,  Ednaide e muitas outras, nos orientaram a  acolher com carinho crianças, jovens e adultos, preparando-os para receber os Sacramentos da Eucaristia e Crisma e também para uma vida de serviço a comunidade." +
                        "São admitidas crianças a partir dos 7 (sete) anos de idade para a turminha da Pré catequese. Para preparação para o Sacramento da Eucaristia temos as turmas de Eucaristia I e II e para ingressar a criança deve ter 8 (oito) anos completos. Para aquelas crianças que já fizeram a Primeira Comunhão temos a turma da Perseverança. Para inscrição na Crisma o jovem deve ter 13 (treze) anos completos e fazer 14 (catorze) anos até o mês de março do ano seguinte.",
                null, coordenadores2, CategoriaGrupo.PASTORAL,
                "A reunião de preparação para pais e padrinhos de crianças de até seis (06) anos de idade acontece toda segunda-feira, às 19:00hs, na Escola Municipal Professora Ivonete Maciel, situada na Avenida Adolfo Gordo, 658 – 672, na cidade da esperança." +
                        "A preparação para o batismo de adultos se faz de forma especial, em dia e local acordados pelos candidatos ao batismo e o casal responsável pelas formações, ou as quartas-feiras às 17;30hs na catequese.");
        databaseReference.push().setValue(g2);

        ArrayList<String> documentos3 = new ArrayList<>();
        documentos3.add("Certidão de nascimento da criança");
        documentos3.add("Comprovante de preparação de Batismo dos pais e padrinhos");

        ArrayList<String> coordenadores3 = new ArrayList<>();
        coordenadores3.add("Neirivan - Tel. 99984-4496");

        Grupo g3 = new Grupo(3, "Pastoral do Dízimo",
                "Quando assumiu a Arquidiocese de natal no final de 1993, o então arcebispo Dom Heitor de Araújo Sales percebendo a dificuldade para manutenção das paróquias logo começou a defender a implantação da pastoral do dízimo, fato que aconteceu oficialmente, em 1997. O objetivo é evangelização em primeiro lugar e depois fazer que os fiéis entendam que devolver o dízimo é uma questão de gratidão e amor para com nossa igreja.",
                null, coordenadores3, CategoriaGrupo.PASTORAL,
                "Última quinta-feira do mês na Paróquia Santuário de Nossa Senhora da Esperança e Santo Inácio de Loyola após a missa.");
        databaseReference.push().setValue(g3);

        Grupo g4 = new Grupo(4, "Pastoral do Idoso",
                "",
                null, null, CategoriaGrupo.PASTORAL,
                "");
        databaseReference.push().setValue(g4);


        ArrayList<String> documentos5 = new ArrayList<>();
        documentos5.add("Certidão de nascimento da criança");
        documentos5.add("Comprovante de preparação de Batismo dos pais e padrinhos");

        ArrayList<String> coordenadores5 = new ArrayList<>();
        coordenadores5.add("Neildo e Luciana - Tel. (84) 98834-0979/98848-7807");

        Grupo g5 = new Grupo(5, "Pastoral Familiar",
                "As primeiras linhas de organização da Pastoral Familiar têm precedentes históricos dos Sínodos dos Bispos realizado em 1980 em Roma, de 26 de Setembro a 25 de Outubro, no qual este foi uma continuação natural dos dois precedentes; a família cristã, de fato, é a primeira comunidade chamada a anunciar o Evangelho à pessoa humana em crescimento e levá-la, através de uma catequese e educação progressiva, à plenitude da maturidade humana e cristã." +
                        "Iluminado pela fé, o Papa, representante máximo da Igreja, fez conhecer toda a verdade sobre o precioso bem do matrimônio e da família, e sobre os seus significados mais profundos." +
                        "Num momento histórico, em que a família é alvo de numerosas forças que procuram destruir, se viu a necessidade de começarmos, inspirados pela força do Espírito Santo, a nos organizar." +
                        "Daí a determinação do Papa: que se crie a Pastoral Familiar, que seja uma ação realizada na Igreja, com a Igreja e pela Igreja, de forma ordenada, planejada, por meio de agentes específicos e com metodologia própria, tudo visando à evangelização da família." +
                        "O Encontro de Preparação para Vida Matrimonial tem como objetivo central sensibilizar os noivos para optarem livre e conscientemente pelo sacramento do matrimônio baseado no amor conjugal cristão e buscando a evangelização da sua família.",
                null, coordenadores5, CategoriaGrupo.PASTORAL,
                "Segunda e Sexta-Feira de cada mês para partilhar o Evangelho e montar as jornadas.");
        databaseReference.push().setValue(g5);

        ArrayList<String> documentos6 = new ArrayList<>();
        documentos6.add("Certidão de nascimento da criança");
        documentos6.add("Comprovante de preparação de Batismo dos pais e padrinhos");

        ArrayList<String> coordenadores6 = new ArrayList<>();
        coordenadores6.add("Jefferson Ramon - Tel. (84) 98834-0979/98848-7807");
        coordenadores6.add("Laryssa Aguiar - Tel. 98848-9965/99819-5744");

        Grupo g6 = new Grupo(6, "Pastoral da Solidariedade",
                "Conforme nos relata a Sagrada Escritura (Mt 13,1-9): “Um semeador saiu a semear. E, semeando, parte da semente caiu ao longo do caminho; os pássaros vieram e a comeram. Outra parte caiu em solo pedregoso, onde não havia muita terra, e nasceu logo, porque a terra era pouco profunda. Logo, porém, que o sol nasceu, queimou-se, por falta de raízes. Outras sementes caíram entre os espinhos: os espinhos cresceram e as sufocaram. Outras, enfim, caíram em terra boa: deram frutos, cem por um, sessenta por um, trinta por um”." +
                        "Na luz deste evangelho, em meados de agosto de 2007, um grupo de amigos da nossa comunidade, decidiu vivenciar, pela ação do Espírito Santo, uma maravilhosa experiência." +
                        "Um grupo de casais do ECC (Encontro de Casais com Cristo) da Paróquia de nossa Senhora da Esperança participando de trabalhos voluntários resolveram dar continuidade da missão ajudando o próximo. Nesses trabalhos voluntários começaram a conhecer o real sentido de se doar pelo irmão e se doar pra Cristo, e num deles iniciou a vontade de levar essa missão a demais casais do ECC." +
                        "Os membros da pastoral e convidados se encontram às sextas-feiras, a cada 15 dias, alternando entre reuniões espirituais e missões nas ruas. Em dias de missões, o grupo inicia com oração em frente à igreja, após a missa, e, de lá, sai em um comboio de carros por alguns pontos da cidade levando alimentos, roupas e principalmente a palavra de Deus." +
                        "Trabalhamos com material partilhado. São doações de roupas, alimentos e amor." +
                        "Evangelizar através de ações sociais com moradores de rua e comunidades carentes, alimentando sua necessidade física e principalmente, espiritual.",
                null, coordenadores6, CategoriaGrupo.PASTORAL,
                "No presente momento, estamos fazendo reuniões na quarta-feira, à cada 15 dias na casa do casal Josy e Xandão.");
        databaseReference.push().setValue(g6);








    }
}
