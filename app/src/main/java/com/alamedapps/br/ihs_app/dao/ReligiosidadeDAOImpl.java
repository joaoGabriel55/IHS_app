package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.ReligiosidadeAdapter;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class ReligiosidadeDAOImpl {

    private DatabaseReference mDatabaseReference;

    public void getAll(ChildEventListener mChildEventListener, final ReligiosidadeAdapter adapter) {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child("religiosidade");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Oracao oracao = dataSnapshot.getValue(Oracao.class);
                adapter.add(oracao);
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
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }
}
