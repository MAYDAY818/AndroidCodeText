package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment5.VideoAdapter;
import cn.edu.hebtu.software.snowcarsh2.bean.Video;


public class ChildrenFragment5 extends ListFragment {
    public static ChildrenFragment5 newInstance() {
        ChildrenFragment5 fragment = new ChildrenFragment5();
        return fragment;

    }
    @Nullable
    private ListView list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Video video1 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video2 = new Video(R.drawable.radio2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video3 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video4 = new Video(R.drawable.radio2,"《驯龙高手3》即将上线","22'12",54,4);
        Video video5 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video6 = new Video(R.drawable.radio2,"《驯龙高手3》即将上线","22'12",54,4);


        List<Video> videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);
        videos.add(video6);
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), R.layout.childrenfragment5_item,videos);
        setListAdapter(videoAdapter);
        View view = inflater.inflate(R.layout.childrenfragment5,
                container,
                false);
        list = view.findViewById(android.R.id.list);
        return view;
    }
}
