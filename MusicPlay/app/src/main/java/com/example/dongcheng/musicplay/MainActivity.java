package com.example.dongcheng.musicplay;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.dongcheng.musicplay.adapter.ViewPagerAdapter;
import com.example.dongcheng.musicplay.adapter.ViewPagerAdapter1;
import com.example.dongcheng.musicplay.service.MediaService;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler mHandler = new Handler();
    private static final String TAG = "MainActivity";
    private MediaService.MyBinder mMyBinder;
    private ImageView play;
    private ImageView next;
    private SeekBar mSeekBar;
    private TextView mTextView;
    //进度条下面的当前进度文字，将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    //“绑定”服务的intent
    Intent MediaServiceIntent;
    //横向画廊
    private ViewPager mViewPager;
    private ViewPager mViewPager2;
    private LinearLayout page_layout;
    private LinearLayout lll_layout;
    //图片载入
    private MyApplication myApplication;
    private HttpProxyCacheServer proxy;
    private String proxyUrl;

    //图片载入
    private String[]mPics=new String[]{
            "http://e.hiphotos.baidu.com/image/h%3D300/sign=73443062281f95cab9f594b6f9177fc5/72f082025aafa40fafb5fbc1a664034f78f019be.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/6159252dd42a2834a75bb01156b5c9ea15cebf2f.jpg",
            "http://c.hiphotos.baidu.com/image/h%3D300/sign=cfce96dfa251f3dedcb2bf64a4eff0ec/4610b912c8fcc3ce912597269f45d688d43f2039.jpg",
            "http://e.hiphotos.baidu.com/image/pic/item/6a600c338744ebf85ed0ab2bd4f9d72a6059a705.jpg",
            "http://b.hiphotos.baidu.com/image/h%3D300/sign=8ad802f3801001e9513c120f880e7b06/a71ea8d3fd1f4134be1e4e64281f95cad1c85efa.jpg",
            "http://e.hiphotos.baidu.com/image/h%3D300/sign=73443062281f95cab9f594b6f9177fc5/72f082025aafa40fafb5fbc1a664034f78f019be.jpg",
            "http://d.hiphotos.baidu.com/image/pic/item/6159252dd42a2834a75bb01156b5c9ea15cebf2f.jpg"};
    private String[] musicPath = new String[]
            {
            "http://samples.mplayerhq.hu/A-codecs/MP3/01%20-%20Charity%20Case.mp3",
            "http://ws.stream.qqmusic.qq.com/M500001VfvsJ21xFqb.mp3?guid=ffffffff82def4af4b12b3cd9337d5e7&uin=346897220&vkey=6292F51E1E384E06DCBDC9AB7C49FD713D632D313AC4858BACB8DDD29067D3C601481D36E62053BF8DFEAF74C0A5CCFADD6471160CAF3E6A&fromtag=46",
            "http://samples.mplayerhq.hu/A-codecs/MP3/01%20-%20Charity%20Case.mp3"
//                    Environment.getExternalStorageDirectory()+"/Sounds/张杰,张碧晨 - 只要平凡.mp3",
//                    Environment.getExternalStorageDirectory()+"/Sounds/鞠婧祎 - 叹云兮.mp3",
//                    Environment.getExternalStorageDirectory()+"/Sounds/Joel Adams - Please Don't Go.mp3"
            };
    //内容载入
    private String[] mname=new String[]{"游戏介绍","游戏介绍","游戏介绍","游戏介绍","游戏介绍","游戏介绍","游戏介绍"};
    private String[] mcontext=new String[]{
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品5",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品1",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品2",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品3",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品4",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品5",
            "制作游戏本身是很难的， 一个具有创意和趣味的游戏不乏称之为是更具现代气息的艺术品1"};
    //初始化MediaPlayer
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);

        }
    };
    private Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mViewPager2.setCurrentItem(mViewPager2.getCurrentItem()+1);
        }
    };
    private Handler handler3=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ImageView imageView=findViewById(R.id.mp3_play);
            if (!mMyBinder.playstate()){
                imageView.setImageResource(R.mipmap.stopmusic);
            }else if (mMyBinder.playstate()){
                imageView.setImageResource(R.mipmap.playmusic);}
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniView();
        MediaServiceIntent = new Intent(this, MediaService.class);


        //判断权限够不够
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            //够了就设置路径等，准备播放
            bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        }


    }

    //获取到权限回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[]permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                } else {
                    Toast.makeText(this, "权限不够获取不到音乐，程序将退出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MediaService.MyBinder) service;
            mSeekBar.setMax(mMyBinder.getProgress());

            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser){
                        mMyBinder.seekToPositon(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });


            mHandler.post(mRunnable);

            Log.d(TAG, "Service与Activity已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };





    private void iniView() {

        play =  findViewById(R.id.mp3_play);
        next =  findViewById(R.id.mp3_next);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);
        mTextView = (TextView) findViewById(R.id.time_text);
        play.setOnClickListener(this);
        next.setOnClickListener(this);
        //轮播图片和文字内容----------------------------
        mViewPager=(ViewPager) findViewById(R.id.viewPager);
        mViewPager2=(ViewPager) findViewById(R.id.viewPager2);
        page_layout=findViewById(R.id.page_layout);
        lll_layout=findViewById(R.id.lll_layout);
        //设置适配器——pageview-------------------------------
        mViewPager.setAdapter(new ViewPagerAdapter(this,mPics));
        mViewPager2.setAdapter(new ViewPagerAdapter1(this,mname,mcontext));
        //切换动作的时间控制----------------------------
        try {
            Field mField1 = ViewPager.class.getDeclaredField("mScroller");
            mField1.setAccessible(true);
            FixedSpeedScroller scroller =new FixedSpeedScroller(mViewPager.getContext(),
                    new AccelerateInterpolator());
            mField1.set(mViewPager,scroller);
            scroller.setmDuration(3000);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field mField2 = ViewPager.class.getDeclaredField("mScroller");
            mField2.setAccessible(true);
            FixedSpeedScroller scroller =new FixedSpeedScroller(mViewPager2.getContext(),
                   new AccelerateInterpolator());
            mField2.set(mViewPager2,scroller);
            scroller.setmDuration(3000);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //-----------------------------------------------
        mViewPager.setPageMargin(20);
        mViewPager2.setPageMargin(20);
        mViewPager.setOffscreenPageLimit(mPics.length);
        mViewPager2.setOffscreenPageLimit(mcontext.length);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        mViewPager2.setPageTransformer(true,new ZoomOutPageTransformer());
        //左右都有图片
        mViewPager.setCurrentItem(1);
        mViewPager2.setCurrentItem(0);
        //无限循环
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int currentPosition;
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPosition=i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // ViewPager.SCROLL_STATE_IDLE 标识的状态是当前页面完全展现，
                // 并且没有动画正在进行中，如果不
                // 是此状态下执行 setCurrentItem 方法回在首位替换的时候会出现跳动！
                if (i!=ViewPager.SCROLL_STATE_IDLE)return;
                //当视图在第一个时，将页号设置为图片的最后一张。
                if (currentPosition==0){
                    mViewPager.setCurrentItem(mPics.length-2,false);
                    mViewPager2.setCurrentItem(mname.length-2,false);
                }else if (currentPosition==mPics.length-1){
                    mViewPager.setCurrentItem(1,false);
                    mViewPager2.setCurrentItem(1,false);
                }

            }
        });
        mViewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int currentPosition;
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPosition=i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // ViewPager.SCROLL_STATE_IDLE 标识的状态是当前页面完全展现，
                // 并且没有动画正在进行中，如果不
                // 是此状态下执行 setCurrentItem 方法回在首位替换的时候会出现跳动！
                if (i!=ViewPager.SCROLL_STATE_IDLE)return;
                //当视图在第一个时，将页号设置为图片的最后一张。
                if (currentPosition==0){
                    mViewPager.setCurrentItem(mPics.length-2,false);
                }else if (currentPosition==mPics.length-1){
                    mViewPager.setCurrentItem(1,false);
                }

            }
        });
        //VIEWPAGER左右滑动无效的处理
        page_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
        lll_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager2.dispatchTouchEvent(event);
            }
        });
        //图片切换控制---------
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(2);
                }
            }
        }).start();
        //文字切换控制----------------
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler2.sendEmptyMessage(2);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler3.sendEmptyMessage(2);
                }
            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mp3_play:
                ImageView imageView=findViewById(R.id.mp3_play);
                if(mMyBinder.playstate()){
                    Log.e("","暂停");
                    imageView.setImageResource(R.mipmap.stopmusic);
                }else{
                    Log.e("","开始");
                    imageView.setImageResource(R.mipmap.playmusic);}

                mMyBinder.playMusic();
                break;
            case R.id.mp3_next:
                mMyBinder.nextMusic();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
        mHandler.removeCallbacks(mRunnable);
        mMyBinder.closeMedia();
        unbindService(mServiceConnection);
    }

    /**
     * 更新ui的runnable
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mSeekBar.setProgress(mMyBinder.getPlayPosition());
            mTextView.setText(time.format(mMyBinder.getPlayPosition()) + "s");
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
}
