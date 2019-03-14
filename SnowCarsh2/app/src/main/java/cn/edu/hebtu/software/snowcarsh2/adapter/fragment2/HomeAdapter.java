package cn.edu.hebtu.software.snowcarsh2.adapter.fragment2;





import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.bean.say;


public class HomeAdapter extends BaseQuickAdapter<say,BaseViewHolder> {

    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, say item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.date, item.getDate());
        helper.setText(R.id.content,item.getContent());

        helper.setImageResource(R.id.icon_image, item.getIcon());
        helper.setImageResource(R.id.img1, item.getA1());
        helper.setImageResource(R.id.img2, item.getA2());
        helper.setImageResource(R.id.img3, item.getA3());


        // 加载网络图片
        //Glide.with(mContext).load(item.getTitle()).into((ImageView) helper.getView(R.id.img));


    }

}
