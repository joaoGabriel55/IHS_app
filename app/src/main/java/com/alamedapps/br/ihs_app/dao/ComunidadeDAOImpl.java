package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.ComunidadeAdapter;
import com.alamedapps.br.ihs_app.adapters.ReligiosidadeAdapter;
import com.alamedapps.br.ihs_app.models.comunidade.Capela;
import com.alamedapps.br.ihs_app.models.religiosidade.Oracao;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class ComunidadeDAOImpl {

    private DatabaseReference mDatabaseReference;

    public void getAll(ChildEventListener mChildEventListener, final ComunidadeAdapter adapter) {
        mDatabaseReference = IHSUtil.getDatabase().getReference().child("comunidade");
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Capela capela = dataSnapshot.getValue(Capela.class);
                adapter.add(capela);
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
