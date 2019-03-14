package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.fragment3.BaseRecycleAdapter;
import cn.edu.hebtu.software.snowcarsh2.adapter.fragment3.SeachRecordAdapter;
import cn.edu.hebtu.software.snowcarsh2.db.fragment3.DbDao;

public class fragment3 extends Fragment {
    private String[] mStrs = {"主流孩子", "王者荣耀", "刺激战场", "破解"};
    private SearchView mSearchView;
    private ListView mListView;
    private RecyclerView mRecyclerView;
    private TextView mtv_deleteAll;
    private SeachRecordAdapter mAdapter;
    private DbDao mDbDao;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment3,container,false);
        mSearchView = (SearchView) view.findViewById(R.id.searchView);
        mListView = (ListView) view.findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);
        initViews();

        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() != 0) {
                    boolean hasData = mDbDao.hasData(query.trim());
                    if (!hasData) {
                        mDbDao.insertData(query.trim());
                    } else {

                    }

                    //
                    mAdapter.updata(mDbDao.queryData(""));

                } else {

                }
                return false;
            }


            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    mListView.setFilterText(newText);
                } else {
                    mListView.clearTextFilter();
                }
                return false;
            }
        });
        return view ;
    }
    private void initViews() {
        mDbDao =new DbDao(getContext());
        mtv_deleteAll = (TextView) view.findViewById(R.id.tv_deleteAll);
        mtv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbDao.deleteData();
                mAdapter.updata(mDbDao.queryData(""));
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter =new SeachRecordAdapter(mDbDao.queryData(""),getContext());
        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                mDbDao.delete(mDbDao.queryData("").get(position));

                mAdapter.updata(mDbDao.queryData(""));
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }
}
