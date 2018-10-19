package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.adapters.TaxasAdapter;
import com.alamedapps.br.ihs_app.models.TaxasEmolumentos;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class TaxasDAOImpl {

    public void getAll(ChildEventListener mChildEventListener, DatabaseReference databaseReference, final TaxasAdapter adapter) {
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TaxasEmolumentos taxasEmolumentos = dataSnapshot.getValue(TaxasEmolumentos.class);
                adapter.add(taxasEmolumentos);
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
        databaseReference.addChildEventListener(mChildEventListener);
    }
}
