package activitytext.example.com.demo04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import activitytext.example.com.demo04.bean.User;
import activitytext.example.com.demo04.db.UserListDao;

public class MainActivity extends AppCompatActivity {
    private Button db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = findViewById(R.id.button);
        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserListDao userListDao = new UserListDao();
                List<User> userList=userListDao.getAllUser();
                for (User user:userList){
                    Toast.makeText(MainActivity.this,user.getUserName(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
