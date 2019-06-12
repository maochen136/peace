package cn.edu.hebtu.software.peace;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.hebtu.software.peace.utils.MusicService1;
import cn.edu.hebtu.software.peace.utils.MusicService2;
import cn.edu.hebtu.software.peace.utils.MusicService3;
import cn.edu.hebtu.software.peace.utils.MusicService4;
import cn.edu.hebtu.software.peace.utils.TasksCompletedView;

public class StartSleepActivity extends AppCompatActivity {
    private TasksCompletedView mTasksView;
    private int mTotalProgress;
    private int mCurrentProgress;
    private Handler mHandler = new Handler();
    private boolean isClick;
    private TextView startsleep_txtMsg;
    private MediaPlayer mediaPlayer;
    private ViewPager sleepViewPager;
    private List<View> mViewList = new ArrayList<View>();//存放待滑动的view
    private LayoutInflater mInflater;
    private View view_01, view_02, view_03,view_04;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.startsleep);
        initViews();
        initEvent();
        initVariable();
        initView();

        //显示时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        startsleep_txtMsg=findViewById(R.id.startsleep_txtMsg);
        startsleep_txtMsg.setText(simpleDateFormat.format(date));
    }

    private void initVariable() {
        mTotalProgress = 100;
        mCurrentProgress = 0;
    }

    private void initView() {
        mTasksView = (TasksCompletedView) findViewById(R.id.startsleep_taskview);
        mTasksView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {// 返回true的话，单击事件、长按事件不可以被触发
                    case MotionEvent.ACTION_DOWN:
                        isClick = true;
                        mCurrentProgress = 0;
                        startplay();
                        return true;
                    case MotionEvent.ACTION_UP:
                        isClick = false;

                        break;

                    default:
                        break;
                }

                return false;
            }

        });

    }

    private void startplay() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentProgress < mTotalProgress) {

                    if (isClick) {// 一直长按
                        mCurrentProgress += 1;
                        mHandler.postDelayed(this, 50);
                        mTasksView.setProgress(mCurrentProgress);
                   if (mCurrentProgress==100){
                       int n=sleepViewPager.getCurrentItem();
                       final Intent[] ii = new Intent[4];
                       ii[0] =new Intent(StartSleepActivity.this,MusicService1.class);
                       ii[1] =new Intent(StartSleepActivity.this,MusicService2.class);
                       ii[2] =new Intent(StartSleepActivity.this,MusicService3.class);
                       ii[3] =new Intent(StartSleepActivity.this,MusicService4.class);
                       stopService(ii[n]);
                       Intent intent=new Intent(StartSleepActivity.this,SleepActivity.class);
                       startActivity(intent);
                   }
                    } else {// 中途放弃长按
                        if (mCurrentProgress >= 50) {// 进度超过50%直接走到100%，
                            mCurrentProgress += 1;
                            mHandler.postDelayed(this, 5);
                            mTasksView.setProgress(mCurrentProgress);
                        } else {// 进度没到50%重置为0
                            mCurrentProgress = 0;
                            mTasksView.setProgress(mCurrentProgress);
                        }
                    }
                }
            }
        });

    }
        private  void initViews(){
        mInflater = getLayoutInflater();
        view_01 = mInflater.inflate(R.layout.layout11, null);
        view_02 = mInflater.inflate(R.layout.layout22, null);
        view_03 = mInflater.inflate(R.layout.layout33, null);
        view_04 = mInflater.inflate(R.layout.layout44, null);

        mViewList.add(view_01);
        mViewList.add(view_02);
        mViewList.add(view_03);
        mViewList.add(view_04);
        sleepViewPager =findViewById(R.id.startsleep_type);
        sleepViewPager.setAdapter(new MyPagerAdapter());

            Intent intent=getIntent();
            int id=intent.getIntExtra("type",0);
            switch (id){
                case 0:
                    sleepViewPager.setCurrentItem(0);//设置当前pager
             ;      Intent intent1=new Intent(StartSleepActivity.this,MusicService1.class);
                    startService(intent1);
                    break;
                case 1:
                    sleepViewPager.setCurrentItem(1);//设置当前pager
                    Intent intent2=new Intent(StartSleepActivity.this,MusicService2.class);
                    startService(intent2);
                    break;
                case 2:
                    sleepViewPager.setCurrentItem(2);//设置当前pager
                    Intent intent3=new Intent(StartSleepActivity.this,MusicService3.class);
                    startService(intent3);
                    break;
                case 3:
                    sleepViewPager.setCurrentItem(3);//设置当前pager;
                    Intent intent4=new Intent(StartSleepActivity.this,MusicService4.class);
                    startService(intent4);
                    break;
            }


    }
    private void initEvent(){
        sleepViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //获取ViewPager的当前Tab
                int currentItem = sleepViewPager.getCurrentItem();
                final Intent[] ii = new Intent[4];
                ii[0] =new Intent(StartSleepActivity.this,MusicService1.class);
                ii[1] =new Intent(StartSleepActivity.this,MusicService2.class);
                ii[2] =new Intent(StartSleepActivity.this,MusicService3.class);
                ii[3] =new Intent(StartSleepActivity.this,MusicService4.class);
                switch (currentItem) {
                    case 0:
                        startService(ii[0]);
                        stopService(ii[1]);
                        stopService(ii[2]);
                        stopService(ii[3]);

                        break;
                    case 1:

                        stopService(ii[0]);
                        startService(ii[1]);
                        stopService(ii[2]);
                        stopService(ii[3]);
                        break;
                    case 2:

                        stopService(ii[0]);
                        stopService(ii[1]);
                        startService(ii[2]);
                        stopService(ii[3]);
                        break;
                    case 3:

                        stopService(ii[0]);
                        stopService(ii[1]);
                        stopService(ii[2]);
                        startService(ii[3]);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
