package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.EventoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.models.Evento;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class SecretariaDAOImpl {

    public void getAllEventos(ChildEventListener mChildEventListener, Secretaria secretaria, DatabaseReference databaseReference, final SecretariaAdapter adapter) {
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Secretaria secretaria = dataSnapshot.getValue(Secretaria.class);
                adapter.add(secretaria);
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
