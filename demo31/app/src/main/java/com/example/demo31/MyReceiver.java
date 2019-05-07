package com.example.demo31;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String actionStr=intent.getAction();
        switch (actionStr) {
            case JPushInterface.ACTION_NOTIFICATION_OPENED:
                String title=bundle.getString("JPushInterface.EXTRA_NOTIFICATION_TITLE");

                break;
            case JPushInterface.ACTION_MESSAGE_RECEIVED:

                break;
            case  JPushInterface.ACTION_NOTIFICATION_RECEIVED:

                break;
        }
    }
}
