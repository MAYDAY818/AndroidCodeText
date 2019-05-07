package com.example.demo29;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<Shopping> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment2, container, false);
        recyclerView = view.findViewById(R.id.recycler_view2);
        data = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase
                .openOrCreateDatabase("/data/data/com.example.demo29/databases/shopping.db", null);
        // 查询获得游标
        Cursor cursor = db.query ("COMPANY",null,null,null,null,null,null);
        // 判断游标是否为空
        if(cursor.moveToFirst()) {
            // 遍历游标
            do {
                Shopping s= new Shopping();
                s.setImg(cursor.getString(cursor.getColumnIndex("IMG")));
                s.setInfo(cursor.getString(cursor.getColumnIndex("INFO")));
                s.setPrice(cursor.getString(cursor.getColumnIndex("ID")));
                data.add(s);
                Log.e(TAG, "onCreateView: "+s.toString() );
            } while (cursor.moveToNext());
        }

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(R.layout.cart, data);
        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
