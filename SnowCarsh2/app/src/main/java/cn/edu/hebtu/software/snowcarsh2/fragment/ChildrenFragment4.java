package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.hebtu.software.snowcarsh2.MainActivity;
import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment4.RadioAdapter;
import cn.edu.hebtu.software.snowcarsh2.bean.Radio;

public class ChildrenFragment4 extends Fragment{
    public static Fragment newInstance() {
        ChildrenFragment4 fragment = new ChildrenFragment4();
        return fragment;

    }

    private static final int RESULT = 0;
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //处理Message,运行在主线程，可以修改界面元素
            switch (msg.what) {
                case RESULT:
                    initRadios();
                    adapter2.notifyDataSetChanged();
                    swipeRefresh.setRefreshing(false);;
                    break;
            }

        }
    };




    private DrawerLayout mDrawerLayout;
    private Radio[] radios={new Radio(R.drawable.r1,"昭和电音",20,20),
            new Radio(R.drawable.r4,"昭和电音",20,20),
            new Radio(R.drawable.r5,"昭和电音",20,20),
            new Radio(R.drawable.r6,"昭和电音",20,20),
            new Radio(R.drawable.r8,"昭和电音",20,20),
            new Radio(R.drawable.r10,"昭和电音",20,20),
            new Radio(R.drawable.r11,"昭和电音",20,20),
            new Radio(R.drawable.r12,"昭和电音",20,20),
            new Radio(R.drawable.r13,"昭和电音",20,20),
            new Radio(R.drawable.r14,"昭和电音",20,20),
            new Radio(R.drawable.r15,"昭和电音",20,20),
            new Radio(R.drawable.r16,"昭和电音",20,20)

    };
    private List<Radio> radioList=new ArrayList<>();

    //    private FruitAdapter adapter;
    private RadioAdapter adapter2;

    private SwipeRefreshLayout swipeRefresh;
    private boolean mIsRefreshing=false;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.childrenfragment4,container,false);
        initRadios();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter2=new RadioAdapter(R.layout.childrenfragment4_item,radioList);
        recyclerView.setAdapter(adapter2);


        recyclerView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (mIsRefreshing) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshFruits();
                refreshRadios();
            }
        });
        return view ;
    }

    private void initRadios(){
        radioList.clear();
        for(int i=0;i<20;i++){
            Random random=new Random();
            int index=random.nextInt(radios.length);
            radioList.add(radios[index]);
        }
    }
    private Handler workHandler;
    private void refreshRadios(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Message message = new Message();
                    message.obj=1;
                    //添加标识
                    message.what = RESULT;
                    mainHandler.sendMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }


}
