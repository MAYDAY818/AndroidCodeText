package activitytext.example.com.demo22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnPost;
    private Button btnSet;
    private TextView tvMsg;
    private EventOnClickLisenter event;
    private EventBus eventBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReg=findViewById(R.id.btnReg);
        btnPost=findViewById(R.id.btnPost);
        btnSet=findViewById(R.id.btnSet);
        tvMsg=findViewById(R.id.tvMsg);
        event=new EventOnClickLisenter();
        btnReg.setOnClickListener(event);
        btnPost.setOnClickListener(event);
        btnSet.setOnClickListener(event);
        eventBus=EventBus.getDefault();
    }
    class EventOnClickLisenter implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnReg:
                    //注册事件
                    if(!eventBus.isRegistered(MainActivity.this)){
                        eventBus.register(MainActivity.this);
                    }
                    break;
                case R.id.btnPost:
                    //发布事件
                    eventBus.post(new MessageEvent("四十二"));

                    break;
                case R.id.btnSet:
                    Intent intent=new Intent(

                    );


//                    //取消注册
//                    if(eventBus.isRegistered(MainActivity.this)){
//                        eventBus.unregister(MainActivity.this);
//                    }
                    break;
            }
        }
    }
    //当前方法在主线程执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        tvMsg.setText(messageEvent.getMessage().toString());
    }
}
