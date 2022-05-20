package com.cookandroid.capstone_front_android;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.location.Location1;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
public class Jmap extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());
    private View view;
    TextView text;
    private MapView sView;
    LocationManager lm;
    Location location;
    String provider;
    double longitude = 0;
    double latitude = 0;

    private GoogleMap gMap;

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


        // last known location 이 위치를 제대로 가져왔는지 확인
        if(location == null) { // 위치 확인 안 되는 경우
            Toast.makeText(getActivity(), "마지막 위치 가져오기 실패", Toast.LENGTH_SHORT).show();

            //location = new Location(Location1.NETWORK_PROVIDER);
            provider = "null";
            longitude = 127.187559;
            latitude = 37.224158;
            text.setText("위치정보 확인 불가");
        } else { // 위치 확인 성공한 경우
            provider = location.getProvider();
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            text.setText("위치정보 : " + provider + "\n" + "위도 : " + longitude + "\n" + "경도 : " + latitude);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gpsLocationListener);
        }

        sView = (MapView) view.findViewById(R.id.sMap);
        sView.onCreate(savedInstanceState);

        //Log.e(this.getClass().getName(), "맵 켜짐");

        sView.getMapAsync(this);

        //Log.e(this.getClass().getName(), "async 맵");

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
        this.gMap = googleMap;

        // 현재 위치 마커 표시
        /*MarkerOptions curLocMarker = new MarkerOptions();
        curLocMarker.position(new LatLng(latitude, longitude));
        curLocMarker.title("현위치");
        googleMap.addMarker(curLocMarker);
        googleMap.setOnMarkerClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocMarker.getPosition(), 15));*/

        // 현재 위치 마커 표시
        googleMap.addMarker(new MarkerOptions().
                position(new LatLng(latitude, longitude)).
                title("현위치").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        googleMap.setOnMarkerClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

        // 현재 위치 주변 정보 마커 표시
        //new Location1().setMarkerByPosition(googleMap, latitude, longitude);
        new Location1().setMarkerByPosition(googleMap, longitude, latitude);

        /*googleMap.addMarker(new MarkerOptions().
                position(new LatLng(37.2217035027, 127.1856397023)).
                title("현위치2").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));*/


    }
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    private void updateMarkers() {

    }




}