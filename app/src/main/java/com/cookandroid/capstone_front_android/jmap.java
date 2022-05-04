package com.cookandroid.capstone_front_android;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class jmap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private View view;
    TextView text;

    private MapView sView;

    LocationManager lm;
    Location location;
    String provider;
    double longitude = 0;
    double latitude = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map, container, false);
        text = view.findViewById(R.id.txt);

        lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "GPS OFF상태", Toast.LENGTH_SHORT).show();
            return view;
        }

        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null){
            location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        provider = location.getProvider();
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        text.setText("위치정보 : " + provider + "\n" + "위도 : " + longitude + "\n" + "경도 : " + latitude);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gpsLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gpsLocationListener);

        sView = (MapView) view.findViewById(R.id.s_map);
        sView.onCreate(savedInstanceState);
        sView.getMapAsync(this);

        return view;
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            provider = location.getProvider();
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            text.setText("위치정보 : " + provider + "   " + "위도 : " + latitude + "\n" + "경도 : " + longitude);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        sView.onStart();
    }

    @Override
    public void onStop () {
        super.onStop();
        sView.onStop();

    }

    @Override
    public void onSaveInstanceState (@Nullable Bundle outState){
        super.onSaveInstanceState(outState);
        sView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        sView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //마커찍기(위도,경도)
        //LatLng solnae = new LatLng(37.145995272094034, 127.06707518461991); // 위치지정.
        LatLng solnae = new LatLng(latitude, longitude);
        //text.setText("위치정보 : " + provider + "   " + "위도 : " + latitude + "\n" + "경도 : " + longitude);
        //마커 옵션.
        MarkerOptions marker = new MarkerOptions();
        //marker.title("역");
        //marker.snippet("오산역");
        marker.position(solnae); //마커 위치.

        //맵에 마커표시, 인포윈도우 보여줌.
        googleMap.addMarker(marker);

        //인포윈도우 클릭
        googleMap.setOnMarkerClickListener(this);

        //맵뷰 카메라위치, 줌 설정
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(solnae, 16));
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}
