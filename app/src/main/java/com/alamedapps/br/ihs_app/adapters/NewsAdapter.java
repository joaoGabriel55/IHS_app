package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.viewholders.NewsViewHolder;
import com.alamedapps.br.ihs_app.fragment.news.ILoadMoreItems;
import com.alamedapps.br.ihs_app.fragment.news.NewsSelectedActivity;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

import java.util.ArrayList;
import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder {

    public final ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar_items);
    }
}


public class NewsAdapter extends RecyclerView.Adapter {

    public static final String TITLE_NEWS_KEY = "title";
    public static final String CONTENT_NEWS_KEY = "content";
    public static final String IMAGE_NAME_NEWS_KEY = "image";
    public static final String DATE_KEY = "registerDate";

    private List<News> newsList;
    private Context context;
    private FragmentActivity fragmentActivity;

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private ILoadMoreItems iLoadMoreItems;
    private boolean isLoading;
    private int visibleLimit = 5;
    private int lastVisibleItem, totalItemCount;


    public NewsAdapter(List<News> newsList, Context context, FragmentActivity fragmentActivity, RecyclerView recyclerView) {
        if (newsList != null) {
            this.newsList = newsList;
        } else {
            this.newsList = new ArrayList<>();
        }
        this.fragmentActivity = fragmentActivity;
        this.context = context;

//        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleLimit)) {
//                    if (iLoadMoreItems != null) {
//                        iLoadMoreItems.onLoadMore();
//                    }
//                }
//                isLoading = true;
//            }
//        });
    }

    @Override
    public int getItemViewType(int position) {
        return newsList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setILoadMoreItems(ILoadMoreItems iLoadMoreItems) {
        this.iLoadMoreItems = iLoadMoreItems;
    }

    public void add(News news) {
        newsList.add(news);
        notifyItemInserted(newsList.size() + 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
            return new NewsViewHolder(v);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View v = LayoutInflater.from(context).inflate(R.layout.items_loading, parent, false);
            return new LoadingViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            final NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            final News news = newsList.get(position);

            newsViewHolder.title.setText(news.getTitle());
            newsViewHolder.dateNew.setText(news.getRegisterDate().toString());
            IHSUtil.handleImage(newsViewHolder.image, "news/" + news.getImage());


            newsViewHolder.newsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putString(TITLE_NEWS_KEY, news.getTitle());
                    b.putString(CONTENT_NEWS_KEY, news.getContent());
                    b.putString(IMAGE_NAME_NEWS_KEY, news.getImage());
                    b.putString(DATE_KEY, news.getRegisterDate().toString());
                    Intent intent = new Intent(fragmentActivity, NewsSelectedActivity.class);
                    intent.putExtras(b);
                    fragmentActivity.startActivity(intent);
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    public List<News> getNewsList() {
        return newsList;
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void setLoaded() {
        isLoading = false;
    }
}
