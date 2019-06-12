package cn.edu.hebtu.software.peace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.utils.MusicService1;
import cn.edu.hebtu.software.peace.utils.MusicService2;
import cn.edu.hebtu.software.peace.utils.MusicService3;
import cn.edu.hebtu.software.peace.utils.MusicService4;

public class WakeSpotActivity extends AppCompatActivity {
    private ImageView spotimg1;
    private ImageView spotimg2;
    private ImageView spotimg3;
    private ImageView spotimg4;
    private ImageView spotimg5;
    private ImageView spotimg6;

    private ImageView b1;
    private boolean flag1=true;
    private ImageView b2;
    private boolean flag2=true;
    private ImageView b3;
    private boolean flag3=true;
    private ImageView b4;
    private boolean flag4=true;
    private ImageView b5;
    private boolean flag5=true;
    private ImageView b6;
    private boolean flag6=true;

    private TextView btn1;
    private TextView btn2;
    private TextView btn3;
    private TextView btn4;
    private TextView btn5;
    private TextView btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_spot);

        b1=findViewById(R.id.b1);
        b1.setImageResource(R.drawable.bofang);
        final Intent intent1=new Intent(WakeSpotActivity.this,MusicService1.class);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1==true){

                    startService(intent1);
                    b1.setImageResource(R.drawable.tz);
                    flag1=false;

                }else{
                    stopService(intent1);
                    b1.setImageResource(R.drawable.bofang);
                    flag1=true;
                }



            }
        });
        btn1=findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn1.getText());
                startActivity(intent);
            }
        });

        b2=findViewById(R.id.b2);
        b2.setImageResource(R.drawable.bofang);
        final Intent intent2=new Intent(WakeSpotActivity.this,MusicService2.class);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==true){

                    startService(intent2);
                    b2.setImageResource(R.drawable.tz);
                    flag2=false;

                }else{
                    stopService(intent2);
                    b2.setImageResource(R.drawable.bofang);
                    flag2=true;
                }
            }
        });
        btn2=findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn2.getText());
                startActivity(intent);
            }
        });
        b3=findViewById(R.id.b3);
        b3.setImageResource(R.drawable.bofang);
        final Intent intent3=new Intent(WakeSpotActivity.this,MusicService3.class);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag3==true){

                    startService(intent3);
                    b3.setImageResource(R.drawable.tz);
                    flag3=false;

                }else{
                    stopService(intent3);
                    b3.setImageResource(R.drawable.bofang);
                    flag3=true;
                }



            }
        });
        btn3=findViewById(R.id.btn_3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn3.getText());
                startActivity(intent);
            }
        });
        b4=findViewById(R.id.b4);
        b4.setImageResource(R.drawable.bofang);
        final Intent intent4=new Intent(WakeSpotActivity.this,MusicService4.class);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag4==true){

                    startService(intent4);
                    b4.setImageResource(R.drawable.tz);
                    flag4=false;

                }else{
                    stopService(intent4);
                    b4.setImageResource(R.drawable.bofang);
                    flag4=true;
                }



            }
        });
        btn4=findViewById(R.id.btn_4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn4.getText());
                startActivity(intent);
            }
        });
        b5=findViewById(R.id.b5);
        b5.setImageResource(R.drawable.bofang);
        final Intent intent5=new Intent(WakeSpotActivity.this,MusicService3.class);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag5==true){

                    startService(intent5);
                    b5.setImageResource(R.drawable.tz);
                    flag5=false;

                }else{
                    stopService(intent5);
                    b5.setImageResource(R.drawable.bofang);
                    flag5=true;
                }



            }
        });
        btn5=findViewById(R.id.btn_5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn5.getText());
                startActivity(intent);
            }
        });
        b6=findViewById(R.id.b6);
        b6.setImageResource(R.drawable.bofang);
        final Intent intent6=new Intent(WakeSpotActivity.this,MusicService2.class);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag6==true){

                    startService(intent6);
                    b1.setImageResource(R.drawable.tz);
                    flag6=false;

                }else{
                    stopService(intent6);
                    b6.setImageResource(R.drawable.bofang);
                    flag6=true;
                }



            }
        });
        btn6=findViewById(R.id.btn_6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WakeSpotActivity.this,test.class);
                intent.putExtra("data",btn6.getText());
                startActivity(intent);
            }
        });







        InitView();
        rectRoundBitmap();
    }
    private void InitView(){
        spotimg1=findViewById(R.id.index_img1);
        spotimg2=findViewById(R.id.index_img2);
        spotimg3=findViewById(R.id.index_img3);
        spotimg4=findViewById(R.id.index_img4);
        spotimg5=findViewById(R.id.index_img5);
        spotimg6=findViewById(R.id.index_img6);
    }
    private void rectRoundBitmap(){
        //得到资源文件的BitMap
        Bitmap image1= BitmapFactory.decodeResource(getResources(),R.drawable.indexbtn3);
        Bitmap image2= BitmapFactory.decodeResource(getResources(),R.drawable.b2);
        Bitmap image3= BitmapFactory.decodeResource(getResources(),R.drawable.b3);
        Bitmap image4= BitmapFactory.decodeResource(getResources(),R.drawable.b4);
        Bitmap image5= BitmapFactory.decodeResource(getResources(),R.drawable.b5);
        Bitmap image6= BitmapFactory.decodeResource(getResources(),R.drawable.b6);
        //创建RoundedBitmapDrawable对象
        RoundedBitmapDrawable roundImg1 = RoundedBitmapDrawableFactory.create(getResources(),image1);
        RoundedBitmapDrawable roundImg2 = RoundedBitmapDrawableFactory.create(getResources(),image2);
        RoundedBitmapDrawable roundImg3 = RoundedBitmapDrawableFactory.create(getResources(),image3);
        RoundedBitmapDrawable roundImg4 = RoundedBitmapDrawableFactory.create(getResources(),image4);
        RoundedBitmapDrawable roundImg5 = RoundedBitmapDrawableFactory.create(getResources(),image5);
        RoundedBitmapDrawable roundImg6 = RoundedBitmapDrawableFactory.create(getResources(),image6);
        //抗锯齿
        roundImg1.setAntiAlias(true);
        roundImg2.setAntiAlias(true);
        roundImg3.setAntiAlias(true);
        roundImg4.setAntiAlias(true);
        roundImg5.setAntiAlias(true);
        roundImg6.setAntiAlias(true);
        //设置圆角半径
        roundImg1.setCornerRadius(30);
        roundImg2.setCornerRadius(30);
        roundImg3.setCornerRadius(30);
        roundImg4.setCornerRadius(30);
        roundImg5.setCornerRadius(30);
        roundImg6.setCornerRadius(30);
        //设置显示图片
        spotimg1.setImageDrawable(roundImg1);
        spotimg2.setImageDrawable(roundImg2);
        spotimg3.setImageDrawable(roundImg3);
        spotimg4.setImageDrawable(roundImg4);
        spotimg5.setImageDrawable(roundImg5);
        spotimg6.setImageDrawable(roundImg6);
    }
    public  Bitmap getBlackImage(Bitmap bm){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight()/2,Bitmap.Config.RGB_565);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(bmp);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bm,0,0,paint);
        canvas.drawColor(Color.parseColor("#40000000"));
        return bmp;

    }

}
