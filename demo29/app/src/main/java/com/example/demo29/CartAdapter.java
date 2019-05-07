package com.example.demo29;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CartAdapter extends BaseQuickAdapter<Shopping, BaseViewHolder> {
    public CartAdapter(int layoutResId, @Nullable List<Shopping> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shopping shopping) {
        //可链式调用赋值
        helper.setText(R.id.cart_title, shopping.getInfo())
                .setText(R.id.cart_content, shopping.getPrice());
        Glide.with(mContext).load(shopping.getImg()).into((ImageView) helper.getView(R.id.cart_img));

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
