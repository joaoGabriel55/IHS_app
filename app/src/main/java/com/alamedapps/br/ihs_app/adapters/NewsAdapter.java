package com.alamedapps.br.ihs_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.alamedapps.br.ihs_app.adapters.viewholders.NewsViewHolder;
import com.alamedapps.br.ihs_app.fragment.news.NewsSelectedActivity;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter {

    public static final String TITLE_NEWS_KEY = "title";
    public static final String CONTENT_NEWS_KEY = "content";
    public static final String IMAGE_NAME_NEWS_KEY = "image";
    public static final String DATE_KEY = "registerDate";

    private List<News> newsList;
    private Context context;
    private FragmentActivity fragmentActivity;

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

    public void add(News news) {
        newsList.add(news);
        notifyItemInserted(newsList.size() + 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        final News news = newsList.get(position);

        newsViewHolder.title.setText(news.getTitle());

        newsViewHolder.dateNew.setText(news.getRegisterDate());

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
    }

    public List<News> getNewsList() {
        return newsList;
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }
}
