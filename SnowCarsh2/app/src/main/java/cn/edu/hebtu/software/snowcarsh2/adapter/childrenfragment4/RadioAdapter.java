package cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment4;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.bean.Radio;

public class RadioAdapter extends BaseQuickAdapter<Radio,BaseViewHolder> {
    public RadioAdapter(int layoutResId, @Nullable List<Radio> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Radio item) {
        helper.setText(R.id.radio_name, item.getTvMsg());
        //helper.setImageResource(R.id.radio_image, item.getImgPic());
        Glide.with(mContext).load(item.getImgPic()).into((ImageView) helper.getView(R.id.radio_image));
    }


}
