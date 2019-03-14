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
import cn.edu.hebtu.software.read.adapter.RadioAdapter;
import cn.edu.hebtu.software.read.bean.Radio;

public class RadioFragment extends ListFragment {
    @Nullable
    private ListView list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Radio radio1 = new Radio(R.drawable.radio1,"驯龙高手3即将上线",22,54);
        Radio radio2 = new Radio(R.drawable.radio2,"Digimon",45,78);
        Radio radio3 = new Radio(R.drawable.radio1,"驯龙高手3即将上线",6,9);
        Radio radio4 = new Radio(R.drawable.radio2,"Digimon",43,20);
        Radio radio5 = new Radio(R.drawable.radio1,"驯龙高手3即将上线",13,9);
        Radio radio6 = new Radio(R.drawable.radio2,"Digimon",7,11);

        List<Radio> radios = new ArrayList<>();
        radios.add(radio1);
        radios.add(radio2);
        radios.add(radio3);
        radios.add(radio4);
        radios.add(radio5);
        radios.add(radio6);
        RadioAdapter radioAdapter = new RadioAdapter(getActivity(), R.layout.activity_radio,radios);
        setListAdapter(radioAdapter);
        View view = inflater.inflate(R.layout.fragment_layout_radio,
                container,
                false);
        list = view.findViewById(android.R.id.list);
        return view;
    }
}
