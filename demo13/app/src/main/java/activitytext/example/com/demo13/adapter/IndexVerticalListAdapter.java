package activitytext.example.com.demo13.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import activitytext.example.com.demo13.R;
import activitytext.example.com.demo13.bean.IndexVertical;

public class IndexVerticalListAdapter extends BaseQuickAdapter<IndexVertical, BaseViewHolder> {
    public IndexVerticalListAdapter(@LayoutRes int layoutResId, @Nullable List<IndexVertical> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, IndexVertical item) {
        //可链式调用赋值
        helper.setText(R.id.index_vertical_list_title, item.getTitle())
                .setText(R.id.index_vertical_list_introduce, item.getIntroduce())
                .setImageResource(R.id.index_vertical_list_image, R.mipmap.ic_launcher);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }


}
