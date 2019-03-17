package com.alamedapps.br.ihs_app.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamedapps.br.ihs_app.MainActivity;
import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.dao.NewsDAOImpl;

public class FragmentNews extends Fragment {

    private View v;
    private RecyclerView recyclerView;

    private NewsAdapter newsAdapter;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() != null && ((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.noticias);

            floatingActionButton = ((MainActivity) getActivity()).findViewById(R.id.fab_to_top);
            floatingActionButton.setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_clero, container, false);

        setHasOptionsMenu(true);

        recyclerView = v.findViewById(R.id.recyclerviewClero);

        final LinearLayoutManager layout = new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, true
        );
        layout.setReverseLayout(true);
        layout.setStackFromEnd(true);
        recyclerView.setLayoutManager(layout);
        newsAdapter = new NewsAdapter(null, getContext(), getActivity(), recyclerView);

        NewsDAOImpl newsDAO = new NewsDAOImpl();
        newsDAO.getAll(newsAdapter);

        recyclerView.setAdapter(newsAdapter);

        scrollOutItems = layout.findFirstVisibleItemPosition();

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
                    AlphaAnimation animation1 = new AlphaAnimation(1, 0);
                    animation1.setDuration(500);
                    animation1.setStartOffset(1000);
                    animation1.setFillAfter(true);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout.scrollToPositionWithOffset(18, 0);
                            floatingActionButton.setVisibility(View.GONE);
                        }
                    });
                }

                if (layout.findFirstCompletelyVisibleItemPosition() == 15) {
                    floatingActionButton.setVisibility(View.GONE);
                }

                scrollOutItems = currentFirstVisible;


                currentItems = layout.getChildCount();
                totalItems = layout.getItemCount();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
//                    NewsDAOImpl newsDAO = new NewsDAOImpl();
//                    newsAdapter.getNewsList().clear();
//                    newsDAO.getAll(newsAdapter, limit+10);
//
//                    recyclerView.setAdapter(newsAdapter);
                }
            }
        });

//        newsAdapter.setILoadMoreItems(new ILoadMoreItems() {
//            @Override
//            public void onLoadMore() {
//                if (newsAdapter.getNewsList().size() <= limit) {
//                    newsAdapter.add(null);
//                    newsAdapter.notifyItemInserted(newsAdapter.getNewsList().size() - 1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            newsAdapter.getNewsList().remove(newsAdapter.getNewsList().size() - 1);
//                            newsAdapter.notifyItemRemoved(newsAdapter.getNewsList().size());
//
//                            int index = newsAdapter.getNewsList().size();
//                            int end = index + 10;
//                            for (int i = index; i < end; i++) {
//                                newsAdapter.getNewsList().add(newsAdapter.getNewsList().get(i));
//                            }
//                            newsAdapter.notifyDataSetChanged();
//                            newsAdapter.setLoaded();
//                        }
//                    }, 300);
//                } else {
//                    Toast.makeText(getContext(), "Não há mais notícias", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        return v;
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
