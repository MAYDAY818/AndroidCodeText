package activitytext.example.com.demo24;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化okhttpclient
        okHttpClient=new OkHttpClient();
        //1.实现不带请求参数的同步的get
        simpleGetWithNotParam();
        //2.实现不带请求参数的异步的get
        //3.带参数的get
        //4.带参数的post
    }
    //1.实现不带请求参数的同步的get
    private void simpleGetWithNotParam(){
        new Thread(){
            @Override
            public void run() {
                //1.创建okhttpclient
                //2.创建request
                Request.Builder builder=new Request.Builder();
                builder.url("http://www.gcores.com");
                Request request=builder.build();
                //3.创建call
                Call call=okHttpClient.newCall(request);
                //4.提交请求并返回响应
                try {
                    Response response=call.execute();
                    //得到具体的响应数据
                    String result=response.body().toString();
                    Log.i(TAG, "simpleGetWithNotParam: "+result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    //2.实现不带请求参数的异步的get
    private void simpleAsyncGetWithNotParam(){
        //1.创建okhttpclient
        //2.创建request
        Request request=new Request.Builder().url("http://www.gcores.com").build();
        //3.提交call
        Call call=okHttpClient.newCall(request);
        //4.提交请求并返回响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败时调用的方法

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功提交请求并获得响应会调用的方法
                Log.i(TAG, "onResponse: "+response.body().toString());
            }
        });
    }
    //3.带参数的get
    private void simpleGetWithParam(){
        new Thread(){
            @Override
            public void run() {
                Request request=new Request.Builder().url("http://").build();
                Call call=okHttpClient.newCall(request);
                try {
                    Response response=call.execute();
                    Log.i(TAG, "run: "+response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //4.带参数的post


    private static final String TAG = "MainActivity";
}
