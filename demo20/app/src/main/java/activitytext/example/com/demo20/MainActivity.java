package activitytext.example.com.demo20;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;
    private Button btnVideo;
    private VideoView videoView;
    private MediaListener listener;
    private MediaPlayer mediaPlayer;
    private AssetManager assetManager;
    private AssetFileDescriptor assetFileDescriptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        btnVideo=findViewById(R.id.btnVideo);
        videoView=findViewById(R.id.video);
        listener=new MediaListener();

        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        btnVideo.setOnClickListener(listener);

        mediaPlayer=new MediaPlayer();
        assetManager=getAssets();
        try {
            assetFileDescriptor=assetManager.openFd("life3.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                                        assetFileDescriptor.getStartOffset(),
                                        assetFileDescriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.reset();
                mediaPlayer=new MediaPlayer();
                assetManager=getAssets();
                try {
                    assetFileDescriptor=assetManager.openFd("life3.mp4");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(),
                            assetFileDescriptor.getLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });
        mediaPlayer.start();
    }
    class MediaListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start:
                    if(mediaPlayer.isPlaying())
                        mediaPlayer.start();
                    else
                        mediaPlayer.stop();
                    break;
                case R.id.stop:
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.seekTo(0);
                        mediaPlayer.stop();
                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case R.id.btnVideo:
                    //视频播放
                    File file=new File(getFilesDir()+"");
                    if(file.exists()){
                        videoView.setVideoPath(file.getPath());
                    }
                    if(videoView.isPlaying()) {
                        videoView.pause();
                    }else{
                        videoView.start();
                    }
                    break;
            }

        }
    }
}
