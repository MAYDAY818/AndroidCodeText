package activitytext.example.com.gcrosenews.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import activitytext.example.com.gcrosenews.R;
import activitytext.example.com.gcrosenews.bean.Search;

public class SearchAdapter extends BaseQuickAdapter<Search,BaseViewHolder> {
    public SearchAdapter(int layoutResId, @Nullable List<Search> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Search item) {
        helper.setText(R.id.searchTitle,item.getNewsTitle());
        helper.setText(R.id.searchInfo,item.getDesc());
    }
}
