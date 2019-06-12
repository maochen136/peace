package cn.edu.hebtu.software.peace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

public class IndexActivity extends AppCompatActivity {
    private LinearLayout index_layout;
    private TextView indexSign;
    private ImageView indeximg1;
    private ImageView indeximg2;
    private ImageView indeximg3;
    private ImageView indeximg4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        //隐藏状态栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_index);
        InitView();

    }

    private void InitView(){
        index_layout=findViewById(R.id.index_layout);
        indexSign=findViewById(R.id.index_sign);
        indeximg1=findViewById(R.id.index_img1);
        indeximg2=findViewById(R.id.index_img2);
        indeximg3=findViewById(R.id.index_img3);
        indeximg4=findViewById(R.id.index_img4);

        index_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indexintent=new Intent();
                indexintent.setClass(IndexActivity.this,CalenderActivity.class);
                startActivity(indexintent);
            }
        });
        indeximg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sleepintent=new Intent();
                sleepintent.setClass(IndexActivity.this,SleepActivity.class);
                startActivity(sleepintent);
            }
        });

        getDate(indexSign);
        rectRoundBitmap();
    }

    private void getDate(TextView tv){
        Date d = new Date();

        if(d.getHours()<7){
            tv.setText("晨曦中");
        }else if(d.getHours()<11){
            tv.setText("上午好");
        }else if(d.getHours()<13){
            tv.setText("中午好");
        }else if(d.getHours()<17){
            tv.setText("下午好");
        }else if(d.getHours()<19){
            tv.setText("傍晚好");
        }else if(d.getHours()<24){
            tv.setText("晚上好");
        }
    }
    private void rectRoundBitmap(){
        //得到资源文件的BitMap
        Bitmap image1= BitmapFactory.decodeResource(getResources(),R.drawable.index11);
        Bitmap image2= BitmapFactory.decodeResource(getResources(),R.drawable.index22);
        Bitmap image3= BitmapFactory.decodeResource(getResources(),R.drawable.index33);
        Bitmap image4= BitmapFactory.decodeResource(getResources(),R.drawable.index44);
        //创建RoundedBitmapDrawable对象
        RoundedBitmapDrawable roundImg1 = RoundedBitmapDrawableFactory.create(getResources(),image1);
        RoundedBitmapDrawable roundImg2 = RoundedBitmapDrawableFactory.create(getResources(),image2);
        RoundedBitmapDrawable roundImg3 = RoundedBitmapDrawableFactory.create(getResources(),image3);
        RoundedBitmapDrawable roundImg4 = RoundedBitmapDrawableFactory.create(getResources(),image4);
        //抗锯齿
        roundImg1.setAntiAlias(true);
        roundImg2.setAntiAlias(true);
        roundImg3.setAntiAlias(true);
        roundImg4.setAntiAlias(true);
        //设置圆角半径
        roundImg1.setCornerRadius(50);
        roundImg2.setCornerRadius(50);
        roundImg3.setCornerRadius(50);
        roundImg4.setCornerRadius(50);
        //设置显示图片
        indeximg1.setImageDrawable(roundImg1);
        indeximg2.setImageDrawable(roundImg2);
        indeximg3.setImageDrawable(roundImg3);
        indeximg4.setImageDrawable(roundImg4);
    }

}
