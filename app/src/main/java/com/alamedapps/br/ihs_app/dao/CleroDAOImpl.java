package com.alamedapps.br.ihs_app.dao;

import android.content.Context;

import com.alamedapps.br.ihs_app.adapters.CleroAdapter;
import com.alamedapps.br.ihs_app.adapters.EventoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CleroDAOImpl {

    public void getAll(ChildEventListener mChildEventListener, DatabaseReference databaseReference, final CleroAdapter adapter) {
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Clero clero = dataSnapshot.getValue(Clero.class);
                adapter.add(clero);
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
