package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.fragment2.HomeAdapter;
import cn.edu.hebtu.software.snowcarsh2.bean.say;

public class fragment2 extends Fragment {
    private List<say> contentList=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private HomeAdapter mMyAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment2,container,false);
        initContent();
        mRecyclerView=view.findViewById(R.id.rev);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mMyAdapter=new HomeAdapter(R.layout.fragment2_item,contentList);
        mRecyclerView.setAdapter(mMyAdapter);
        View view2= LayoutInflater.from(getContext()).inflate(R.layout.fragment2_header,mRecyclerView, false);
        mMyAdapter.addHeaderView(view2);
        return view ;
    }
    public void initContent(){
        for(int i=0;i<10;i++){
            contentList.add(new say(R.drawable.icon,"今天终于放假了","12-10",
                    "二次元美图分享！！！！！！！！！！！！！！！！！！！！！！！！！！！",
                    R.drawable.dm1,R.drawable.dm2,R.drawable.dm3));
        }
    }
}
