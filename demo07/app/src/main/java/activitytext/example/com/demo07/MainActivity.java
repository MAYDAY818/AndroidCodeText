package activitytext.example.com.demo07;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private XBanner banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner = (XBanner) findViewById(R.id.xbanner);

        List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        // 为XBanner绑定数据
        banner .setData(imgesUrl,null);//第二个参数为提示文字资源集合
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(MainActivity.this).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效，选择一个即可，总的大概就这么多效果啦，欢迎使用
        banner.setPageTransformer(Transformer.Default);//横向移动

        banner.setPageTransformer(Transformer.Alpha); //渐变，效果不明显

        banner.setPageTransformer(Transformer.Rotate);  //单页旋转

        banner.setPageTransformer(Transformer.Cube);    //立体旋转

        banner.setPageTransformer(Transformer.Flip);  // 反转效果

        banner.setPageTransformer(Transformer.Accordion); //三角换页

        banner.setPageTransformer(Transformer.ZoomFade); // 缩小本页，同时放大另一页

        banner.setPageTransformer(Transformer.ZoomCenter); //本页缩小一点，另一页就放大

        banner.setPageTransformer(Transformer.ZoomStack); // 本页和下页同事缩小和放大

        banner.setPageTransformer(Transformer.Stack);  //本页和下页同时左移

        banner.setPageTransformer(Transformer.Depth);  //本页左移，下页从后面出来

        banner.setPageTransformer(Transformer.Zoom);  //本页刚左移，下页就在后面
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);


    }

    private static final String TAG = "MainActivity";
}
