package com.alamedapps.br.ihs_app.dao;

import com.alamedapps.br.ihs_app.adapters.CleroAdapter;
import com.alamedapps.br.ihs_app.adapters.GalleryAdapter;
import com.alamedapps.br.ihs_app.models.Clero;
import com.alamedapps.br.ihs_app.models.gallery.Gallery;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class GalleryDAOImpl {

    public void getAll(ChildEventListener mChildEventListener, DatabaseReference databaseReference, final GalleryAdapter adapter) {
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Gallery clero = dataSnapshot.getValue(Gallery.class);
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
