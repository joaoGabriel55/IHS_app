package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.EventoAdapter;
import com.alamedapps.br.ihs_app.models.Evento;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class EventoDAOImpl {

    public void getAllEventos(ChildEventListener mChildEventListener, Evento evento, DatabaseReference databaseReference, final EventoAdapter adapter) {
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Evento evento = dataSnapshot.getValue(Evento.class);
                adapter.add(evento);
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
