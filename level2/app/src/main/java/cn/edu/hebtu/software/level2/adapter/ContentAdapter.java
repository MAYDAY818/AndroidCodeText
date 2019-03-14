package cn.edu.hebtu.software.level2.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.hebtu.software.level2.R;
import cn.edu.hebtu.software.level2.bean.content;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private List<content> contentList;
    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext==null){
            mContext=viewGroup.getContext();
        }
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        content con=contentList.get(i);
        Glide.with(mContext).load(con.getImage()).into(viewHolder.contentImage);
        //viewHolder.contentImage.setImageResource(con.getImage());
        viewHolder.textView1.setText(con.getTitle());
        viewHolder.textView2.setText(con.getType());
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView contentImage;
        TextView textView1;
        TextView textView2;
        public ViewHolder(View view){
            super(view);

            contentImage=view.findViewById(R.id.content_image);
            textView1=view.findViewById(R.id.t1);
            textView2=view.findViewById(R.id.t2);
        }
    }

    public ContentAdapter(List<content> contentList) {
        this.contentList = contentList;
    }

}
