package cn.edu.hebtu.software.read.fragment;

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

import cn.edu.hebtu.software.read.R;
import cn.edu.hebtu.software.read.adapter.VideoAdapter;
import cn.edu.hebtu.software.read.bean.Video;

public class VideoFragment extends ListFragment {
    @Nullable
    private ListView list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Video video1 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video2 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video3 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video4 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video5 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);
        Video video6 = new Video(R.drawable.radio1,"《驯龙高手3》即将上线","22'12",54,4);


        List<Video> videos = new ArrayList<>();
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);
        videos.add(video6);
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), R.layout.activity_video,videos);
        setListAdapter(videoAdapter);
        View view = inflater.inflate(R.layout.fragment_layout_video,
                container,
                false);
        list = view.findViewById(android.R.id.list);
        return view;
    }
}
