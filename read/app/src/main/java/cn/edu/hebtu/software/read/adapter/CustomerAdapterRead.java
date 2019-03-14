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
import cn.edu.hebtu.software.read.bean.DataRead;

public class CustomerAdapterRead extends BaseAdapter {

    private Context context;
    private int itemLayout;
    private List<DataRead> reads = new ArrayList<>();

    public CustomerAdapterRead(Context context, int itemLayout, List<DataRead> reads) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.reads = reads;
    }

    @Override
    public int getCount() {
        return reads.size();
    }

    @Override
    public Object getItem(int position) {
        return reads.get(position);
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

        TextView tvRead = convertView.findViewById(R.id.tv_read);
        tvRead.setText(reads.get(position).getTitle());
        ImageView ivPic = convertView.findViewById(R.id.iv_pic);
        ivPic.setImageResource(reads.get(position).getPic());
        TextView tvLove = convertView.findViewById(R.id.tv_love);
        tvLove.setText(reads.get(position).getLove()+"");
        TextView tvSay = convertView.findViewById(R.id.tv_say);
        tvSay.setText(reads.get(position).getSay()+"");

        return convertView;

    }
}
