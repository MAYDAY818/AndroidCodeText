package com.example.demo29;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

public class Fragment1 extends Fragment {

    private RecyclerView recyclerView;
    private ShoppingAdapter adapter;
    private Handler handler;
    private List<Shopping> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, container, false);
        Thread thread = new Thread();
        //初始化RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        data = new ArrayList<>();
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器





        new java.lang.Thread(thread).start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    adapter = new ShoppingAdapter(R.layout.item_rv, data);
                    //给RecyclerView设置适配器
                    recyclerView.setAdapter(adapter);
                    Log.e(TAG, "handleMessage: " );



                    //条目点击事件
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getActivity(),ShoppingDetails.class);
                            Shopping shopping1=data.get(position);
                            intent.putExtra("price", shopping1.getPrice());
                            intent.putExtra("info", shopping1.getInfo());
                            intent.putExtra("img", shopping1.getImg());
                            startActivity(intent);
                        }
                    });
                    //条目子控件点击事件
                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            Toast.makeText(getContext(), "已加入购物车", Toast.LENGTH_SHORT).show();
                            Shopping shopping2=data.get(position);
                            File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/databases/");
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            SQLiteDatabase db = SQLiteDatabase
                                    .openOrCreateDatabase("/data/data/com.example.demo29/databases/shopping.db", null);
                            // 创建表SQL语句
                            String stu_table ="CREATE TABLE COMPANY("
                                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + "IMG TEXT , INFO TEXT ,"
                                    + "PRICE INT )";
                            // 执行SQL语句
                            db.execSQL(stu_table);

                            ContentValues cValue = new ContentValues();
                            cValue.put("IMG",shopping2.getImg());
                            cValue.put("INFO", shopping2.getInfo());
                            cValue.put("PRICE", shopping2.getPrice());
                            db.insert("COMPANY", null, cValue);

                        }
                    });
                }
            }
        };
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    class Thread implements Runnable {
        @Override
        public void run() {
            StringBuilder r = new StringBuilder();
            URL url = null;
            try {
                url = new URL("http://120.79.80.250:8080/mysqltext5/b");
                //URL url = new URL(r.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType", "UTF-8");
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                JSONArray array = new JSONArray(res);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    Shopping n = new Shopping(object.getInt("id"), object.getString("img"), object.getString("info"), object.getString("price"), object.getString("uri"));
                    Log.e(TAG, "run: "+object.getInt("id")+object.getString("img")+object.getString("info")+object.getString("price")+object.getString("uri") );
                    data.add(n);
                }

                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
