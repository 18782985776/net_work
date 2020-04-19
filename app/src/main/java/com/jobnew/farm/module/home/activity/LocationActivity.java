package com.jobnew.farm.module.home.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.jobnew.farm.R;
import com.jobnew.farm.base.activity.BaseActivity;
import com.jobnew.farm.constants.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class LocationActivity extends BaseActivity {

    MapView mapView;
    AMap aMap=null;
    String[] permissions={Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
    double farmLongitude;
    double farmLatitude;
    String farmName;
    AMapLocationClient mAMapLocationClient=null;
    AMapLocationListener mAMapLocationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            CameraUpdate cameraUpdate = CameraUpdateFactory
                    .newCameraPosition(new CameraPosition(new LatLng(30.554515,
                            104.064623), 15, 0, 30));
            aMap.moveCamera(cameraUpdate);
            MarkerOptions markerOptions=new MarkerOptions();
            markerOptions.position(new LatLng(30.554515,104.064623));
            // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.farm_icon_address2));
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            // 设置Marker是否可以被拖拽，
            markerOptions.draggable(false);
            // 设置Marker的可见性
            markerOptions.visible(true);
            //将Marker添加到地图上去
            Marker marker = aMap.addMarker(markerOptions);
            marker.showInfoWindow();
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        farmLongitude=intent.getDoubleExtra("farmLongitude", Constant.LONGITUDE);
        farmLatitude= intent.getDoubleExtra("farmLatitude",Constant.LATITUDE);
        farmName=intent.getStringExtra("farmName");
        mapView= (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mAMapLocationClient=new AMapLocationClient(getApplicationContext());
        mAMapLocationClient.setLocationOption(new AMapLocationClientOption().setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy).setOnceLocation(true));
        //设置定位回调监听
        mAMapLocationClient.setLocationListener(mAMapLocationListener);
        if(EasyPermissions.hasPermissions(this,permissions)){
            initMap();
        }else {
            EasyPermissions.requestPermissions(this,"必要权限",663,permissions);
        }
        if(farmName!=null){
            setTitle(farmName,true);
        }else {
            setTitle("花果山农家乐",true);
        }

    }

    private void initMap() {
        if(aMap==null){
            aMap=mapView.getMap();
        }else {
            aMap=mapView.getMap();
        }
        aMap.setMapLanguage(AMap.CHINESE);
        // initSearch();
//启动定位
        mAMapLocationClient.startLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        initMap();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "请在设置里面开启相应权限，若无相应权限会影响使用")
                    .setPositiveButton("确定")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(123)
                    .build()
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mapView!=null){
            mapView.onDestroy();
            mapView=null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mapView!=null){
            mapView.onDestroy();
            mapView=null;
        }
    }
}
