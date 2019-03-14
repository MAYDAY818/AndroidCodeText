package activitytext.example.com.demo13;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activitytext.example.com.demo13.adapter.IndexHorizontalListAdpter;
import activitytext.example.com.demo13.adapter.IndexVerticalListAdapter;
import activitytext.example.com.demo13.bean.IndexHorizontal;
import activitytext.example.com.demo13.bean.IndexVertical;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

public class MainActivity extends AppCompatActivity {
    private int UPDAT_TEXT = 0;


    //index页面RecyclerView
    private RecyclerView recyclerView;
    //纵向列表数据以及adapter.recyclerView

    private List<IndexVertical> indexVerticals;
    private List<IndexVertical> indexVerticalsAdd;
    private IndexVerticalListAdapter indexVerticalListAdapter;
    //横向列表数据以及adapter
    private RecyclerView recyclerHorizontal;
    private List<IndexHorizontal> indexHorizontals;
    private IndexHorizontalListAdpter indexHorizontalListAdpter;
    //baner图片地址以及标题
    private Banner mBanner;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    //刷新次数
    private int count;
    private int freshen = 1;
    //轮播图,新闻标题,新闻横向列表,文章标题,abnner
    private View headerBannerView;
    private View newsHeadlinesView;
    private View articleHeadlinesView;
    private View recyclerHorizontalView;
    private View bannerView;


    Elements titleLinks;
    Elements descLinks;
    Elements timeLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();

        //初始化RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_vertical_vertical);

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerHorizontal = (RecyclerView) recyclerHorizontalView.findViewById(R.id.recycler_horizontal_vertical);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(recyclerHorizontalView.getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerHorizontal.setLayoutManager(layoutManager2);
        indexHorizontalListAdpter = new IndexHorizontalListAdpter(R.layout.index_horizontal_list, indexHorizontals);
        recyclerHorizontal.setAdapter(indexHorizontalListAdpter);


        //创建适配器
        indexVerticalListAdapter = new IndexVerticalListAdapter(R.layout.index_vertical_list, indexVerticalsAdd);

//        indexVerticalListAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
////                indexVerticalsAdd = indexVerticals.subList(6,10);
//                IndexVertical indexVertical;
//                indexVerticalsAdd=new ArrayList<>();
//                for (int i = 0 ;i<5;i++){
//                    indexVertical = new IndexVertical();
//                    indexVertical.setTitle(indexVerticals.get(i+5*freshen).getTitle());
//                    indexVertical.setIntroduce(indexVerticals.get(i+5*freshen).getIntroduce());
//                    indexVerticalsAdd.add(indexVertical);
//                }
//                freshen++;
//                startUpFetch();
//            }
//        });

        //动画效果
//            public static final int ALPHAIN = 0x00000001;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SCALEIN = 0x00000002;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_BOTTOM = 0x00000003;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_LEFT = 0x00000004;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_RIGHT = 0x00000005;
//
//
//            //使用缩放动画
//            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //默认动画
        indexVerticalListAdapter.openLoadAnimation();
        //设置重复执行动画
        indexVerticalListAdapter.isFirstOnly(false);
        //设置下拉刷新
//        indexVerticalListAdapter.setUpFetchEnable(true);
        //添加View
        indexVerticalListAdapter.addHeaderView(headerBannerView);
        indexVerticalListAdapter.addHeaderView(newsHeadlinesView);
        indexVerticalListAdapter.addHeaderView(recyclerHorizontalView);
        indexVerticalListAdapter.addHeaderView(articleHeadlinesView);
        //给RecyclerView设置适配器
        recyclerView.setAdapter(indexVerticalListAdapter);


        setBanner();

        //条目点击事件
        indexVerticalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });
