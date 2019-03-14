package activitytext.example.com.demo18;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btu;
    private Button btuJava;
    private Button btuTween;
    private Button btuTweenJava;
    private Button btnJump;
    private ImageView imageView;
    private AnimationDrawable drawableJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btu = findViewById(R.id.btnFram);
        btuJava=findViewById(R.id.btnFramJava);
        btuTween=findViewById(R.id.btnTween);
        btuTweenJava=findViewById(R.id.btnTweenJava);
        btnJump =findViewById(R.id.btnJump);
        imageView =findViewById(R.id.img);
        drawableJava = new AnimationDrawable();
        drawableJava.setOneShot(false);
        //
        drawableJava.addFrame(getResources().getDrawable(R.mipmap.img00,null),90);
        for(int i=0;i<25;i++){
            if(i<10)
                drawableJava.addFrame(getResources().getDrawable(getResources().getIdentifier("img0"+i,"mipmap",getPackageName()),null),90);
            else
                drawableJava.addFrame(getResources().getDrawable(getResources().getIdentifier("img"+i,"mipmap",getPackageName()),null),90);
        }
        imageView.setImageDrawable(drawableJava);
        btu.setOnClickListener(seton);

    }
    class seton implements View.OnClickListener{
        @Override
        public void onClick(View v) {
           switch (v.getId()) {
               case R.id.btnFram:
                   //加载动画
                   AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
                   //开始动画
                   if(drawable.isRunning()){
                       drawable.stop();
                   }else {
                       drawable.start();
                   }
                   break;
               case R.id.btnFramJava:

                   if(drawableJava.isRunning()){
                       drawableJava.stop();
                   }else {
                       drawableJava.start();
                   }
                   break;
               case R.id.btnTween:
                   //使用xml进行补间动画
                   //1.加载补间动画
                   Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.set);
                   //2.关联补间动画并启动
                   imageView.startAnimation(animation);
                   break;
               case R.id.btnTweenJava:
                   //使用java进行补间动画
                   //1.加载补间动画
                   Animation animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.set);
                   //2.关联补间动画并启动
                   imageView.startAnimation(animation);
                   break;
               case R.id.btnJump:
                   Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                   startActivity(intent);
                   overridePendingTransition(R.anim.in,R.anim.out);
                   break;
           }
        }
    }
}
