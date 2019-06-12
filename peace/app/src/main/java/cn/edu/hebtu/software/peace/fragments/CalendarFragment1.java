package cn.edu.hebtu.software.peace.fragments;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldoublem.thumbUplib.ThumbUpView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.utils.LetterSpacingTextView;
import cn.edu.hebtu.software.peace.utils.TextUtil;

public class CalendarFragment1 extends Fragment {
    private LetterSpacingTextView calendar_sentence;
    private String s;

    private LinearLayout linearLayout;
    private TextView myear;
    private TextView mmonth;
    private TextView mday;
    private String yue;
    private TextView authortext;

    private ThumbUpView zan;
    private TextView zan_num;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar1, null);
        calendar_sentence=view.findViewById(R.id.calendar_sentence);
        linearLayout=view.findViewById(R.id.calendar_layout1);
        myear=view.findViewById(R.id.year);
        mmonth=view.findViewById(R.id.month);
        mday=view.findViewById(R.id.day);
        linearLayout.setBackground(getResources().getDrawable(R.drawable.calendar_background1));
//        日期
        Calendar c = Calendar.getInstance();
        int my = c.get(Calendar.YEAR); // 获取当前年份
        int mm = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int md = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        System.out.println(my);
        System.out.println(mm);
        System.out.println(md);
        myear.setText(""+my);
        switch (mm){
            case 1:
                yue="Jan.";
                break;
            case 2:
                yue="Feb.";
                break;
            case 3:
                yue="Mar.";
                break;
            case 4:
                yue="Apr.";
                break;
            case 5:
                yue="May";
                break;
            case 6:
                yue="Jun.";
                break;
            case 7:
                yue="Jul";
                break;
            case 8:
                yue="Aug.";
                break;
            case 9:
                yue="Sep";
                break;
            case 10:
                yue="Oct";
                break;
            case 11:
                yue="Nov.";
                break;
            case 12:
                yue="Dec";
                break;
        }
        mmonth.setText(yue);
        mday.setText("0"+md);
        authortext=view.findViewById(R.id.author);
        authortext.setText("泰戈尔");

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(3000);

        calendar_sentence.setSpacing(10);
        calendar_sentence.setText(s);
        authortext.setAnimation(aa);

        authortext.startAnimation(aa);
        calendar_sentence.startAnimation(aa);
        s = "信念是鸟，它在黎明仍然黑暗之际，感觉到了光明。";
        calendar_sentence.setSpacing(10);
        calendar_sentence.setText(s);


//        //获取当前时间
//        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
//        t.setToNow(); // 取得系统时间。
//        int year = t.year;
//        int month = t.month+1;
//        int day = t.monthDay;
//        myear.setText(year);
//        mmonth.setText(month);
//        mday.setText(day);

        zan = view.findViewById(R.id.zan);
        zan.setUnLikeType(ThumbUpView.LikeType.broken);
        zan.setCracksColor(Color.WHITE);
        zan.setFillColor(Color.rgb(255, 106, 106));
        zan.setEdgeColor(Color.rgb(205, 201, 201));
        zan_num = view.findViewById(R.id.zan_num);

        zan.setOnThumbUp(new ThumbUpView.OnThumbUp() {
            @Override
            public void like(boolean like) {
                if (like) {
                    zan_num.setText(String.valueOf(Integer.valueOf(zan_num.getText().toString()) + 1));

                } else {
                    zan_num.setText(String.valueOf(Integer.valueOf(zan_num.getText().toString()) - 1));
                }
            }
        });

        return view;

    }
    public void like(View v) {
        zan.Like();
    }

    public void unlike(View v) {
        zan.UnLike();
    }
}