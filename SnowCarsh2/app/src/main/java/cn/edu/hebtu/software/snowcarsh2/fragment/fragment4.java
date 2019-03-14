package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.fragment4.fragment4Adapter;
import cn.edu.hebtu.software.snowcarsh2.bean.content;

public class fragment4 extends Fragment {
    private List<content> contentList=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private fragment4Adapter mMyAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment4,container,false);

        initContent();
        mRecyclerView=view.findViewById(R.id.rv_list);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mMyAdapter=new fragment4Adapter(R.layout.fragment4_item,contentList);
        mRecyclerView.setAdapter(mMyAdapter);
        mMyAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
       View view2= LayoutInflater.from(getContext()).inflate(R.layout.fragment4_header,mRecyclerView, false);

        mMyAdapter.addHeaderView(view2);




        return view ;
    }
    public void initContent(){
        for(int i=0;i<10;i++){
            contentList.add(new content(R.drawable.a,"时尚潮流","radio"));
            contentList.add(new content(R.drawable.b,"时尚潮流","radio"));
            contentList.add(new content(R.drawable.a,"时尚潮流","radio"));
            contentList.add(new content(R.drawable.b,"时尚潮流","radio"));
        }

    }
}
