package activitytext.example.com.demo12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import activitytext.example.com.demo12.adapter.IndexHorizontalListAdapter;
import activitytext.example.com.demo12.adapter.IndexVerticalListAdapter;
import activitytext.example.com.demo12.bean.IndexHorizontalList;
import activitytext.example.com.demo12.bean.IndexVerticalList;

public class MainActivity extends AppCompatActivity {
    //横向列表与纵向列表
    private List<IndexHorizontalList> indexHorizontalLists =new ArrayList<>();
    private List<IndexVerticalList> indexVerticalLists = new ArrayList<>();


    //banner标题与题目
    private Banner mBanner;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    //设置列表
    private RecyclerView recyclerVertical;
    private RecyclerView recyclerHorizontal;

    private IndexVerticalListAdapter indexVerticalListAdapter;
    private IndexHorizontalListAdapter indexHorizontalListAdapter;

    //设置按钮(显示新闻,文章)
    private Button showAllTheNews;
    private Button showAllTheArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        init();

//        View recyclerVorticalView =LayoutInflater.from(this).inflate(R.layout.activity_main,null);
//        View bannerView = LayoutInflater.from(this).inflate(R.layout.index_banner, null);
//        View newsHeadlines = LayoutInflater.from(this).inflate(R.layout.news_headlines, null);
//        View articleHeadlines = LayoutInflater.from(this).inflate(R.layout.article_headlines,null);
//        View recyclerHorizontalView= LayoutInflater.from(this).inflate(R.layout.recycler_horizontal_view, null);

//        //横向列表初始化
//        recyclerHorizontal=(RecyclerView)recyclerHorizontalView.findViewById(R.id.recycler_horizontal_vertical);
//        recyclerHorizontal.setLayoutManager(new LinearLayoutManager(recyclerHorizontalView.getContext(),LinearLayoutManager.HORIZONTAL,false));
//        indexHorizontalListAdapter =new IndexHorizontalListAdapter(R.layout.index_horizontal_list,indexHorizontalLists);
//        recyclerHorizontal.setAdapter(indexHorizontalListAdapter);





//        mBanner = (Banner) bannerView.findViewById(R.id.banner);



        indexVerticalListAdapter = new IndexVerticalListAdapter(R.layout.index_vertical_list, indexVerticalLists);

//        indexVerticalListAdapter.addHeaderView(bannerView);
//        indexVerticalListAdapter.addHeaderView(newsHeadlines);
//        indexVerticalListAdapter.addHeaderView(recyclerHorizontalView);
//        indexVerticalListAdapter.addHeaderView(articleHeadlines);



        recyclerVertical.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerVertical.setAdapter(indexVerticalListAdapter);
//        recyclerVertical = (RecyclerView)recyclerVorticalView.findViewById(R.id.recycler_vertical_vertical);

//        recyclerVertical.setAdapter(indexVerticalListAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerVorticalView.getContext() );
//        //设置布局管理器
//        recyclerVertical.setLayoutManager(layoutManager);
//        //设置Adapter
//        recyclerVertical.setAdapter(indexVerticalListAdapter);




        //banner具体设置
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
//        mBanner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        mBanner.setImages(images);
//        //设置banner动画效果
//        mBanner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
//        //设置自动轮播，默认为true
//        mBanner.isAutoPlay(true);
//        //设置轮播时间
//        mBanner.setDelayTime(3000);
//        //设置指示器位置（当banner模式中有指示器时）
//        mBanner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
//        mBanner.start();


//        indexVerticalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Log.d(TAG, "onItemClick: "+position);
//                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
//            }
//        });

    }



    public void init(){
        //显示新闻,文章
//        showAllTheArticle=findViewById(R.id.show_all_the_article);
//        showAllTheNews=findViewById(R.id.show_all_the_news);

//        showAllTheNews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        showAllTheArticle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        //设置banner信息
//        images.add(new String("https://alioss.gcores.com/uploads/image/c7c67598-1012-41aa-8452-6f31e11fc590_watermark.jpg"));
//        titles.add(new String("1111111111111111"));
//        images.add(new String("https://alioss.gcores.com/uploads/image/2e3573fc-0faa-40dd-b262-5d77e9bd288f_watermark.jpg"));
//        titles.add(new String("2222222222222222"));
//        images.add(new String("https://alioss.gcores.com/uploads/image/d91f7c10-59da-4000-815f-70956f04ca07_watermark.png"));
//        titles.add(new String("3333333333333333"));
//        images.add(new String("https://alioss.gcores.com/uploads/image/71c2eece-911e-459e-8377-635c2f8bce58_watermark.png"));
//        titles.add(new String("4444444444444444"));

        //设置纵向列表
        for (int i = 0; i < 15; i++) {
            IndexVerticalList indexVerticalList= new IndexVerticalList();
            indexVerticalList.setTitle("我是第" + i + "条标题");
            indexVerticalList.setIntroduce("第" + i + "条内容");
            indexVerticalLists.add(indexVerticalList);
        }

//        //设置分享列表
//        for(int i=0;i<10;i++){
//            indexHorizontalLists.add(new IndexHorizontalList(1,R.drawable.radio1,"特别期待的的借口拒绝如果胡歌虎肉过后","radio"));
//        }

    }

    private static final String TAG = "MainActivity";
}