//
//        //条目长按事件
//        indexVerticalListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        //上拉刷新
        indexVerticalListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (freshen >= 5) {
                            //数据全部加载完毕
                            indexVerticalListAdapter.loadMoreEnd();
                        } else {
                            if (true) {
                                //成功获取更多数据
                                getNewDate();
                                indexVerticalListAdapter.addData(indexVerticalsAdd);
                                freshen++;
                                indexVerticalListAdapter.loadMoreComplete();
                            }
//                            } else {
//                                //获取更多数据失败
//                                isErr = true;
//                                Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
//                                mQuickAdapter.loadMoreFail();
//
//                            }
                        }
                    }

                }, 300);
            }
        }, recyclerView);

}


    public void init(){

        //刷新次数(后期更改)
        count=1;
        //轮播图,新闻标题,新闻横向列表,文章标题
        headerBannerView = LayoutInflater.from(this).inflate(R.layout.banner_view, null);
        newsHeadlinesView = LayoutInflater.from(this).inflate(R.layout.news_headlines, null);
        articleHeadlinesView = LayoutInflater.from(this).inflate(R.layout.article_headlines,null);
        recyclerHorizontalView= LayoutInflater.from(this).inflate(R.layout.recycler_horizontal_view, null);
        bannerView=LayoutInflater.from(this).inflate(R.layout.banner, null);


        //banner数据
        images.add(new String("https://image.gcores.com/ca141598-f4d8-4c0e-b8c5-187c28858682.jpg?x-oss-process=style/original_hs"));
        titles.add(new String("1111111111111111"));
        images.add(new String("https://alioss.gcores.com/uploads/image/2e3573fc-0faa-40dd-b262-5d77e9bd288f_watermark.jpg"));
        titles.add(new String("2222222222222222"));
        images.add(new String("https://alioss.gcores.com/uploads/image/d91f7c10-59da-4000-815f-70956f04ca07_watermark.png"));
        titles.add(new String("3333333333333333"));
        images.add(new String("https://alioss.gcores.com/uploads/image/71c2eece-911e-459e-8377-635c2f8bce58_watermark.png"));
        titles.add(new String("4444444444444444"));

        //纵向列表数据
        //模拟的数据（实际开发中一般是从网络获取的）
        indexVerticals = new ArrayList<>();
        IndexVertical indexVertical;
        for (int i = 0; i < 100; i++) {
            indexVertical = new IndexVertical();
            indexVertical.setTitle("我是第" + i + "条标题");
            indexVertical.setIntroduce("第" + i + "条内容");
            indexVerticals.add(indexVertical);
            Log.e(TAG, indexVerticals.get(i).getTitle() );
        }

        indexVerticalsAdd = new ArrayList<>();
        for (int i = 0 ;i<5;i++){
            indexVertical = new IndexVertical();
            indexVertical.setTitle(indexVerticals.get(i).getTitle());
            indexVertical.setIntroduce(indexVerticals.get(i).getIntroduce());
            indexVerticalsAdd.add(indexVertical);
        }

//        MyThread myThread = new MyThread();
//        new Thread(myThread).start();


        //for循环遍历获取到每条新闻的四个数据并封装到News实体类中
        indexHorizontals=new ArrayList<>();
        IndexHorizontal indexHorizontal;
        for(int i=0;i<10;i++){
            indexHorizontal = new IndexHorizontal();
            indexHorizontal.setTitle("标题");
            indexHorizontal.setIntroduce("简介");
            indexHorizontals.add(indexHorizontal);
        }

    }

    public void setBanner(){

        mBanner = (Banner) headerBannerView.findViewById(R.id.banner);
        //设置banner样式
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
    };


//    class MyThread implements Runnable{
//        @Override
//        public void run() {
//            //横向列表数据
//            //https://www.gcores.com/categories/1/originals
//            Document doc = null;
//            try {
//                doc = Jsoup.connect("https://www.gcores.com/categories/1/originals").get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            titleLinks = doc.select("div.showcase_text");//解析来获取每条新闻的标题与链接地址
//            Log.e(TAG, titleLinks.get(0).select("a").text() );
//            descLinks = doc.select("div.showcase_info");//解析来获取每条新闻的简介
//            Log.e(TAG, descLinks.get(0).text() );
//            timeLinks = doc.select("div.showcase_time");   //解析来获取每条新闻的时间与来源
//
//
//
//            //纵向列表数据
//            //模拟的数据（实际开发中一般是从网络获取的）
//            indexVerticals = new ArrayList<>();
//            IndexVertical indexVertical;
//            for (int i = 0; i < timeLinks.size(); i++) {
//                indexVertical = new IndexVertical();
//                indexVertical.setTitle(titleLinks.get(i).select("a").text());
//                indexVertical.setIntroduce(descLinks.get(i).text());
//                indexVerticals.add(indexVertical);
//            }
//            UPDAT_TEXT = 1;
//            Log.e(TAG, "UPDATE="+ UPDAT_TEXT );
//        }
//    }

    //获取下拉列表新消息
    public void getNewDate(){
        indexVerticalsAdd = new ArrayList<>();
        IndexVertical indexVertical;
        for (int i = 0 ;i<5;i++){
            Log.e(TAG, "getNewDate: "+freshen);
            indexVertical = new IndexVertical();
            indexVertical.setTitle(indexVerticals.get(i+5*freshen).getTitle());
            indexVertical.setIntroduce(indexVerticals.get(i+5*freshen).getIntroduce());
            indexVerticalsAdd.add(indexVertical);
        }
    }
    private static final String TAG = "MainActivity";
}
