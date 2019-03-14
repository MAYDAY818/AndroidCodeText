package activitytext.example.com.zuoye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class HomeActivity extends AppCompatActivity {

    private LinearLayout details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        details=findViewById(R.id.home_details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在按钮响应函数中添加如下两句话就ok了
                Intent intent=new Intent(HomeActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

}
