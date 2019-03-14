package activitytext.example.com.demo19;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Animator animator;
    private TextView textView;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.t1);
        button1=findViewById(R.id.b1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        loadXmlAnimator();
    }
    //加载XML动画并关联应用动画的对象
    private void loadXmlAnimator(){
        //1. 加载补间动画
        Animation animation = AnimationUtils.loadAnimation(
                getApplicationContext(),
                R.anim.tween_animation
        );
        //2. 关联补间动画和图像控件并启动动画播放
        textView.startAnimation(animation);

    }
}
