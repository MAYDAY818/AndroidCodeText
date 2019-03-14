package com.example.dongcheng.musicplay;

import android.content.Context;

import java.io.File;

public class Utils {
    public static File getVideoCacheDir(Context context) {
        return new File(context.getExternalFilesDir("music"), "audio-cache");
    }
}
