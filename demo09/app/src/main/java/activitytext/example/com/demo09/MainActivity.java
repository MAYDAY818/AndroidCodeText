package activitytext.example.com.demo09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import activitytext.example.com.demo09.adapter.HomeAdapter;
import activitytext.example.com.demo09.adapter.ContentAdapter;
import activitytext.example.com.demo09.bean.Model;
import activitytext.example.com.demo09.bean.content;

public class MainActivity extends AppCompatActivity {

    private List<content> contentList=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewheng;
    private List<Model> datas;
    private HomeAdapter adapter;
    private Banner mBanner;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initContent();
        images.add(new String("https://alioss.gcores.com/uploads/image/c7c67598-1012-41aa-8452-6f31e11fc590_watermark.jpg"));
        titles.add(new String("1111111111111111"));
        images.add(new String("https://alioss.gcores.com/uploads/image/2e3573fc-0faa-40dd-b262-5d77e9bd288f_watermark.jpg"));
        titles.add(new String("2222222222222222"));
        images.add(new String("https://alioss.gcores.com/uploads/image/d91f7c10-59da-4000-815f-70956f04ca07_watermark.png"));
        titles.add(new String("3333333333333333"));
        images.add(new String("https://alioss.gcores.com/uploads/image/71c2eece-911e-459e-8377-635c2f8bce58_watermark.png"));
        titles.add(new String("4444444444444444"));

        //初始化RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerViewheng = (RecyclerView) findViewById(R.id.recycler_view_heng);

//        xinwenView = (RecyclerView) findViewById(R.id.xinwen);

        //模拟的数据（实际开发中一般是从网络获取的）
        datas = new ArrayList<>();
        Model model;
        for (int i = 0; i < 15; i++) {
            model = new Model();
            model.setTitle("我是第" + i + "条标题");
            model.setContent("第" + i + "条内容");
            datas.add(model);
        }


        View header = LayoutInflater.from(this).inflate(R.layout.indexbanner, null);
        View xinwenbiaoti = LayoutInflater.from(this).inflate(R.layout.xinwenbiaoti, null);

//        View xinwenhengxian =LayoutInflater.from(this).inflate(R.layout.activity_main_heng, null);
//
//        recyclerViewheng.setLayoutManager(new LinearLayoutManager(xinwenhengxian.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        ContentAdapter contentAdapter=new ContentAdapter(contentList);
//        recyclerViewheng.setAdapter(contentAdapter);

        mBanner = (Banner) header.findViewById(R.id.banner);

//        View view= LayoutInflater.from(this).inflate(R.layout.indexbanner,recyclerView, false);
//        mMyAdapter.addHeaderView(view);




        //创建适配器
        adapter = new HomeAdapter(R.layout.item_rv, datas);



        adapter.addHeaderView(header);

        adapter.addHeaderView(xinwenbiaoti);


//        adapter.addHeaderView(xinwenhengxian);



//        //创建布局管理
//        LinearLayoutManager layoutManagerheng = new LinearLayoutManager(this);
//        layoutManagerheng.setOrientation(LinearLayoutManager.HORIZONTAL);
//        xinwenView.setLayoutManager(layoutManagerheng);
        //创建适配器
//        adapter = new HomeAdapter(R.layout.indexbanner, datas);

//        recyclerView.setAdapter(adapter);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
//        mBanner.setImages(images).setBannerTitles(titles).setImageLoader(new GlideImageLoader()).start();
//        adapter.addHeaderView(view);



        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onItemClick: "+position);
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });


//        //条目长按事件
//        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        //条目子控件点击事件
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //条目子控件长按事件
//        adapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
//            @Override
//            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        //给RecyclerView设置适配器
//        xinwenView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

//

    }
    public void initContent(){
        for(int i=0;i<10;i++){

            contentList.add(new content(R.drawable.radio1,"特别期待的的借口拒绝如果胡歌虎肉过后","radio"));

        }

    }

    private static final String TAG = "MainActivity";
}
