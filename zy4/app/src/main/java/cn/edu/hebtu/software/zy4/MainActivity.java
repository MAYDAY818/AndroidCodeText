package cn.edu.hebtu.software.zy4;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {



    private static final int CODE_GALLERY_REQUEST = 0xa0;//本地
    private static final int CODE_CAMERA_REQUEST = 0xa1;//拍照
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private ImageView headImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headImage = (ImageView) findViewById(R.id.imageView);
        Button buttonLocal = (Button) findViewById(R.id.buttonLocal);
        buttonLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromGallery();
            }
        });

        Button buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseHeadImageFromCamera();
            }
        });
    }

    private void choseHeadImageFromGallery() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, CODE_GALLERY_REQUEST);
    }

    private void choseHeadImageFromCamera(){
        Intent intentCamera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentCamera,CODE_CAMERA_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        if(requestCode==CODE_CAMERA_REQUEST&resultCode==RESULT_OK){
            Bitmap pic=(Bitmap)intent.getExtras().get("data");
            headImage.setImageBitmap(pic);
        }
        if(requestCode==CODE_GALLERY_REQUEST){
            Bitmap bitmap;
            ContentResolver cr = this.getContentResolver();
            Uri uri = intent.getData();
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                headImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

}


