package net.onest.week10_02;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImgTask extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://pic28.photophoto" +
                    ".cn/20130818/0020033143720852_b.jpg");
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            FileOutputStream fos = new FileOutputStream(
                    new File((String)objects[0] + "/abc.jpg"));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

            //关闭流
            is.close();
            fos.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
