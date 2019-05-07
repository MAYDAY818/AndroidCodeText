package com.example.administrator.demo28;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BaiduMapUtils {
    private static final String SHAI = "1E:D2:52:FD:3E:E8:37:1E:64:C1:02:D2:7B:E1:91:4B:3B:34:DB:0C";
    private static final String AK = "QzoBfn2iMlqKBalT3Dh0bacFxiLRenGX";
    private static final String PACKAGE = "cn.edu.hebtu.software.demo10";
    private static final String WEB_AK="LRfgsmIBEj0vSSGTSD6crB4mFItaFMGT";


    public static void getLocationInfo(final double lat, final double lng) {
        new Thread() {
            @Override
            public void run() {
                String url = "http://api.map.baidu.com/geocoder/v2/?ak=" + AK + "&location=" + lat + "," + lng + "&output=json&pois=0&mcode=" + SHAI + ";" + PACKAGE;
                try {
                    URLConnection conn = new URL(url).openConnection();
                    conn.connect();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream(), "utf-8"));
                    StringBuffer buffer = new StringBuffer();
                    String str;
                    while (null != (str = reader.readLine())) {
                        buffer.append(str);
                    }
                    reader.close();
                    JSONObject obj = new JSONObject(buffer.toString());
                    JSONObject result = obj.getJSONObject("result");
                    String address = result.getString("formatted_address");
                    JSONObject cityObj = result.getJSONObject("addressComponent");
                    String city = cityObj.getString("city");
                    Log.i("zq", "address: " + address);
                    Log.i("zq", "city: " + city);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void getLngAndLat(final String address) {
        final String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK + "&mcode=" + SHAI + ";" + PACKAGE;
        new Thread() {
            @Override
            public void run() {
                StringBuffer json = new StringBuffer();
                try {
                    URL urlObj = new URL(url);
                    URLConnection conn = urlObj.openConnection();
                    conn.connect();
                    InputStream in = conn.getInputStream();
                    InputStreamReader inReader = new InputStreamReader(in, "utf-8");
                    BufferedReader reader = new BufferedReader(inReader);
                    String inputLine = null;
                    while ((inputLine = reader.readLine()) != null) {
                        json.append(inputLine);
                    }
                    in.close();
                    JSONObject obj = null;//JSONObject.fromObject(json);
                    try {
                        obj = new JSONObject(json.toString());
                        if (obj.get("status").toString().equals("0")) {
                            double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
                            double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
                            Log.i("zq", address + "经度：" + lng + "纬度：" + lat);
                        } else {
                            System.out.println("匹配失败！");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                }
            }
        }.start();
    }

    public static void getRoutePlanning(LatLng origin,LatLng destination){
        double latitude1=origin.latitude;
        double longitude1=origin.longitude;

        double latitude2=destination.latitude;
        double longitude2=destination.longitude;
        final String url ="http://api.map.baidu.com/direction/v2/transit?origin="+latitude1+","+longitude1+"&destination="+latitude2+","+longitude2+"&ak="+WEB_AK;
        OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    String resultJson = call.execute().body().string();
                    JSONObject obj = new JSONObject(resultJson);
                    System.out.println(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
    }
}
