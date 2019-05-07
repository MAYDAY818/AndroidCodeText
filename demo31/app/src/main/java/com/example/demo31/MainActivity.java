package com.example.demo31;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
