package activitytext.example.com.gcrosenews;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activitytext.example.com.gcrosenews.adapter.NewsAdapter;
import activitytext.example.com.gcrosenews.adapter.NewsHorizontalAdapter;
import activitytext.example.com.gcrosenews.bean.New;
import activitytext.example.com.gcrosenews.bean.NewsHorizontal;
import activitytext.example.com.gcrosenews.tools.GlideImageLoader;

public class MainActivity extends AppCompatActivity {
    //判断二次加载
    private int loading = 0;
    //加载页
    private int page=1;
    private View xinwenbiantiView;
    private View bannerView;
    private RecyclerView recyclerView;
    private View horView;

    //搜索
    private String search;

    //纵向列表
    private List<New> datas;
    private List<New> datasAdd;
    private NewsAdapter adapter;
    //横向列表
    private RecyclerView recyclerView2;
    private List<NewsHorizontal> newsHorList;
    private NewsHorizontalAdapter newsHorizontalAdapter;


    private Handler handler;
    //baner图片地址以及标题
    private Banner mBanner;
    List<String> bannerImages = new ArrayList<>();
    List<String> bannerUrl = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        SearchView searchView = (SearchView)findViewById(R.id.searchView);
        //设置我们的ToolBar
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置我们的SearchView
        searchView.setIconifiedByDefault(true);//设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
        searchView.onActionViewExpanded();// 写上此句后searchView初始是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能出现输入框,也就是设置为ToolBar的ActionView，默认展开
        searchView.requestFocus();//输入焦点
        searchView.setSubmitButtonEnabled(true);//添加提交按钮，监听在OnQueryTextListener的onQueryTextSubmit响应
        searchView.setFocusable(true);//将控件设置成可获取焦点状态,默认是无法获取焦点的,只有设置成true,才能获取控件的点击事件
        searchView.setIconified(false);//输入框内icon不显示
        searchView.requestFocusFromTouch();//模拟焦点点击事件

        searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
        searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要

