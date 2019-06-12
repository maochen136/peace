package cn.edu.hebtu.software.peace.fragments;


import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldoublem.thumbUplib.ThumbUpView;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.utils.LetterSpacingTextView;

public class CalendarFragment5 extends Fragment {
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
    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar1, null);
        calendar_sentence=view.findViewById(R.id.calendar_sentence);
        linearLayout=view.findViewById(R.id.calendar_layout1);
        myear=view.findViewById(R.id.year);
        mmonth=view.findViewById(R.id.month);
        mday=view.findViewById(R.id.day);
        linearLayout.setBackground(getResources().getDrawable(R.drawable.calendar_background5));
//        日期
        Calendar c = Calendar.getInstance();
        int my = c.get(Calendar.YEAR); // 获取当前年份
        int mm = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int md = c.get(Calendar.DAY_OF_MONTH)-4;// 获取当日期
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
        authortext.setText("爱在日落黄昏时");

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(3000);
        authortext.setAnimation(aa);
        calendar_sentence.startAnimation(aa);
        s = "你不能取代任何人，因为每个人都有他美丽又独特的细微之处";
        calendar_sentence.setSpacing(10);
        calendar_sentence.setText(s);
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