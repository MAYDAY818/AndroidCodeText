package cn.edu.hebtu.software.level2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.level2.adapter.ContentAdapter;
import cn.edu.hebtu.software.level2.bean.content;

public class MainActivity extends AppCompatActivity {
    private List<content> contentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initContent();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        ContentAdapter adapter=new ContentAdapter(contentList);
        recyclerView.setAdapter(adapter);

    }
    public void initContent(){
        for(int i=0;i<10;i++){

            contentList.add(new content(R.drawable.radio1,"特别期待的的借口拒绝如果胡歌虎肉过后","radio"));

        }

    }
}
