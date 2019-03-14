package activitytext.example.com.demo08;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBannerListener {

    Banner banner;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    private String[] data ={"11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",
            "11111111111","11111111111","11111111111","11111111111","11111111111","11111111111",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView)findViewById(R.id.list_view);
        View view = LayoutInflater.from(this).inflate(R.layout.bannerdemo, null);
        listView.addHeaderView(view);
        listView.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,data));

        images.add(new String("https://alioss.gcores.com/uploads/image/c7c67598-1012-41aa-8452-6f31e11fc590_watermark.jpg"));
        titles.add(new String("1111111111111111"));
        images.add(new String("https://alioss.gcores.com/uploads/image/2e3573fc-0faa-40dd-b262-5d77e9bd288f_watermark.jpg"));
        titles.add(new String("2222222222222222"));
        images.add(new String("https://alioss.gcores.com/uploads/image/d91f7c10-59da-4000-815f-70956f04ca07_watermark.png"));
        titles.add(new String("3333333333333333"));
        images.add(new String("https://alioss.gcores.com/uploads/image/71c2eece-911e-459e-8377-635c2f8bce58_watermark.png"));
        titles.add(new String("4444444444444444"));


        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);

        banner.setOnBannerListener(this);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();



    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getApplicationContext(),"你点击了："+position,Toast.LENGTH_SHORT).show();
    }
}
