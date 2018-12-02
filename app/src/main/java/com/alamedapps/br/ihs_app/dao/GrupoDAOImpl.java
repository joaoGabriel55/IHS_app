package com.alamedapps.br.ihs_app.dao;

import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;

import com.alamedapps.br.ihs_app.adapters.GrupoAdapter;
import com.alamedapps.br.ihs_app.adapters.SecretariaAdapter;
import com.alamedapps.br.ihs_app.models.Secretaria;
import com.alamedapps.br.ihs_app.models.igrejaemacao.Grupo;
import com.alamedapps.br.ihs_app.utils.IHSUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAOImpl {

    private DatabaseReference  mDatabaseReference = IHSUtil.getDatabase().getReference().child("grupo");

    public void getAll(final GrupoAdapter adapter) {

        Query query = mDatabaseReference.orderByChild("categoriaGrupo");
        ChildEventListener mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Grupo grupo = dataSnapshot.getValue(Grupo.class);
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

    private void firebaseSearchGrupoByName(String name){
        Query query = mDatabaseReference.orderByChild("categoriaGrupo").startAt(name).endAt(name + "\uf8ff");
    }
}
