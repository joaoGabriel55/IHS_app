package com.alamedapps.br.ihs_app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alamedapps.br.ihs_app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    private View v;
    private MapView mapView;
    private GoogleMap googleMap;
    private static final double LATITUDE = -5.825818;
    private static final double LONGITUDE = -35.234974;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_map, container, false);

        setHasOptionsMenu(true);

        mapView = v.findViewById(R.id.map_igreja);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(getString(R.string.como_chegar));
    }

    @Override
    public void onMapReady(GoogleMap gMap) {
        MapsInitializer.initialize(getContext());
        googleMap = gMap;

        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition liberty = CameraPosition.builder().target(new LatLng(LATITUDE, LONGITUDE)).zoom(18).bearing(0).build();

        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));
        gMap.addMarker(new MarkerOptions().position(new LatLng(LATITUDE, LONGITUDE)).title(getString(R.string.label_map)));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(LATITUDE, LONGITUDE), 18));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
    }

}