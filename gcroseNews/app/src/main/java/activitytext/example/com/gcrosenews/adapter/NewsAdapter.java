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

public class NewsAdapter extends BaseQuickAdapter<New,BaseViewHolder> {
    public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<New> news) {
        super(layoutResId, news);
    }

    @Override
    protected void convert(BaseViewHolder helper, New item) {
        helper.setText(R.id.tv_title,item.getNewsTitle());
        helper.setText(R.id.tv_content,item.getDesc());
        Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_img));
    }

}
