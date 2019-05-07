package com.example.administrator.demo28;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private MapView mapView;

    private BaiduMap baiduMap;

    private LocationClient locationClient;

    private LocationClientOption locationClientOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        judgePermission();

        mapView=findViewById(R.id.mapView);
        //启动图层定位
        baiduMap.setMyLocationEnabled(true);



        //定位客户端对象
        //选项对象，设置定位参数
        //给定位客户端注册定位监听器
        //启动定位
        locationClient=new LocationClient(getApplicationContext());

        forgroundService();

        locationClientOption=new LocationClientOption();

        setLocationOptions();

        locationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                //获取定位信息
                String addrStr=bdLocation.getAddrStr();
                Log.i("lww",addrStr);
                double latitude=bdLocation.getLatitude();
                double longitude=bdLocation.getLongitude();
                float radius= bdLocation.getRadius();
                List<Poi> pois=bdLocation.getPoiList();
                for(Poi poi: pois){
                    Log.i("lww",poi.toString());
                }
                //在地图上显示定位信息
                MyLocationConfiguration configuration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true, BitmapDescriptorFactory.fromResource(R.mipmap.oil));
                baiduMap.setMyLocationConfigeration(configuration);
                //配置定位数据
                MyLocationData locationData=new MyLocationData.Builder().
                        latitude(latitude).longitude(longitude).build();
                //给地图控制器设置定位数据
                baiduMap.setMyLocationData(locationData);
                LatLng latLng=new LatLng(latitude,longitude);
                MapStatusUpdate mapStatusUpdate= MapStatusUpdateFactory.newLatLng(latLng);
                baiduMap.animateMapStatus(mapStatusUpdate);

            }
        });

        locationClient.start();

    }
    private void setLocationOptions(){
        //定位模式
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //打开GPS
        locationClientOption.setOpenGps(true);
        //设置需要提供位置信息
        locationClientOption.setIsNeedAddress(true);
        //设置获取POI
        locationClientOption.setIsNeedLocationPoiList(true);
        //设置坐标类型
        locationClientOption.setCoorType("gcj02");
        //设置扫描间隔时间
        locationClientOption.setScanSpan(1000);
        //应用定位参数
        locationClient.setLocOption(locationClientOption);


    }


    protected void judgePermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝

            // sd卡权限
            String[] SdCardPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, SdCardPermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, SdCardPermission, 100);
            }

            //手机状态权限
            String[] readPhoneStatePermission = {Manifest.permission.READ_PHONE_STATE};
            if (ContextCompat.checkSelfPermission(this, readPhoneStatePermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, readPhoneStatePermission, 200);
            }

            //定位权限
            String[] locationPermission = {Manifest.permission.ACCESS_FINE_LOCATION};
            if (ContextCompat.checkSelfPermission(this, locationPermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, locationPermission, 300);
            }

            String[] ACCESS_COARSE_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION};
            if (ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, ACCESS_COARSE_LOCATION, 400);
            }


            String[] READ_EXTERNAL_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, READ_EXTERNAL_STORAGE, 500);
            }

            String[] WRITE_EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, WRITE_EXTERNAL_STORAGE, 600);
            }

        }else{
            //doSdCardResult();
        }
        //LocationClient.reStart();
    }


    //前台定位服务
    private void forgroundService(){
        //开启前台定位服务：

        Notification.Builder builder = new Notification.Builder (Main2Activity.this.getApplicationContext());
        //获取一个Notification构造器

        Intent nfIntent = new Intent(Main2Activity.this.getApplicationContext(), Main2Activity.class);
        builder.setContentIntent(PendingIntent.getActivity(Main2Activity.this, 0, nfIntent, 0)) // 设置PendingIntent
                .setContentTitle("正在进行后台定位") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("后台定位通知") // 设置上下文内容
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
        Notification notification = null;
        notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        locationClient.enableLocInForeground(1001, notification);// 调起前台定位
    }
}
