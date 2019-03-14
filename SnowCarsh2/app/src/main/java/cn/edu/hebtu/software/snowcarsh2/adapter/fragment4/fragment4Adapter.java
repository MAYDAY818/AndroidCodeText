package cn.edu.hebtu.software.snowcarsh2.adapter.fragment4;

import android.widget.ImageView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.bean.content;

public class fragment4Adapter extends BaseQuickAdapter<content,BaseViewHolder> {

    public fragment4Adapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, content item) {
        helper.setText(R.id.t1, item.getTitle());
        helper.setImageResource(R.id.img, item.getImage());
        // 加载网络图片
        // Glide.with(mContext).load(item.getTitle()).into((ImageView) helper.getView(R.id.img));

    }

}
