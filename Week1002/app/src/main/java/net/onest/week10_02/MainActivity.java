package net.onest.week10_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        socketRequest();//实现socket请求
        downloadImg();
        login();
    }

    private void login(){
        Button button = findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask loginTask = new LoginTask();
                loginTask.execute();
            }
        });
    }

    private void downloadImg(){
        Button button = findViewById(R.id.btn_download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = getFilesDir().getAbsolutePath();
                DownloadImgTask downloadImgTask = new DownloadImgTask();
                downloadImgTask.execute(path);
            }
        });
    }

    private void socketRequest(){
        Button button = findViewById(R.id.btn_socket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket("10.7.89.251",10000);
                            InputStream is = socket.getInputStream();
                            byte[] b = new byte[1024];
                            is.read(b);
                            Log.e("Socket",new String(b));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }
}
