package activitytext.example.com.zuoye;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import activitytext.example.com.zuoye.fragment.FirstFragment;
import activitytext.example.com.zuoye.fragment.SecondFragment;
import activitytext.example.com.zuoye.fragment.ThirdFramgent;

public class MainActivity extends AppCompatActivity {


    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFramgent thirdFragment;
    private Button tab1;
    private Button tab2;
    private Button tab3;
    private Button login;
    private Button home;

    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout);

        //获取视图组件对象
        findViews();
        //绑定点击事件监听器
        setListener();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFramgent();
        fragmentManager = getSupportFragmentManager();
        showFragment(firstFragment);



        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });


        home = findViewById(R.id.list);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void findViews(){
        tab1 = findViewById(R.id.tab_1);
        tab2 = findViewById(R.id.tab_2);
        tab3 = findViewById(R.id.tab_3);

    }
    private void setListener(){
        TabClickListener listener = new TabClickListener();
        tab1.setOnClickListener(listener);
        tab2.setOnClickListener(listener);
        tab3.setOnClickListener(listener);
    }
    private class TabClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tab_1:
                    showFragment(firstFragment);
                    break;
                case R.id.tab_2:
                    showFragment(secondFragment);
                    break;
                case R.id.tab_3:
                    showFragment(thirdFragment);
                    break;
            }
        }
    }
    private void showFragment(Fragment fragment){
        //创建Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment != currentFragment){
            //隐藏正在显示的fragment
            transaction.hide(currentFragment);
            //添加fragment
            if (!fragment.isAdded()) {
                transaction.add(R.id.content, fragment);
            }
            //显示fragment
            transaction.show(fragment);
            //提交事务
            transaction.commit();
            currentFragment = fragment;
        }
    }
}
