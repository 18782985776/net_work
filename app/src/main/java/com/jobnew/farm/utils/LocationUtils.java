package com.jobnew.farm.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.google.gson.Gson;
import com.jobnew.farm.R;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by wangwenlang on 2017/8/5.
 * title:
 * note:
 */

public class LocationUtils implements EasyPermissions.PermissionCallbacks {
    private static LocationUtils instance;
    private  Activity aty;
    private LatLng latlng;//LatLng
    int locationNum=0;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private AMapLocationListener mLocationListener=null;
    String[] permissions={Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE};
    private void setActivity(Activity aty){
        this.aty=aty;
    }
    public static LocationUtils getInstance(Activity aty){
        if(instance==null){
            instance=new LocationUtils();
        }
        instance.setActivity(aty);
        instance.initMapClient();
        return instance;
    }

    private void initMapClient() {
        mLocationClient=new AMapLocationClient(aty);
        mLocationListener= new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        locationNum++;
                        //解析定位结果
                        Log.d("定位结果", "onLocationChanged: "+aMapLocation.getLongitude()+"经度");
                        Log.d("定位结果", "onLocationChanged: "+aMapLocation.getLatitude()+"纬度");
                        latlng=new LatLng(aMapLocation.getLongitude(),aMapLocation.getLatitude());
                        if(locationNum==1){
                            String latlngString=new Gson().toJson(latlng);
                            SPUtils.put("locationData",latlng);
                        }
                    }
                }
            }
        };
        getperssion();
    }
    /**开启定位***/
    private void startLocation() {
        mLocationClient.startLocation();
    }

    private void getperssion() {
        if(EasyPermissions.hasPermissions(aty,permissions)){
            startLocation();
        }else {
            EasyPermissions.requestPermissions(aty,"必要权限",123321,permissions);
        }
    }


    public LatLng getLocationLatLng() {
        if(latlng!=null){
            return latlng;
        }else {
            String locationData1= SPUtils.get("locationData", "");
            Gson gson=new Gson();
            latlng= gson.fromJson(locationData1,LatLng.class);
        }
        return  latlng;
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startLocation();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(aty, "请在设置里面开启相应权限，若无相应权限会影响使用")
                    .setPositiveButton("确定")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(123321)
                    .build()
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, aty);
    }
}
