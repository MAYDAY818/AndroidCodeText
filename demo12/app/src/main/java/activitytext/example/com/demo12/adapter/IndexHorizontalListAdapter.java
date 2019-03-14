package activitytext.example.com.demo12.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import activitytext.example.com.demo12.R;
import activitytext.example.com.demo12.bean.IndexHorizontalList;

public class IndexHorizontalListAdapter extends BaseQuickAdapter<IndexHorizontalList, BaseViewHolder> {

    public IndexHorizontalListAdapter(@LayoutRes int layoutResId, @Nullable List<IndexHorizontalList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexHorizontalList item) {
        //可链式调用赋值
        helper.setText(R.id.index_horizontal_list_title, item.getTitle())
                .setText(R.id.index_horizontal_list_introduxe, item.getIntroduce())
                //图片连接,以后设置
                .setImageResource(R.id.index_horizontal_list_image, R.mipmap.ic_launcher);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }


}