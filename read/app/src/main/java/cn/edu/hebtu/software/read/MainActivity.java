package cn.edu.hebtu.software.read;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import cn.edu.hebtu.software.read.fragment.FragmentRead;
import cn.edu.hebtu.software.read.fragment.RadioFragment;
import cn.edu.hebtu.software.read.fragment.VideoFragment;


public class MainActivity extends AppCompatActivity {

    private Map<String, View> tabspecViews = new HashMap<>();
    private Map<String, ImageView> imageViewMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfrag);
        getSupportActionBar().hide();

        final FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化FragmentTabHost对象
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),
                android.R.id.tabhost);

        //创建选项卡对象
        final TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tab1")
                .setIndicator(getTabSpecView("首页",R.drawable.one, "tab1"));
        //添加选项卡
        fragmentTabHost.addTab(tabSpec1,FragmentRead.class, null);

        final TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tab2")
                .setIndicator(getTabSpecView("话题",R.drawable.like, "tab2"));
        fragmentTabHost.addTab(tabSpec2, RadioFragment.class, null);

        final TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tab3")
                .setIndicator(getTabSpecView("吉考斯",R.drawable.three,"tab3"));
        fragmentTabHost.addTab(tabSpec3, VideoFragment.class, null);

        final TabHost.TabSpec tabSpec4 = fragmentTabHost.newTabSpec("tab4")
                .setIndicator(getTabSpecView("个人",R.drawable.my,"tab4"));
        fragmentTabHost.addTab(tabSpec4,FragmentRead.class,null);

        //设置默认选中某个选项卡
        fragmentTabHost.setCurrentTab(0);
        //改变第一个选项卡图片
        ImageView imageView1 = imageViewMap.get("tab1");
        imageView1.setImageResource(R.drawable.onet);



        //监听选项卡点击事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                ImageView imageView = imageViewMap.get("tab1");
                ImageView imageView1 = imageViewMap.get("tab2");
                ImageView imageView2 = imageViewMap.get("tab3");
                ImageView imageView3 = imageViewMap.get("tab4");

                //改变选项卡中的图片
                if (tabId.equals("tab1")){

                    imageView.setImageResource(R.drawable.onet);
                    imageView1.setImageResource(R.drawable.like);
                    imageView2.setImageResource(R.drawable.three);
                    imageView3.setImageResource(R.drawable.my);
                }else if(tabId.equals("tab2")){

                    imageView.setImageResource(R.drawable.one);
                    imageView1.setImageResource(R.drawable.liket);
                    imageView2.setImageResource(R.drawable.three);
                    imageView3.setImageResource(R.drawable.my);
                }else if(tabId.equals("tab3")){

                    imageView.setImageResource(R.drawable.one);
                    imageView1.setImageResource(R.drawable.like);
                    imageView2.setImageResource(R.drawable.threet);
                    imageView3.setImageResource(R.drawable.my);
                }else {

                    imageView.setImageResource(R.drawable.one);
                    imageView1.setImageResource(R.drawable.like);
                    imageView2.setImageResource(R.drawable.three);
                    imageView3.setImageResource(R.drawable.myt);
                }

            }
        });
    }

    private View getTabSpecView(String name, int imageId, String tag){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tabspc_layout,null);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(imageId);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(name);
        tabspecViews.put(tag, view);
        imageViewMap.put(tag, imageView);
        return view;
    }
}
