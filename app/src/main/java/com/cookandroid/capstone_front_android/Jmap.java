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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cookandroid.capstone_front_android.location.model.LocationAPI;
import com.cookandroid.capstone_front_android.location.model.LocationListResponse;
import com.cookandroid.capstone_front_android.location.model.LocationResponse;
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
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private Button refreshLocationButton;                                   // 위치정보 갱신 버튼
    private Button mjButton;                                                // 학교 위치로 이동
    private Marker curLocMarker;                                            // 현 위치 표시 마커
    private GoogleMap gMap;                                                 // 구글맵!
    private LocationAPI locationAPI;                                        // 서버 통신을 위한 레트로핏 클라이언트
    private Map<LatLng, LocationResponse> locationMap = new HashMap<>();    // 위치, 위치정보 쌍
    private TextView locInfo;                                               // 문화 생활 상세 정보 텍스트뷰
    private ImageView locImage;                                             // 문화 생활 상세 정보 이미지뷰
    private SlidingUpPanelLayout mapLayout;                                 // 맵 전체 레이아웃

    // 테스트용으로 임시 좌표
    public static final double SEOUL_CITY_HALL_X = 126.9779;
    public static final double SEOUL_CITY_HALL_Y = 37.5663;
    public static final double SUWON_HWASEONG_X = 127.0119;
    public static final double SUWON_HWASEONG_Y = 37.2871;
    public static final double YONGIN_CITY_HALL_X = 127.1779;
    public static final double YONGIN_CITY_HALL_Y = 37.2410;
    public static final double MJU_NS_X = 127.187559;
    public static final double MJU_NS_Y = 37.224158;

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

        refreshLocationButton = view.findViewById(R.id.getLocationButton);
        mjButton    = view.findViewById(R.id.myongji_button);
        locInfo     = view.findViewById(R.id.location_info);
        locImage    = view.findViewById(R.id.location_image);
        mapLayout   = view.findViewById(R.id.map);

        // 레트로핏 클라이언트 가져오기
        locationAPI = RetrofitClient.getClient(LocationAPI.class);

        // last known location 이 위치를 제대로 가져왔는지 확인
        if(location == null) { // 위치 확인 안 되는 경우
            Toast.makeText(getActivity(), "마지막 위치 가져오기 실패", Toast.LENGTH_SHORT).show();

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
        this.gMap = googleMap;

        // 확대 축소 버튼 표시
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // 현재 위치 마커 표시
        curLocMarker = googleMap.addMarker(new MarkerOptions().
                position(new LatLng(latitude, longitude)).
                title("현위치").
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        googleMap.setOnMarkerClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

        // 현재 위치 주변 정보 마커 표시
        updateLocations(googleMap);

        // 위치 정보 갱신 버튼 클릭시
        refreshLocationButton.setOnClickListener(v -> updateLocations(googleMap));

        // 학교로 순간이동 버튼
        mjButton.setOnClickListener(v -> {
            curLocMarker.setPosition(new LatLng(37.224158, 127.187559));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(curLocMarker.getPosition()));
        });

        // 정보창 클릭시 상세정보 표시
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {

                // 선택한 마커가 현재 위치 마커면 통과
                if(marker.equals(curLocMarker)) {
                    Log.e("선택한 마커에 문화 생활이 없습니다", "선택한 마커가 현위치 입니다.(" + marker.getPosition().toString() + ")");
                    return;
                }

                // 마커 찾아서 정보 보여주기
                LocationResponse l = locationMap.get(marker.getPosition());
                if(l == null) {
                    Log.e("정보 받아오기 에러 발생", "" + marker.getPosition().toString());
                    Toast.makeText(getActivity(), "" + marker.getPosition().toString(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    locInfo.setText("문화생활:" + l.getTitle() + "\n" +
                            "주소: " + l.getAddress() + "\n" +
                            "지역: " + l.getAreaName() + "\n" +
                            "종류: " + l.getContentName());
                    if(l.getFirstImage() != null) Glide.with(Jmap.this).load(l.getFirstImage()).into(locImage);
                    mapLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                }
            }

        });


    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    // 지도상의 위치로 현재 위치 업데이트하고 주변 문화 생활 정보 찾기
    private void updateLocations(GoogleMap googleMap) {

        // 현 위치 마커 및 현 위치 업데이트
        curLocMarker.setPosition(googleMap.getCameraPosition().target);
        longitude = googleMap.getCameraPosition().target.longitude;
        latitude = googleMap.getCameraPosition().target.latitude;
        text.setText(text.getText() + "\n지도위치: " + longitude + ", " + latitude);

        Log.e("tag", "위치:" + longitude + "," + latitude);

        locationAPI.findAllByPostition(longitude, latitude).enqueue(new Callback<LocationListResponse>() {
            @Override
            public void onResponse(Call<LocationListResponse> call, Response<LocationListResponse> response) {

                LocationListResponse r = response.body();
                if(r == null) {
                    Log.e("tag", "정보가 없습니다.");
                    Toast.makeText(getActivity(), "현재 위치에 문화 생활 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 서버로부터 위치 리스트 받기
                List<LocationResponse> l = r.getLocations();

                Log.e("tag", "정보 가져옴");

                // 가져온 위치를 지도(구글맵)와 맵(Map<LatLng, LocationResponse)에 추가
                for(LocationResponse i : l) {

                    // 이미 추가됐는지 확인
                    if(locationMap.containsKey((new LatLng(i.getMapY(), i.getMapX()))) == true) {
                        Log.e("tag", "중복 위치: " + i.getTitle() + ", 위치: " + (new LatLng(i.getMapY(), i.getMapX())).toString());
                        continue;
                    } else {
                        // 추가 안된경우 (위치, LocationResponse) 쌍 추가
                        locationMap.put((new LatLng(i.getMapY(), i.getMapX())), i);
                    }

                    Log.e("tag", "정보 추가: " + i.getTitle() + ", 위치: " + (new LatLng(i.getMapY(), i.getMapX())).toString());

                    // 마커 추가
                    googleMap.addMarker(new MarkerOptions().
                            position(new LatLng(i.getMapY(), i.getMapX())).
                            title(i.getTitle()).
                            snippet(i.getAddress()).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }

                Log.e("tag", "정보 추가완료");

            }

            @Override
            public void onFailure(Call<LocationListResponse> call, Throwable t) {

                Log.e("정보 받아오기 에러 발생", t.getMessage());
                Toast.makeText(getActivity(), "정보 받아오기 에러 발생", Toast.LENGTH_SHORT).show();

            }
        });
    }
}