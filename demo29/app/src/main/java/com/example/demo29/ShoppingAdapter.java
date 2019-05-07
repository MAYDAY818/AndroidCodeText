package com.example.demo29;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ShoppingAdapter extends BaseQuickAdapter<Shopping,BaseViewHolder> {

    public ShoppingAdapter(int layoutResId, @Nullable List<Shopping> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shopping shopping) {
        //可链式调用赋值
        helper.setText(R.id.tv_title, shopping.getInfo())
                .setText(R.id.tv_content, shopping.getPrice())
                .addOnClickListener(R.id.item_button);
        Glide.with(mContext).load(shopping.getImg()).into((ImageView) helper.getView(R.id.iv_img));

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}
