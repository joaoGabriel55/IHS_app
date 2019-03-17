package com.alamedapps.br.ihs_app.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class NewsDAOImpl {

    private DatabaseReference mDatabaseReference;
    private Query query;

    public void getAll(final NewsAdapter adapter) {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");
        query = mDatabaseReference.orderByChild("registerDate");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                News news = dataSnapshot.getValue(News.class);
                adapter.add(news);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * This method is responsible for remove old news (2 months past).
     */
    private void deleteOldNews() {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");
        query = mDatabaseReference.orderByChild("registerDate");
        query.removeEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
