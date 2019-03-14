package activitytext.example.com.demo09.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import activitytext.example.com.demo09.R;
import activitytext.example.com.demo09.bean.Model;

public class HomeAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {
//    private final int HEADER_TYPE = 1;
//    private final int TEXT_TYPE = 1;
    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List<Model> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        //可链式调用赋值
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .setImageResource(R.id.iv_img, R.mipmap.ic_launcher);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }


}
