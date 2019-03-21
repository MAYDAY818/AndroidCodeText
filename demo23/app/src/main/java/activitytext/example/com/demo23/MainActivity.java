package activitytext.example.com.demo23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Button btnImg;
    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnImg=findViewById(R.id.btnImg);
        ivImg=findViewById(R.id.ivImg);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myUrl = "https://alioss.gcores.com/uploads/focus_image/94d3c33c-ab3b-4e4b-bea9-0033ff21a801.jpg";
                Glide.with(MainActivity.this).load(myUrl).into(ivImg);
            }
        });
    }
}
