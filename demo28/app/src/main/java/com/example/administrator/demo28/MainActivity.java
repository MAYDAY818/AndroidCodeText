package com.example.administrator.demo28;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private boolean isFirstLocate=true;
    private BaiduMap baiduMap;
    private UiSettings uiSettings;
    private LocationClient mLocationClient;
    private TextView positionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());



        //http://api.map.baidu.com/geocoder/v2/?ak=QzoBfn2iMlqKBalT3Dh0bacFxiLRenGX&location=39.915,116.403&output=json&pois=0&mcode=1E:D2:52:FD:3E:E8:37:1E:64:C1:02:D2:7B:E1:91:4B:3B:34:DB:0C;cn.edu.hebtu.software.demo10



        //初始化地图服务
        //1.setContentView之前，2.上下文参数不能写this，需getApplication
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        positionText=findViewById(R.id.position_text_view);





        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);

        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }
        if(!permissionList.isEmpty()){
            String []permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else{
            requestLocation();
        }











        mapView = findViewById(R.id.mapView);

        //百度地图控制器
        baiduMap=mapView.getMap();
        //得到UI设置器
        uiSettings=baiduMap.getUiSettings();


        baiduMap.setMyLocationEnabled(true);

        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        baiduMap.setTrafficEnabled(true);
        //baiduMap.setBaiduHeatMapEnabled(true);
        mapView.setLogoPosition(LogoPosition.logoPostionRightBottom);
        //不显示logo
        //mapView.removeViewAt(1);

        //设置指南针，地图方向是南北方时，不显示
//        uiSettings.setCompassEnabled(false);
//        baiduMap.setCompassEnable(true);
//        baiduMap.setCompassPosition(new Point(800,700));
//        baiduMap.setCompassIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.znz));



        //设置是否显示比例尺
        //mapView.showScaleControl(false);
        //比例尺位置(单独设置无效果，需配合百度控制器)
//        mapView.setScaleControlPosition(new Point(500,400));
//        //获取当前比例尺规格
//        int level=mapView.getMapLevel();
//        Log.i("zq",level+"");

        //给百度控制器注册监听器，以响应

        baiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //设置显示比例尺
                mapView.showScaleControl(true);
                //位置
                //mapView.setScaleControlPosition(new Point(500,400));
                //设置当前默认比例尺大小
                MapStatusUpdate msu= MapStatusUpdateFactory.zoomTo(15);
                baiduMap.setMapStatus(msu);
                baiduMap.setMaxAndMinZoomLevel(15,20);
            }
        });

        //设置地图俯视效果
        //创建mapstatus对象
        //mapstatudupdate
        //应用状态变化

//        MapStatus status=new MapStatus.Builder().overlook(-45).build();
//
//        MapStatusUpdate statusUpdate=MapStatusUpdateFactory.newMapStatus(status);
//        baiduMap.setMapStatus(statusUpdate);

    }







    //显示Marker覆盖物
    private void showMarkerOption(){
        //1.经纬度
        final LatLng latLng=new LatLng(39.91544675736359,116.40384939145729);

        //2.创建覆盖物
        BitmapDescriptor icon= BitmapDescriptorFactory.fromResource(R.mipmap.loc);
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).icon(icon).perspective(true).zIndex(6);

        //3.覆盖物添加到地图上
        Overlay overlay= baiduMap.addOverlay(markerOptions);
        //4.给百度控制器注册标注覆盖物事件监听器
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng position=marker.getPosition();

                return false;
            }
        });

    }



    //显示几何图形覆盖物

    private void showPolyGonOption(){
        //1.创建坐标点对象


        List<LatLng> points=new ArrayList<>();
        LatLng pt1=new LatLng(40.015,116.404);
        LatLng pt2=new LatLng(39.915,116.304);
        LatLng pt3=new LatLng(39.875,116.204);
        LatLng pt4=new LatLng(39.845,116.504);
        LatLng pt5=new LatLng(39.925,116.604);
        points.add(pt1);
        points.add(pt1);
        points.add(pt1);
        points.add(pt1);
        points.add(pt1);


        //2.创建多边形
        OverlayOptions polygonOption =new PolygonOptions().points(points).
                fillColor(0xaaff0000).stroke(new Stroke(6, Color.GREEN)).zIndex(4);
        //3.添加
        baiduMap.addOverlay(polygonOption);
    }





    //显示文字覆盖物
    private void showTextOption(){
        LatLng latLng=new LatLng(39.915,116.403);
        OverlayOptions overlayOptions=new TextOptions().text("dfkjg").
                position(latLng).fontSize(20).fontColor(0xffff00ff).rotate(-30).zIndex(5);
        baiduMap.addOverlay(overlayOptions);
    }
    //图层覆盖物
    private void showGroundOverlay(){
        //1.定义显示位置
        LatLng northEast =new LatLng(39.935,116.329);
        LatLng sounthWest=new LatLng(39.725,116.129);
        LatLngBounds bounds=new LatLngBounds.Builder().include(northEast).include(sounthWest).build();

        //2.定义图层覆盖物
        BitmapDescriptor img=BitmapDescriptorFactory.fromResource(R.mipmap.oil);
        GroundOverlayOptions groundOverlayOptions=new GroundOverlayOptions().
                positionFromBounds(bounds).image(img).transparency(0.5f);
        //3.添加覆盖物
        baiduMap.addOverlay(groundOverlayOptions);
    }


    //弹出窗
    private void showInforWindow(){
        //1.得到弹出框View
        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.infolayout,null);
        Button button=view.findViewById(R.id.btnGo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"被点击",Toast.LENGTH_SHORT).show();

            }
        });

        //2.定义显示位置
        LatLng latLng=new LatLng(39.915,116.404);
        //3.创建弹出窗覆盖物
        InfoWindow infoWindow=new InfoWindow(view,latLng,0);
        //4.显示弹出窗覆盖物
        baiduMap.showInfoWindow(infoWindow);

    }


    private void showInforWindow2(){
        //1.得到弹出框View的BitmapDescript

        LayoutInflater inflater=LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.infolayout,null);
        Button button=view.findViewById(R.id.btnGo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"被点击",Toast.LENGTH_SHORT).show();

            }
        });
        BitmapDescriptor descriptor=BitmapDescriptorFactory.fromView(view);
        //2.定义显示位置
        LatLng latLng=new LatLng(39.915,116.404);
        //3.创建弹出窗覆盖物
        InfoWindow.OnInfoWindowClickListener infoWindowClickListener
                = new InfoWindow.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick() {
                // 隐藏InfoWindow
                baiduMap.hideInfoWindow();
            }
        };
        InfoWindow infoWindow=new InfoWindow(descriptor,latLng,0,infoWindowClickListener);
        //4.显示弹出窗覆盖物
        baiduMap.showInfoWindow(infoWindow);

    }
















    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }











    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();



        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }



















    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
            StringBuilder currentPosition =new StringBuilder();
            currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经度：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
            currentPosition.append("定位方式：");
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");

            }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");

            }
            positionText.setText(currentPosition);


        }
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }

        MyLocationConfiguration configuration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,true, BitmapDescriptorFactory.fromResource(R.mipmap.oil));
        baiduMap.setMyLocationConfigeration(configuration);



        MyLocationData.Builder locationBulider=new MyLocationData.Builder();
        locationBulider.latitude(location.getLatitude());
        locationBulider.longitude(location.getLongitude());
        MyLocationData locationData=locationBulider.build();
        baiduMap.setMyLocationData(locationData);
    }
}
