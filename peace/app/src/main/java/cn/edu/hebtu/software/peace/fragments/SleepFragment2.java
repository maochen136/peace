package cn.edu.hebtu.software.peace.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.SleepTotalAcitivty;
import cn.edu.hebtu.software.peace.StartSleepActivity;
import cn.edu.hebtu.software.peace.utils.ViewUtil;

public class SleepFragment2 extends Fragment{
    private ViewUtil viewUtil;
    private ImageView relax;
//    private ImageView ling;
//    private TextView time;
    //文字滑动
    private ViewPager mViewPager;
    private List<View> mViewList = new ArrayList<View>();//存放待滑动的view
    private LayoutInflater mInflater;
    private View view_01, view_02, view_03,view_04;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sleepfrg1,container,false);
        viewUtil = view.findViewById(R.id.wave);
        viewUtil.setInitialRadius(130);
        viewUtil.setDuration(5000);
        viewUtil.setStyle(Paint.Style.STROKE,6);
        viewUtil.setColor(Color.parseColor("#ffffff"));
        viewUtil.setInterpolator(new LinearOutSlowInInterpolator());
        viewUtil.start();
        mViewPager =view.findViewById(R.id.txtMsg);
//        ling=view.findViewById(R.id.ling);
////        time=view.findViewById(R.id.time);
//        ling.setImageResource(R.drawable.shalou);
//        time.setText("30分钟");
        relax=view.findViewById(R.id.relax);
        relax.setImageResource(R.drawable.eye);
        floatAnim(relax,1);
        relax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startsleep_intent=new Intent();
                startsleep_intent.setClass(getActivity(),StartSleepActivity.class);
                startsleep_intent.putExtra("type",mViewPager.getCurrentItem());
                startActivity(startsleep_intent);
            }
        });
//        ling.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent choosetime=new Intent();
//                choosetime.setClass(getActivity(),SleepTotalAcitivty.class);
//                startActivity(choosetime);
//            }
//        });
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent choosetime=new Intent();
//                choosetime.setClass(getActivity()  ,SleepTotalAcitivty.class);
//                startActivity(choosetime);
//            }
//        });
        initViews();
        return view;
    }
    @SuppressLint("WrongConstant")
    private void floatAnim(ImageView imageView, int delay) {
        List<Animator> animators = new ArrayList<>();

        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(imageView, "translationY", -25.0f,25.0f, -25.0f);
        translationYAnim.setDuration(3000);
        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
        translationYAnim.setRepeatMode(ValueAnimator.INFINITE);
        translationYAnim.start();
        animators.add(translationYAnim);

        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(animators);
        btnSexAnimatorSet.setStartDelay(delay);
        btnSexAnimatorSet.start();
    }
    private  void initViews(){
        mInflater = getLayoutInflater();
        view_01 = mInflater.inflate(R.layout.layout1, null);
        view_02 = mInflater.inflate(R.layout.layout2, null);
        view_03 = mInflater.inflate(R.layout.layout3, null);
        view_04 = mInflater.inflate(R.layout.layout4, null);

        mViewList.add(view_01);
        mViewList.add(view_02);
        mViewList.add(view_03);
        mViewList.add(view_04);
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter();
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setCurrentItem(0);//设置当前pager

    }
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {//返回view数量
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }
    }
}
