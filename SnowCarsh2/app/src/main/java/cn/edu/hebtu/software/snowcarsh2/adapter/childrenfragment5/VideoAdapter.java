package cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.bean.Video;


public class VideoAdapter extends BaseAdapter {
    private Context context;
    private int itemLayout;
    private List<Video> videoList = new ArrayList<>();

    public VideoAdapter(Context context, int itemLayout, List<Video> videoList) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.videoList = videoList;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return videoList.get(position);
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

        TextView tvTime = convertView.findViewById(R.id.tv_time);
        tvTime.setText(videoList.get(position).getVideoTime());
        TextView tvMsg = convertView.findViewById(R.id.tv_msg);
        tvMsg.setText(videoList.get(position).getTvMsg());
        ImageView imgFirst = convertView.findViewById(R.id.iv_pic);
        imgFirst.setImageResource(videoList.get(position).getIvPic());
        TextView tvLike = convertView.findViewById(R.id.like);
        tvLike.setText(videoList.get(position).getLike()+"");
        TextView tvTalk = convertView.findViewById(R.id.talk);
        tvTalk.setText(videoList.get(position).getTalk()+"");
        return convertView;
    }
}
