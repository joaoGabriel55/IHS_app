package com.alamedapps.br.ihs_app.fragment.news;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

public class NewsSelectedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageNews;

    private TextView textViewTitleNews;
    //    private TextView textViewContentNews;
    private WebView textViewContentNews;
    private TextView textViewDateNews;


    private AppBarLayout appBarLayout;

    private String titleNewsParam;
    private String contentNewsParam;
    private String imageNewsParam;
    private String dateNewsParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_selected);

        Bundle b = getIntent().getExtras();
        titleNewsParam = b.getString(NewsAdapter.TITLE_NEWS_KEY);
        contentNewsParam = b.getString(NewsAdapter.CONTENT_NEWS_KEY);
        imageNewsParam = b.getString(NewsAdapter.IMAGE_NAME_NEWS_KEY);
        dateNewsParam = b.getString(NewsAdapter.DATE_KEY);

        appBarLayout = findViewById(R.id.app_bar_layout_news);

        imageNews = findViewById(R.id.image_news_selected);

        textViewTitleNews = findViewById(R.id.title_news_selected);
        textViewContentNews = findViewById(R.id.webView_text);
        textViewDateNews = findViewById(R.id.date_news_selected);

        textViewTitleNews.setText(titleNewsParam);
        textViewContentNews.loadData(contentNewsParam, "text/html", "utf-8");

        textViewDateNews.setText(dateNewsParam);

        IHSUtil.handleImage(imageNews, "news/" + imageNewsParam);

        toolbar = findViewById(R.id.toolbar_news);
//
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back_inverted);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().toString() == "EXPANDED") {
                    toolbar.setTitle("");
                    collapsingToolbarLayout.setTitleEnabled(false);
                } else {
                    toolbar.setTitle(titleNewsParam);
                    collapsingToolbarLayout.setTitleEnabled(true);
                    collapsingToolbarLayout.setTitle(titleNewsParam);
                }

//                Log.d(getClass().getCanonicalName(), state.name());
            }
        });
    }


}
