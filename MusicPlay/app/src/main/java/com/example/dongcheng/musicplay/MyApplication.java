package com.example.dongcheng.musicplay;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;

public class MyApplication extends Application {
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication myApplication = (MyApplication) context.getApplicationContext();
        return myApplication.proxy == null ? (myApplication.proxy = myApplication.newProxy()) : myApplication.proxy;
    }
    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(Utils.getVideoCacheDir(this))//缓存地址
                .maxCacheSize(200 * 1024 * 1024)
                .fileNameGenerator(new MyFileNameGenerator()).build();
    }
    public class MyFileNameGenerator implements FileNameGenerator {//缓存的命名规则
        public String generate(String url) {
            Uri uri = Uri.parse(url);
            String audioId = uri.getQueryParameter("guid");
            return audioId + ".mp3";
        }
    }


}
