package cn.edu.hebtu.software.peace;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.fragments.AlarmFragment;
import cn.edu.hebtu.software.peace.fragments.RespiteFragment;

public class SleepTotalAcitivty extends AppCompatActivity implements AlarmFragment.CallBackValue,RespiteFragment.RespiteCallBackValue{


    private List<String> respiteList;
    private List<String> alarmList;
    private TextView[] textViews;
    private ViewPager viewPager;
    private Fragment[] fragments;
    private Button buttonSave;
    private String alarmHourValue, alarmMintueValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sleep_total_layout );
        buttonSave = findViewById( R.id.save_all_time );

        init();
        viewPager.setAdapter( new MyAdapter( getSupportFragmentManager() ) );
        getVisibleFragment();
        viewPager.setCurrentItem( 0 );

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                Intent intent=new Intent();
                if (current == 0) {
//                    String alarmHour = alarmList.get(0);
//                    String alarmMintue = alarmList.get(1);
//                    String alarmTime = alarmHour+":"+alarmMintue;
                    intent.putExtra( "awake", (Serializable) alarmList);
//                    intent.setClass( SleepTotalAcitivty.this, SleepActivity.class );
//                    startActivity( intent );
                    setResult(2, intent);
                }
                else if(current==1){
//                    String  respiteMinute = respiteList.get(0);
                    intent.putExtra( "awake", (Serializable) respiteList);
//                    intent.setClass( SleepTotalAcitivty.this, SleepActivity.class );
//                    startActivity( intent );
                    setResult(3, intent);
                }
                finish();


            }
        } );
    }

    public void init() {
        viewPager = findViewById( R.id.view_pager );
        LinearLayout linearLayout = findViewById( R.id.dot_layout );
        int count = linearLayout.getChildCount();
        textViews = new TextView[count];
        fragments = new Fragment[count];
        fragments[0] = new AlarmFragment();
        fragments[1] = new RespiteFragment();

        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedImage( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );

        for (int i = 0; i < count; i++) {
            textViews[i] = (TextView) linearLayout.getChildAt( i );
            textViews[i].setEnabled( true );
            textViews[i].setTag( i );
            textViews[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item = (int) v.getTag();
                    viewPager.setCurrentItem( item );
                    selectedImage( item );
                }
            } );
        }
    }

    private void selectedImage(int item) {
        //这一步从使能到不使能是状态发生了变化，只要状态发生了变化，那么就会简介调用dot里面的select
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setEnabled( true );
        }
        textViews[item].setEnabled( false );
    }




    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super( fm );
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = SleepTotalAcitivty.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) return fragment;
            Log.e( "fragment", String.valueOf( fragment ) );
        }
        return null;
    }


    @Override
    public void SendMessageValue(List<String> data) {
        alarmList = new ArrayList<>( 2 );
        alarmList = data;
    }

    @Override
    public void SendRespiteValue(List<String> data) {
        respiteList = new ArrayList<>( 1 );
        respiteList = data;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView firstCardText;
        firstCardText = findViewById(R.id.first_card_alarm);
        if (requestCode == 12 && resultCode == 24) {
            String resultData = data.getStringExtra("yinyue");
            firstCardText.setText(resultData);
            Log.e("收到没啦", resultData);
        } else {
            firstCardText.setText("晨诗");
        }
    }
}

