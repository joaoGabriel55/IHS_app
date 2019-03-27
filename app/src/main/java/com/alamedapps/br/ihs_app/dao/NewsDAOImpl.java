package com.alamedapps.br.ihs_app.dao;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;

import static android.view.View.GONE;
import static com.alamedapps.br.ihs_app.utils.FormatterUtils.isPastMouth;

public class NewsDAOImpl {

    private DatabaseReference mDatabaseReference;

    public void getAll(final NewsAdapter adapter,
                       final LinearLayout layoutLoading,
                       final RecyclerView recyclerView,
                       final ProgressBar progressBar,
                       final TextView tvStatusNews,
                       final FloatingActionButton fab) {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");
        Query query = mDatabaseReference.orderByChild("registerDate");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    progressBar.setVisibility(View.VISIBLE);
                    String key = snapshot.getKey();
                    News news = snapshot.getValue(News.class);
                    try {
                        if (isPastMouth(news.getRegisterDate()))
                            mDatabaseReference.child(key).removeValue();
                        else
                            adapter.add(news);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (adapter.getNewsList() == null || adapter.getNewsList().size() <= 0) {
                    layoutLoading.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(GONE);
                    progressBar.setVisibility(GONE);
                    tvStatusNews.setText("Não há notícias novas cadastradas");
                } else {
                    if (adapter.getNewsList().size() <= 5)
                        fab.setVisibility(GONE);

                    layoutLoading.setVisibility(GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method is responsible for remove old news (2 months past).
     */
    private void deleteOldNews() {
    }

}
