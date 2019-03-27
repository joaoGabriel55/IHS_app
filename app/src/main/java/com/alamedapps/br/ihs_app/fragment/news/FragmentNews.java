package com.alamedapps.br.ihs_app.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.MainActivity;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.dao.NewsDAOImpl;

import static android.view.View.GONE;

public class FragmentNews extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private NewsAdapter newsAdapter;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    private LinearLayout layoutLoading;
    private ProgressBar progressBar;
    private TextView tvStatusNews;


    private FloatingActionButton floatingActionButton;
    private Animation fabOpen, fabClose;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null && ((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.noticias);

            floatingActionButton = ((MainActivity) getActivity()).findViewById(R.id.fab_to_top);
            floatingActionButton.setVisibility(GONE);

            fabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
            fabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);

        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.recyclerviewNews);
        progressBar = v.findViewById(R.id.progressBar_news);
        layoutLoading = v.findViewById(R.id.layout_loading);
        tvStatusNews = v.findViewById(R.id.textStatus_news);

        final LinearLayoutManager layout = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, true
        );
        layout.setReverseLayout(true);
        layout.setStackFromEnd(true);
        recyclerView.setLayoutManager(layout);
        newsAdapter = new NewsAdapter(null, getContext(), getActivity(), recyclerView);

        NewsDAOImpl newsDAO = new NewsDAOImpl();
        newsDAO.getAll(newsAdapter, layoutLoading, recyclerView, progressBar, tvStatusNews, floatingActionButton);

        recyclerView.setAdapter(newsAdapter);

        scrollOutItems = layout.findFirstVisibleItemPosition();

        if (newsAdapter.getNewsList() != null && newsAdapter.getNewsList().size() > 10)
            recyclerViewScrollBehavior(recyclerView, layout);

        return v;
    }

    private void recyclerViewScrollBehavior(RecyclerView recyclerView, final LinearLayoutManager layout) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int currentFirstVisible = layout.findFirstVisibleItemPosition();

                //When scroll down...
                if (currentFirstVisible < scrollOutItems) {
                    if (isScrolling) {
                        floatingActionButton.startAnimation(fabOpen);
                        isScrolling = false;
                    }

                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout.scrollToPositionWithOffset(18, 0);
                            floatingActionButton.setVisibility(GONE);
                        }
                    });
                }

                if (layout.findFirstCompletelyVisibleItemPosition() == 15) {
                    floatingActionButton.startAnimation(fabClose);
                    floatingActionButton.setVisibility(GONE);
                }

                scrollOutItems = currentFirstVisible;


                currentItems = layout.getChildCount();
                totalItems = layout.getItemCount();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.noticias));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }
}
