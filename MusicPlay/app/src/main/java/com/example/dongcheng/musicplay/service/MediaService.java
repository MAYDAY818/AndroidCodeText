
package com.example.dongcheng.musicplay.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dongcheng.musicplay.MainActivity;
import com.example.dongcheng.musicplay.R;

import java.io.IOException;

public class MediaService extends Service {

    private static final String TAG = "MediaService";
    private MyBinder mBinder = new MyBinder();
    //标记当前歌曲的序号
    private int i = 0;
    //歌曲路径
    private String[] musicPath = new String[]
            {
            "http://samples.mplayerhq.hu/A-codecs/MP3/01%20-%20Charity%20Case.mp3",
            "http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E06DCBDC9AB7C49FD713D632D313AC4858BACB8DDD29067D3C601481D36E62053BF8DFEAF74C0A5CCFADD6471160CAF3E6A&fromtag=46",
            "http://samples.mplayerhq.hu/A-codecs/MP3/01%20-%20Charity%20Case.mp3"
//            Environment.getExternalStorageDirectory()+"/Sounds/张杰,张碧晨 - 只要平凡.mp3",
//            Environment.getExternalStorageDirectory()+"/Sounds/鞠婧祎 - 叹云兮.mp3",
//            Environment.getExternalStorageDirectory()+"/Sounds/Joel Adams - Please Don't Go.mp3"
    };
    //初始化MediaPlayer
    public MediaPlayer mMediaPlayer = new MediaPlayer();


    public MediaService() {
        iniMediaPlayerFile(i);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {

//        /**
//         *  获取MediaService.this（方便在ServiceConnection中）
//         *
//         * *//*
//        public MediaService getInstance() {
//            return MediaService.this;
//        }*/
        public boolean playstate(){
            return mMediaPlayer.isPlaying();
        }
        /**
         * 播放音乐
         */
        public void playMusic() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
            else{
                mMediaPlayer.start();
            }
        }
        /**
         * 暂停播放
         */
        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.pause();
            }
        }

        /**
         * reset
         */
        public void resetMusic() {
            if (!mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.reset();
                iniMediaPlayerFile(i);
            }
        }

        /**
         * 关闭播放器
         */
        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if (mMediaPlayer != null&& i >= 0) {
                //切换歌曲reset()很重要很重要很重要，没有会报IllegalStateException
                mMediaPlayer.reset();
                iniMediaPlayerFile((i+1)%3);
                i++;
                playMusic();
            }
        }

        /**
         * 上一首
         */
        public void preciousMusic() {
            if (mMediaPlayer != null && i < 4 && i > 0) {
                mMediaPlayer.reset();
                iniMediaPlayerFile(i - 1);
                if (i == 1) {

                } else {

                    i = i - 1;
                }
                playMusic();
            }
        }

        /**
         * 获取歌曲长度
         **/
        public int getProgress() {

            return mMediaPlayer.getDuration();
        }

        /**
         * 获取播放位置
         */
        public int getPlayPosition() {
          //  Log.e(TAG,""+ mMediaPlayer.getCurrentPosition());
            return mMediaPlayer.getCurrentPosition();
        }
        /**
         * 播放指定位置
         */
        public void seekToPositon(int msec) {
            mMediaPlayer.seekTo(msec);
        }




    }


    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private void iniMediaPlayerFile(int dex) {
        //获取文件路径
        try {
            //此处的两个方法需要捕获IO异常
            //设置音频文件到MediaPlayer对象中
            mMediaPlayer.setDataSource(musicPath[dex]);
            //让MediaPlayer对象准备
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.d(TAG, "设置资源，准备阶段出错");
            e.printStackTrace();
        }
    }
}