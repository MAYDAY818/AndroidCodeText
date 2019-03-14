package activitytext.example.com.gcrosenews.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import activitytext.example.com.gcrosenews.R;
import activitytext.example.com.gcrosenews.bean.New;
import activitytext.example.com.gcrosenews.bean.NewsHorizontal;

public class NewsHorizontalAdapter extends BaseQuickAdapter<NewsHorizontal,BaseViewHolder> {
public NewsHorizontalAdapter(@LayoutRes int layoutResId, @Nullable List<NewsHorizontal> news) {
        super(layoutResId, news);
        }

@Override
protected void convert(BaseViewHolder helper, NewsHorizontal item) {
        helper.setText(R.id.index_horizontal_list_title,item.getNewsTitle());
        helper.setText(R.id.index_horizontal_list_desc,item.getDesc());
        Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.index_horizontal_list_image));
        }
}