        //事件监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("data",search);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.equals("")&&!newText.isEmpty())
                {
                    search = new String(newText.toString());
                    Log.e(TAG, "onQueryTextChange:newText "+ newText );
                    Log.e(TAG, "onQueryTextChange: search"+ search );
                }
                return false;
            }
        });


        //初始化RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        bannerView=LayoutInflater.from(this).inflate(R.layout.banner_view, null);

        horView=LayoutInflater.from(this).inflate(R.layout.new_adapter_view, null);
        recyclerView2=(RecyclerView) horView.findViewById(R.id.recycler_horizontal_vertical);

        xinwenbiantiView=LayoutInflater.from(this).inflate(R.layout.xinwenbiaoti_view, null);

        datas = new ArrayList<>();
        newsHorList = new ArrayList<>();

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



        LinearLayoutManager layoutManager2 = new LinearLayoutManager(recyclerView2.getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(layoutManager2);


        NewsUpdateThread newsUpdateThread = new NewsUpdateThread();
        new Thread(newsUpdateThread).start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {

                    newsHorizontalAdapter = new NewsHorizontalAdapter(R.layout.new_adapter_heng, newsHorList);

                    recyclerView2.setAdapter(newsHorizontalAdapter);
                    //点击事件
                    newsHorizontalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(newsHorList.get(position).getNewUrl()));
                            startActivity(intent);
                        }
                    });

                    //初始化
                    //创建适配器
                    adapter = new NewsAdapter(R.layout.new_adapter, datas);
                    adapter.addHeaderView(bannerView);
                    adapter.addHeaderView(xinwenbiantiView);
                    adapter.addHeaderView(horView);
                    //给RecyclerView设置适配器
                    recyclerView.setAdapter(adapter);
                    //添加动画
                    adapter.openLoadAnimation();
                    //上拉事件
                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            loadMore();
                        }
                    });
                    //点击事件
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(datas.get(position).getNewUrl()));
                        startActivity(intent);
                        }
                    });

                    setBanner();

                } else if (msg.what == 2){
                    //更新数据
                    Log.e(TAG, "handleMessage: " );
                    adapter.addData(datasAdd);
                    adapter.loadMoreComplete();

                }
            }
        };



    }

    private void loadMore() {
        new Thread(new NewsAddThread()).start();
    }


    class NewsUpdateThread implements Runnable{

        @Override
        public void run() {
            Log.e(TAG, "线程开始" );

            try {
                if(loading==0){
                    Log.e(TAG, "开始抓取");

                    //抓取banner
                    Document docBanner = Jsoup.connect("https://www.gcores.com/").get();
                    //抓取垂直列表
                    Document doc = Jsoup.connect("https://www.gcores.com/categories/1/originals?page=" + page).get();
                    //抓取水平列表
                    Document docHorizontal = Jsoup.connect("https://www.gcores.com/categories/2/originals?page=1").get();

                    Elements imgBannerLinks = docBanner.select("img.visible-xs-inline-block");
                    Elements imgBannerUrl = docBanner.select("div.swiper-wrapper").select("a");
                    Log.e(TAG, imgBannerUrl.toString());
    //                Elements urlBannerLinks = docBanner.select("div.swiper-slide");
                    for (int j = 0; j < imgBannerLinks.size(); j++) {
                        //banner链接(OK)
                        String imgUrl1 = imgBannerUrl.select("a").attr("href");
                        bannerUrl.add(imgUrl1);
                        //banner图片(OK)
                        String imgUrl = imgBannerLinks.get(j).attr("src");
                        bannerImages.add(imgUrl);
                        Log.e(TAG, "Banner:" + imgUrl);
                    }


                    page++;
                    Elements imgLinks = doc.select("div.showcase_img");
                    Elements textLinks = doc.select("div.showcase_text");
                    New new2;
                    for (int j = 0; j < imgLinks.size(); j++) {
                        new2 = new New();
                        //标题(OK)
                        String title = textLinks.get(j).select("h4").text();
                        new2.setNewsTitle(title);
                        Log.e(TAG, "title:" + title);
                        //简介(ok)
                        String desc = textLinks.get(j).select("div.showcase_info").text();
                        new2.setDesc(desc);
                        Log.e(TAG, "desc:" + desc);
                        //网址(OK)
                        String url = imgLinks.get(j).select("a").attr("href");
                        new2.setNewUrl(url);
                        Log.e(TAG, "url:" + url);
                        //图片(OK)
                        String imgUrl = imgLinks.get(j).select("img").attr("src");
                        new2.setImgUrl(imgUrl);
                        Log.e(TAG, "imgurl:" + imgUrl);
                        datas.add(new2);
                    }

                    Log.e(TAG, "抓取水平");
                    Elements imgHorLinks = docHorizontal.select("div.showcase_img");
                    Elements textHorLinks = docHorizontal.select("div.showcase_text");
                    NewsHorizontal newsHorizontal;
                    for (int j = 0; j < 10; j++) {
                        newsHorizontal = new NewsHorizontal();
                        //标题(OK)
                        String title = textHorLinks.get(j).select("h4").text();
                        newsHorizontal.setNewsTitle(title);
                        Log.e(TAG, "title:" + newsHorizontal.getNewsTitle());
                        //简介(ok)
                        String desc = textHorLinks.get(j).select("div.showcase_info").text();
                        newsHorizontal.setDesc(desc);
                        Log.e(TAG, "desc:" + desc);
                        //网址(OK)
                        String url = imgHorLinks.get(j).select("a").attr("href");
                        newsHorizontal.setNewUrl(url);
                        Log.e(TAG, "url:" + url);
                        //图片(OK)
                        String imgUrl = imgHorLinks.get(j).select("img").attr("src");
                        newsHorizontal.setImgUrl(imgUrl);
                        Log.e(TAG, "imgurl:" + imgUrl);
                        newsHorList.add(newsHorizontal);
                    }
                    Log.e(TAG, "水平结束 ");
                    loading=1;
            }
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"联网失败",Toast.LENGTH_LONG).show();
                Log.e(TAG, "无法连接" );
            }
        }
    }

    class NewsAddThread implements Runnable{

        @Override
        public void run() {
            datasAdd = new ArrayList<>();
            Log.e(TAG, "线程开始" );
            try {
                Log.e(TAG, "开始抓取" );
                Document doc = Jsoup.connect("https://www.gcores.com/categories/1/originals?page="+page).get();
                page++;
                Elements imgLinks = doc.select("div.showcase_img");
                Elements textLinks= doc.select("div.showcase_text");
                New new2;
                for(int j = 0;j < imgLinks.size();j++){
                    new2 = new New();
                    //标题(OK)
                    String title = textLinks.get(j).select("h4").text();
                    new2.setNewsTitle(title);
                    Log.e(TAG, "title:"+title );
                    //简介(ok)
                    String desc = textLinks.get(j).select("div.showcase_info").text();
                    new2.setDesc(desc);
                    Log.e(TAG, "desc:"+desc );
                    //网址(OK)
                    String url = imgLinks.get(j).select("a").attr("href");
                    new2.setNewUrl(url);
                    Log.e(TAG, "url:"+url );
                    //图片(OK)
                    String imgUrl = imgLinks.get(j).select("img").attr("src");
                    new2.setImgUrl(imgUrl);
                    Log.e(TAG, "imgurl:"+imgUrl );
                    datasAdd.add(new2);
                }
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"联网失败",Toast.LENGTH_LONG).show();
                Log.e(TAG, "无法连接" );
            }
        }
    }


    public void setBanner(){
        final Banner banner = (Banner) bannerView.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(bannerImages);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        Banner onBannerListener = banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(bannerUrl.get(position).toString()));
                startActivity(intent);
            }
        });

    };

    private static final String TAG = "MainActivity";
}
