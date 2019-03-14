package activitytext.example.com.demo03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mob.MobSDK;
import com.mob.ums.gui.UMSGUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobSDK.init(this);
        UMSGUI.showProfilePage();
    }
}
