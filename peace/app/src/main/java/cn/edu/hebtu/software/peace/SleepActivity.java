package cn.edu.hebtu.software.peace;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.edu.hebtu.software.peace.alarmactivity.RepeatingAlarm;
import cn.edu.hebtu.software.peace.fragments.SleepFragment1;
import cn.edu.hebtu.software.peace.fragments.SleepFragment2;

public class SleepActivity extends AppCompatActivity {
//    private ViewUtil viewUtil;
//    private ImageView relax;
//    private ImageView ling;
    private TextView time;
    private TextView xiaosleep;
    private TextView sleep;
    int i=1;
    private ImageView close;
    private List<String> alarmTimeList;
    private List<String> respiteTimeList;

    private ImageView ivRelax;

    Calendar c = Calendar.getInstance( Locale.CHINESE );
    AlarmManager am;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        alarmTimeList = new ArrayList<>(2);
        respiteTimeList = new ArrayList<>(1);

        setContentView(R.layout.sleep);
        replaceFragment(new SleepFragment1());


        //实现闹钟铃声
        am = (AlarmManager) getSystemService( ALARM_SERVICE );
        sleep=findViewById(R.id.sleep);
        xiaosleep=findViewById(R.id.xiaosleep);
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SleepFragment1());
            }
        });
        xiaosleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SleepFragment2());
            }
        });

        close=findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sleepclose_intent=new Intent();
                sleepclose_intent.setClass(SleepActivity.this,IndexActivity.class);
                startActivity(sleepclose_intent);
            }
        });
        time=findViewById(R.id.awake_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SleepActivity.this,SleepTotalAcitivty.class);
                startActivityForResult(intent,1);
            }
        });


    }
// 为了获取结果
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
               // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:
              // operation succeeded. 默认值是-1
               if (resultCode == 2) {
                    if (requestCode == 1) {
                        alarmTimeList = (List<String>) data.getSerializableExtra("awake");
                        //alarmTimeList= data.getSerializableExtra("awake");//获取intent中的数据

//                        String alarmHour = alarmTimeList.get(0);
////                        String alarmMintue = alarmTimeList.get(1);
////                        String alarmTime = alarmHour+":"+alarmMintue;
                        replaceFragment(new SleepFragment1());
                        String alarmHour = alarmTimeList.get(0);
                        String alarmMintue = alarmTimeList.get(1);
                        String alarmTime = alarmHour+":"+alarmMintue;
                        Log.i("mc",alarmTime);
                        getTimeMillis(alarmTime);
                        Calendar calendar = Calendar.getInstance();
                        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
                        Log.i("mc",hour+":"+minute);
                        String s1= null;
                        try {
                            s1 = getTimeExpend(hour+":"+minute,alarmTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        time.setText( "预计睡眠"+s1);

                                new Thread(new MyAlarmThread()).start();

                    }
                   }else if(resultCode == 3){
                        if (requestCode == 1){
                            replaceFragment(new SleepFragment2());
                            respiteTimeList= (List<String>)data.getSerializableExtra("awake");
                            String  s2 = respiteTimeList.get(0);
//                            Serializable s= data.getSerializableExtra("awake");//获取intent中的数据
                            time.setText( "将于"+s2 +"分钟后叫醒");

                                    new Thread(new MyRespiteThread()).start();

                            //new Thread(new MyRespiteThread()).start();
                        }
                   }
    }
    private long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            Toast.makeText(SleepActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return returnMillis;
    }
    private String getTimeExpend(String startTime, String endTime) throws ParseException {

        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差
        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        return longHours + "小时" + longMinutes+"分";
    }
    public void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.sleepFragment1,fragment);
        transaction.commit();

    }
//    private class TextListener implements View.OnClickListener{
//        public void onClick(View v){
//            switch (v.getId()){
//                case R.id.sleep:
//                    ling.setImageResource(R.drawable.ling);
//                    time.setText("8:00");
//                    relax.setImageResource(R.drawable.relax1);
//                    floatAnim(relax,1);
//                    time.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent choosetime=new Intent();
//                            choosetime.setClass(SleepActivity.this,SleepTotalAcitivty.class);
//                            startActivity(choosetime);
//                        }
//                    });
//                    break;
//                case R.id.xiaosleep:
//                    ling.setImageResource(R.drawable.shalou);
//                    time.setText("30分钟");
//                    relax.setImageResource(R.drawable.eye);
//                    floatAnim(relax,1);
//                    break;
//            }
//        }
//    }

//    @SuppressLint("WrongConstant")
//    private void floatAnim(ImageView imageView, int delay) {
//        List<Animator> animators = new ArrayList<>();
//
//        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(imageView, "translationY", -25.0f,25.0f, -25.0f);
//        translationYAnim.setDuration(3000);
//        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
//        translationYAnim.setRepeatMode(ValueAnimator.INFINITE);
//        translationYAnim.start();
//        animators.add(translationYAnim);
//
//        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
//        btnSexAnimatorSet.playTogether(animators);
//        btnSexAnimatorSet.setStartDelay(delay);
//        btnSexAnimatorSet.start();
//    }
//    private  void initViews(){
//        mInflater = getLayoutInflater();
//        view_01 = mInflater.inflate(R.layout.layout1, null);
//        view_02 = mInflater.inflate(R.layout.layout2, null);
//        view_03 = mInflater.inflate(R.layout.layout3, null);
//        view_04 = mInflater.inflate(R.layout.layout4, null);
//
//        mViewList.add(view_01);
//        mViewList.add(view_02);
//        mViewList.add(view_03);
//        mViewList.add(view_04);
//        mViewPager =findViewById(R.id.txtMsg);
//        mViewPager.setAdapter(new MyPagerAdapter());
//        mViewPager.setCurrentItem(0);//设置当前pager
//
//    }
//    class MyPagerAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {//返回view数量
//            return mViewList.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mViewList.get(position));
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mViewList.get(position), 0);
//            return mViewList.get(position);
//        }
//    }



    //启动一个闹钟
    private void setAlarm(Calendar c) {
        Intent intent = new Intent( this, RepeatingAlarm.class );
        intent.setAction( "com.ll.alarm" );
        PendingIntent sender = PendingIntent.getBroadcast( this, 0, intent, 0 );
// Schedule the alarm!


        am.set( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender );//c为设置闹钟的时间的Calendar对象
    }

    //alarm闹钟实现时间差
    public class MyRespiteThread implements Runnable{
        @Override
        public void run() {

            int minute = Integer.parseInt(respiteTimeList.get(0));

            //需要加判等条件
            try {

                Thread.sleep( (minute*60)*1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setAlarm( c );
        }
    }

    //小憩的时间计算
    public class MyAlarmThread implements Runnable{
        @Override
        public void run() {
            Date starttime = new Date();
            int hour = starttime.getHours();
            int mintue = starttime.getMinutes();


            final int sleepHoour = Integer.parseInt(alarmTimeList.get(0))-hour;
            final int sleepMinute = Integer.parseInt(alarmTimeList.get(1))-mintue;

            //需要加判等条件
            try {

                Thread.sleep( (sleepHoour*3600+sleepMinute*60)*1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setAlarm( c );
        }



    }




}
