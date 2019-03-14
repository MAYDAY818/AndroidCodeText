package com.example.dongcheng.musicplay.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dongcheng.musicplay.R;

public class ViewPagerAdapter2 extends PagerAdapter{
    private Context context;
    private String[] mDate;

    public ViewPagerAdapter2(Context context, String[] mPics) {
        this.context = context;
        this.mDate = mPics;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.viewpager_item, null);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        Glide.with(imageView.getContext()).load(R.mipmap.download).into(imageView);


        container.addView(view);//添加到父控件
        return view;
    }

    @Override
    public int getCount() {
      return mDate.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
