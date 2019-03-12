package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.adapters.NewsAdapter;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.models.news.News;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class NewsDAOImpl {

    private DatabaseReference mDatabaseReference;

    public void getAll(final NewsAdapter adapter) {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child(IHSUtil.DATABASE + "news");
        Query query = mDatabaseReference.orderByChild("registerDate");
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                News grupo = dataSnapshot.getValue(News.class);
                adapter.add(grupo);
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
        };
        query.addChildEventListener(mChildEventListener);
    }
}
