package com.alamedapps.br.ihs_app.fragment.news;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

/**
 * Fragment FullScreen para a noticia da paraquia selecionada
 */
public class DialogFragmentNewsSelected extends DialogFragment {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageNews;

    private TextView textViewTitleNews;
    private TextView textViewContentNews;
    private TextView textViewDateNews;

    private AppBarLayout appBarLayout;

    private String titleNewsParam;
    private String contentNewsParam;
    private String imageNewsParam;
    private String dateNewsParam;

    public static DialogFragmentNewsSelected newInstance() {
        return new DialogFragmentNewsSelected();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogThemeNews);

        Bundle b = getArguments();
        titleNewsParam = b.getString(NewsAdapter.TITLE_NEWS_KEY);
        contentNewsParam = b.getString(NewsAdapter.CONTENT_NEWS_KEY);
        imageNewsParam = b.getString(NewsAdapter.IMAGE_NAME_NEWS_KEY);
        dateNewsParam = b.getString(NewsAdapter.DATE_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_selected, container, false);

        appBarLayout = view.findViewById(R.id.app_bar_layout_news);

        imageNews = view.findViewById(R.id.image_news_selected);

        textViewTitleNews = view.findViewById(R.id.title_news_selected);
        textViewContentNews = view.findViewById(R.id.content_news_selected);
        textViewDateNews = view.findViewById(R.id.date_news_selected);

        textViewTitleNews.setText(titleNewsParam);
        textViewContentNews.setText(contentNewsParam);
        textViewDateNews.setText(dateNewsParam);

        IHSUtil.handleImage(imageNews, "news/" + imageNewsParam);

        toolbar = view.findViewById(R.id.toolbar_news);
//
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back_inverted);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle("");
                toolbar = null;
                collapsingToolbarLayout = null;
                getArguments().clear();
                getFragmentManager().beginTransaction().detach(getFragmentManager().getPrimaryNavigationFragment());
                getDialog().cancel();
                dismiss();
            }
        });

        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_layout);

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

        return view;
    }
}
