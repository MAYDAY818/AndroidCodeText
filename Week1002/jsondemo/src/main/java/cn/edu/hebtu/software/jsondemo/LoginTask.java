package cn.edu.hebtu.software.jsondemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://10.7.89.251:8080/BookShop/LoginServlet?username=zhangsan&password=12355");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("contentType","UTF-8");
            InputStream is = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String res = reader.readLine();
            //解析Json字符串
            try {
                JSONObject object = new JSONObject(res);
                User user = new User();
                user.setUsername(object.getString("username"));
                user.setPassword(object.getString("password"));
                Log.e("LoginTask",user.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

