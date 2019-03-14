package cn.edu.hebtu.software.read.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.read.R;
import cn.edu.hebtu.software.read.bean.Radio;

public class RadioAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<Radio> radioList = new ArrayList<>();

    public RadioAdapter(Context context, int itemLayout, List<Radio> radioList) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.radioList = radioList;
    }

    @Override
    public int getCount() {
        return radioList.size();
    }

    @Override
    public Object getItem(int position) {
        return radioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, null);
        }

        TextView tvMsg = convertView.findViewById(R.id.tv_msg);
        tvMsg.setText(radioList.get(position).getTvMsg());
        ImageView ivPic = convertView.findViewById(R.id.iv_pic);
        ivPic.setImageResource(radioList.get(position).getImgPic());
        TextView tvLike = convertView.findViewById(R.id.like);
        tvLike.setText(radioList.get(position).getLike()+"");
        TextView tvTalk = convertView.findViewById(R.id.talk);
        tvTalk.setText(radioList.get(position).getTalk()+"");

        return convertView;
    }
}
