package activitytext.example.com.demo16;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    private Button btnRect;
    private Button btnLine;
    private Button btnLineText;
    private Button btnWuGui;
    private ImageView imageView;
    private DrawLinsenter linsenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRect=findViewById(R.id.juxing);
        btnLine=findViewById(R.id.wujiaoxing);
        btnLineText=findViewById(R.id.xiantiaowenzi);
        btnWuGui=findViewById(R.id.wugui);
        imageView=findViewById(R.id.image);

        linsenter=new DrawLinsenter();
        btnRect.setOnClickListener(linsenter);
        btnLine.setOnClickListener(linsenter);
        btnLineText.setOnClickListener(linsenter);
        btnWuGui.setOnClickListener(linsenter);

    }
    class DrawLinsenter implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.juxing:
                    //1.实例化bitmap
                    Bitmap rectBitmap = Bitmap.createBitmap(600,600, Bitmap.Config.ARGB_8888);
                    //2.实例化canvas
                    Canvas canvas = new Canvas(rectBitmap);
                    //3.实例化paint
                    Paint rectPaint = new Paint();
                    //4.设置绘画参数
                    rectPaint.setColor(Color.RED);
                    //5.画图
                    canvas.drawRect(100,100,500,500,rectPaint);
                    //6.把图像显示在图像控件中
                    imageView.setImageBitmap(rectBitmap);
                    break;
                case R.id.wujiaoxing:
                    //1.实例化bitmap
                    Bitmap lineBitmap = Bitmap.createBitmap(600,600, Bitmap.Config.ARGB_8888);
                    //2.实例化canvas
                    Canvas lineCanvas = new Canvas(lineBitmap);
                    //3.实例化paint
                    Paint linePaint = new Paint();
                    linePaint.setARGB(255,0,0,0);
                    //不填充
                    linePaint.setStyle(Paint.Style.STROKE);
                    //设置线宽
                    linePaint.setStrokeWidth(4);
                    //设置线条虚线
                    linePaint.setPathEffect(new DashPathEffect(new float[]{2,3},3));
                    //4.实例化path
                    Path path = new Path();
                    path.moveTo(100,0);
                    path.lineTo(41,181);
                    path.lineTo(195,69);
                    path.lineTo(5,69);
                    path.lineTo(159,181);
                    path.lineTo(100,0);
                    //5.画图
                    lineCanvas.drawPath(path,linePaint);
                    //6.把图像显示在图像控件中
                    imageView.setImageBitmap(lineBitmap);
                    break;
                case R.id.xiantiaowenzi:
                    //1.实例化bitmap
                    Bitmap lineTextBitmap = Bitmap.createBitmap(600,600, Bitmap.Config.ARGB_8888);
                    //2.实例化canvas
                    Canvas lineTextCanvas = new Canvas(lineTextBitmap);
                    //3.实例化paint
                    Paint lineTextPaint = new Paint();
                    lineTextPaint.setStrokeWidth(5);
                    lineTextPaint.setColor(Color.BLUE);
                    lineTextPaint.setStyle(Paint.Style.STROKE);
                    //4.实例化path
                    Path lineTextPath = new Path();
                    //左上角坐标,右下角坐标,绘图函数逆时针
                    lineTextPath.addOval(100,100,500,400,Path.Direction.CCW);
                    //设置字体大小
                    lineTextPaint.setTextSize(40);
                    //5.画图
                    //画椭圆
                    lineTextCanvas.drawPath(lineTextPath,lineTextPaint);
                    //画字
                    lineTextCanvas.drawTextOnPath("aaaaaaaaaaaaaa",lineTextPath,30,30,lineTextPaint);
                    //6.把图像显示在图像控件中
                    imageView.setImageBitmap(lineTextBitmap);
                    break;
                case R.id.wugui:
                    //1.实例化bitmap
                    Bitmap wuBitmap = Bitmap.createBitmap(600,700, Bitmap.Config.ARGB_8888);
                    //2.实例化canvas
                    Canvas wuCanvas = new Canvas(wuBitmap);
                    //3.实例化paint
                    Paint wuPaint = new Paint();
                    wuPaint.setColor(Color.GREEN);
                    //4.实例化path
                    Path wuPath = new Path();
                    //左上角坐标,右下角坐标,绘图函数逆时针
                    wuPath.addOval(550,120,420,240,Path.Direction.CW);
                    wuPath.addOval(100,100,500,600,Path.Direction.CCW);
                    wuPath.addOval(50,120,180,240,Path.Direction.CCW);
                    wuPath.addOval(50,430,180,550,Path.Direction.CCW);
                    wuPath.addOval(550,430,420,550,Path.Direction.CW);
                    RectF oval = new RectF();
                    oval.set(250,50,350,150);
                    wuCanvas.drawArc(oval, 180, 180, true, wuPaint);
                    wuPath.moveTo(260, 600);
                    wuPath.lineTo(340, 600);
                    wuPath.lineTo(300, 700);
                    wuPath.close();
                    //绘制path路径
                    //5.画图
                    //画椭圆
                    wuCanvas.drawPath(wuPath,wuPaint);
                    //6.把图像显示在图像控件中
                    imageView.setImageBitmap(wuBitmap);
                    break;
            }
        }
    }
}
