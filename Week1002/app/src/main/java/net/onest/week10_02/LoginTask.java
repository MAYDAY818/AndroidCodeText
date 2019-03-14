package net.onest.week10_02;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            String path = "http://10.7.89.251:8080/ch03demo2/LoginServlet?name=张三&password=123";
//            String path = "http://10.7.89.45:8080/LoginTest/LoginServlet?username=张三&password=123";
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //发送get请求
            connection.setRequestMethod("GET");
            connection.setRequestProperty("contentType","utf-8");

            //发送post请求
//            connection.setRequestMethod("POST");
//            OutputStream os = connection.getOutputStream();
//            OutputStreamWriter opw = new OutputStreamWriter(os);
//            BufferedWriter writer = new BufferedWriter(opw);
//            writer.write("username=张三&password=123456");
//            writer.flush();
//            writer.close();
            //请求
            connection.connect();
            //获得响应
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Log.e("LoginTask",bufferedReader.readLine());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
