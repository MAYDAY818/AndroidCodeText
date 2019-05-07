package com.example.demo29;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;
import com.bumptech.glide.Glide;

public class ShoppingDetails extends AppCompatActivity {

    private ImageView imageView;
    private TextView price;
    private TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        imageView=findViewById(R.id.img);
        price=(TextView) findViewById(R.id.price);
        info=findViewById(R.id.info);
        Intent intent = getIntent();
        Glide.with(this).load(intent.getStringExtra("img")).into(imageView);
        Log.e(TAG, "onCreate: "+intent.getStringExtra("img") );
        info.setText(intent.getStringExtra("info"));
        Log.e(TAG, "onCreate: "+intent.getStringExtra("info") );
        price.setText(intent.getStringExtra("price"));
        Log.e(TAG, "onCreate: "+intent.getStringExtra("price") );
    }
}
